package skyline.service;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import skyline.common.MD5Helper;
import skyline.repository.dao.PtoperMapper;
import skyline.repository.model.Ptdept;
import skyline.repository.model.Ptmenu;
import skyline.repository.model.Ptoper;
import skyline.repository.model.Ptoplog;
import skyline.security.OperManager;

import javax.annotation.Resource;
import javax.faces.model.SelectItem;
import java.util.*;

/**
 * Created by zhanrui on 2015/11/05.
 * 处理平台表信息
 */
@Service
public class PlatformService {
    private static final Logger logger = LoggerFactory.getLogger(PlatformService.class);

    @Autowired
    private OperManager operManager;

    @Resource
    private NamedParameterJdbcTemplate skylineJdbc;

    @Autowired
    private PtoperMapper ptoperMapper;


    /**
     * 获取流水号
     * 由数据库表Ptsequence控制, 按顺序获取
     * @param seqName  流水号名称
     * @return 最大12位整数
     */
    public long obtainSeqNo(String seqName) {
        SequenceManager sm = SequenceManager.getInstance(skylineJdbc);
        return sm.nextID(seqName);
    }

    //返回 机构号|机构名称(前面加全角空格)
    public List<String> selectBranchLevelString(String instId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("instId", instId);
        String sql = "select deptid || '|' || LPad('　', (level - 1) * 2, '　') || deptname" +
                "  from ptdept" +
                " start with deptid = :instId" +
                " connect by prior deptid = parentdeptid";
        return skylineJdbc.queryForList(sql, paramMap, String.class);
    }

    //机构号
    public List<String> selectBranchLevelList(String instId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("instId", instId);
        String sql = "select deptid " +
                "  from ptdept" +
                " start with deptid = :instId" +
                " connect by prior deptid = parentdeptid";
        return skylineJdbc.queryForList(sql, paramMap, String.class);
    }

    public List<SelectItem> selectBranchList(String branchId) {
        List<String> records = selectBranchLevelString(branchId);
        List<SelectItem> selectItems = new ArrayList<SelectItem>();
        for (String record : records) {
            String[] recordArray = record.split("\\|");
            SelectItem item = new SelectItem(recordArray[0], recordArray[1]);
            selectItems.add(item);
        }
        return selectItems;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertNewOperationLog(Ptoplog ptoplog) {
        if (!this.operManager.isLogined()) {
            throw new RuntimeException("用户未登录！");
        }

        ptoplog.setGuid(UUID.randomUUID().toString());
        ptoplog.setOperId(operManager.getOperInfo().operId);
        ptoplog.setOperName(operManager.getOperInfo().operName);
        ptoplog.setOperBranchid(operManager.getOperInfo().getPtdept().getDeptid());
        ptoplog.setHostIp("");
        ptoplog.setOpDate(new Date());

        String sql = "insert into ptoplog (GUID, MENU_ID, MENU_NAME, ACTION_ID, ACTION_NAME, OPER_ID, OPER_NAME, OPER_BRANCHID, \n" +
                "    OPER_BRANCHNAME, OP_DATE, OP_DATA_BRANCHID, OP_DATA_BRANCHNAME, OP_DATA_STARTDATE, \n" +
                "    OP_DATA_ENDDATE, OP_LOG, HOST_IP)" +
                " VALUES (:guid, :menuId, :menuName, :actionId, :actionName, :operId, :operName, :operBranchid, \n" +
                "    :operBranchname, :opDate, :opDataBranchid, :opDataBranchname, :opDataStartdate, \n" +
                "    :opDataEnddate, :opLog, :hostIp)";
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(ptoplog);
        skylineJdbc.update(sql, paramSource);
    }


    //==============
    public OperInfo initOperInfo(String operId) {
        Ptoper ptoper = selectPtoper(operId.trim());
        if (ptoper == null) {
            return null;
        }
        OperInfo operInfo = new OperInfo();
        operInfo.ptoper = ptoper;
        operInfo.operId = operId.trim();
        operInfo.operName = ptoper.getOpername();

        operInfo.ptdept = selectPtdept(ptoper.getDeptid());
//        operInfo.ptRoles = selectRoles(operId);
//        operInfo.ptLinks = selectWorkbenchPermission(operId);
        return operInfo;
    }

    private Ptoper selectPtoper(String operId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("operId", operId);
        String sql = "select * from PTOPER t where t.operid=:operId";
        List<Ptoper> ptopers = skylineJdbc.query(sql, paramMap, new BeanPropertyRowMapper(Ptoper.class));
        if (ptopers.size() == 0) {
            return null;
        } else {
            return ptopers.get(0);
        }
        //return (Ptoper) skylineJdbc.queryForObject(sql, paramMap, new BeanPropertyRowMapper(Ptoper.class));
    }

    private Ptdept selectPtdept(String deptId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("deptId", deptId);
        String sql = "select * from PTDEPT t where t.deptid=:deptId";
        List<Ptdept> ptdepts = skylineJdbc.query(sql, paramMap, new BeanPropertyRowMapper(Ptdept.class));
        if (ptdepts.size() == 0) {
            return null;
        } else {
            return ptdepts.get(0);
        }
        //return (Ptdept) skylineJdbc.queryForObject(sql, paramMap, new BeanPropertyRowMapper(Ptdept.class));
    }


    //获取某操作人员所拥有的菜单
    public List<Ptmenu> selectMenuList(String operId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("operId", operId);

        String sql = "select menu.* from (select t1.*, t2.resname\n" +
                "  from (select distinct b.resid\n" +
                "          from ptoperrole a\n" +
                "          left join ptroleres b\n" +
                "            on a.roleid = b.roleid\n" +
                "         where a.operid = :operId) t1\n" +
                "  left join ptresource t2\n" +
                "    on t1.resid = t2.resid\n" +
                " where t2.restype = '4') res, ptmenu menu\n" +
                " where res.resname = menu.menuid\n" +
//                "   and (menu.targetmachine is null or menu.targetmachine != 'system')\n" +
//                "   and menu.menulevel = 1\n" +
//                "   and menu.isleaf = 0\n" +
//                "   and menu.targetmachine = :moduleName\n" +
                "   order by menu.levelidx";

        logger.info("select top menu sql=" + sql);
        return skylineJdbc.query(sql, paramMap, new BeanPropertyRowMapper<Ptmenu>(Ptmenu.class));
    }

/*
    //获取操作员岗位名称
    public List<String> selectRolesInfo(String operId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("operId", operId);
        String sql = "select b.roledesc from dbo.t_hrm_role_staff a left join dbo.t_hrm_rolemanage b on a.roleid=b.roleid  where a.serno=:operId";
        return skylineJdbc.queryForList(sql, paramMap, String.class);
    }
*/



    //获取MD5后的密文。
    public String getCipherPassword(String operId, String password) {
        if (operId.length() < 6) {//做兼容旧系统处理
            operId = StringUtils.rightPad(operId, 6, " ");
        }
        password = operId.substring(3, 6) + password + operId.substring(0, 3);
        return MD5Helper.getMD5String(password);
    }

    //修改用户密码
    //原始public void changeOperPassword
    //2015-11-24 改public int changeOperPassword
    public int changeOperPassword(String operId, String password) {

        Ptoper oper = ptoperMapper.selectByPrimaryKey(operId);
        if (oper == null) {
            throw new RuntimeException("无此用户.");
        }
        oper.setOperpasswd(getCipherPassword(operId, password));
        ptoperMapper.updateByPrimaryKey(oper);

        return 1;
    }
/*
    //查询原始密码是否一致
    public int getCountByPasswordAndOperId(String operId, String password) {
        THrmStaffBaseInfoExample example = new THrmStaffBaseInfoExample();
        example.createCriteria().andSerPwdEqualTo(getCipherPassword(operId, password))
                                .andSerNoEqualTo(operId);
        return operMapper.countByExample(example);
    }
*/
}
