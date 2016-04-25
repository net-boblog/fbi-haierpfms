package pfms.service.inv;

import gateway.sbs.txn.model.form.re.T467;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pfms.enums.EnuZzsKpFlag;
import pfms.enums.EnuZzsProcFlag;
import pfms.enums.EnuZzsSrc;
import pfms.repository.dao.InvZzsHeadMapper;
import pfms.repository.dao.InvZzsItemMapper;
import pfms.repository.dao.InvZzsSrcMapper;
import pfms.repository.dao.custom.CustomMapper;
import pfms.repository.dao.rwkj.XwsqZzsHeadMapper;
import pfms.repository.dao.rwkj.XwsqZzsItemMapper;
import pfms.repository.model.InvZzsHead;
import pfms.repository.model.InvZzsItem;
import pfms.repository.model.InvZzsSrc;
import pfms.repository.model.InvZzsSrcExample;
import pfms.repository.model.custom.CustomInvZzsSrc;
import pfms.repository.model.rwkj.XwsqZzsHead;
import pfms.repository.model.rwkj.XwsqZzsItem;
import pfms.util.ToolUtil;
import skyline.common.MybatisFactory;
import skyline.common.PropertyManager;
import skyline.service.PlatformService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 增值税原始数据维护
 */
@Service
public class InvZzsSrcService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private InvZzsSrcMapper invZzsSrcMapper;

    @Resource
    private InvZzsHeadMapper invZzsHeadMapper;

    @Resource
    private InvZzsItemMapper invZzsItemMapper;

    @Resource
    private CustomMapper customMapper;

    @Resource
    private PlatformService platformService;

    @Transactional
    public void insert(String txnDate, List<T467.Bean> dataList) {
        InvZzsSrc invZzsSrc;
        String sysdate = ToolUtil.getDateDash();
        for (T467.Bean bean : dataList) {
            // 验证主键（流水号、数据来源）是否重复
            InvZzsSrcExample example = new InvZzsSrcExample();
            InvZzsSrcExample.Criteria criteria = example.createCriteria();
            criteria.andFbtidxEqualTo(bean.getFBTIDX());
            criteria.andDatasrcEqualTo(EnuZzsSrc.SRC_00.getCode());
            int count = invZzsSrcMapper.countByExample(example);
            if (count > 0) {
                logger.error("主键（流水号、数据来源）重复！流水号：" + bean.getFBTIDX() +
                        " 数据来源：" + EnuZzsSrc.SRC_00.getCode());
                continue;
            }

            invZzsSrc = new InvZzsSrc();
            invZzsSrc.setFbtidx(bean.getFBTIDX());                     // 流水号
            invZzsSrc.setDatasrc(EnuZzsSrc.SRC_00.getCode());          // 数据来源
            invZzsSrc.setKhdm(bean.getCUSIDT());                       // 客户代码
            invZzsSrc.setCpdm(bean.getPRDCDE());                       // 产品代码
            invZzsSrc.setCpmc(bean.getPRDNAM());                       // 产品名称
            invZzsSrc.setXh(bean.getPRDTYP());                         // 型号
            invZzsSrc.setCpdw(bean.getPRDUNT());                       // 产品单位
            if (StringUtils.isNotEmpty(bean.getPRDCNT())) {
                invZzsSrc.setCpsl(new BigDecimal(bean.getPRDCNT()));   // 数量
            }
            if (StringUtils.isNotEmpty(bean.getTOTPRI())) {
                invZzsSrc.setHsdj(new BigDecimal(bean.getTOTPRI()));   // 含税单价
            }
            if (StringUtils.isNotEmpty(bean.getTOTAMT())) {
                invZzsSrc.setHsje(new BigDecimal(bean.getTOTAMT()));   // 含税金额
            }
            if (StringUtils.isNotEmpty(bean.getCLNPRI())) {
                invZzsSrc.setXxdj(new BigDecimal(bean.getCLNPRI()));   // 不含税单价
            }
            if (StringUtils.isNotEmpty(bean.getCLNAMT())) {
                invZzsSrc.setBhsje(new BigDecimal(bean.getCLNAMT()));  // 不含税金额
            }
            if (StringUtils.isNotEmpty(bean.getTAXAMT())) {
                invZzsSrc.setSe(new BigDecimal(bean.getTAXAMT()));     // 税额
            }
            if (StringUtils.isNotEmpty(bean.getTAXRAT())) {
                invZzsSrc.setSl(new BigDecimal(bean.getTAXRAT()));     // 税率
            }
            if (StringUtils.isNotEmpty(bean.getOFCAMT())) {
                invZzsSrc.setZbhsje(new BigDecimal(bean.getOFCAMT())); // 折扣不含税金额
            }
            if (StringUtils.isNotEmpty(bean.getOFCTAX())) {
                invZzsSrc.setZse(new BigDecimal(bean.getOFCTAX()));    // 折扣税额
            }
            invZzsSrc.setCrtDate(sysdate);                             // 创建日期YYYY-MM-DD
            invZzsSrc.setCrtTime(ToolUtil.getTimeColon());             // 创建时间HH:mm:ss
            invZzsSrc.setCrtOperId("sbs");                             // 创建者ID
            invZzsSrc.setProcFlag(EnuZzsProcFlag.PROC_FLAG_0.getCode()); // 处理标志
            invZzsSrc.setTxnDate(txnDate);                              // 交易日期
            invZzsSrcMapper.insertSelective(invZzsSrc);
        }
    }

    /**
     * 查询待处理数据
     *
     * @param customInvZzsSrc
     * @return
     */
    public List<CustomInvZzsSrc> selectUnProc(CustomInvZzsSrc customInvZzsSrc) {
        return customMapper.selectUnProc(customInvZzsSrc);
    }

    /**
     * 打印发票
     *
     * @param selectedRecords
     * @param fpzl
     * @param operId
     */
    @Transactional
    public void printFp(CustomInvZzsSrc[] selectedRecords, String fpzl, String operId) throws Exception {
        String sysdate = ToolUtil.getDateDash();
        InvZzsHead invZzsHead = null;
        InvZzsItem invZzsItem = null;
        XwsqZzsHead xwsqZzsHead = null;
        XwsqZzsItem xwsqZzsItem = null;
        String dmgs = PropertyManager.getProperty("DMGS");
        Short mxxh = 1;
        SqlSessionFactory factory = MybatisFactory.ORACLE.getInstance();
        SqlSession session = factory.openSession();
        XwsqZzsHeadMapper xwsqZzsHeadMapper = session.getMapper(XwsqZzsHeadMapper.class);
        XwsqZzsItemMapper xwsqZzsItemMapper = session.getMapper(XwsqZzsItemMapper.class);
        try {
            for (CustomInvZzsSrc record : selectedRecords) {
                long xsddm = platformService.obtainSeqNo("xsddm");
                // 插入本地销售发票头
                invZzsHead = new InvZzsHead();
                invZzsHead.setFbtidx(record.getFbtidx());   // 流水号
                invZzsHead.setDatasrc(record.getDatasrc()); // 数据来源
                invZzsHead.setXsddm(String.valueOf(xsddm)); // 单据号码
                invZzsHead.setDmgs(dmgs);                   // 单据公司
                invZzsHead.setKhdm(record.getKhdm());       // 客户代码
                invZzsHead.setKhswdjh(record.getKhswdjh()); // 客户税号
                invZzsHead.setKhmc(record.getKhmc());       // 客户名称
                invZzsHead.setKhsj(record.getKhsj());       // 客户手机号
                invZzsHead.setKhdz(record.getKhdz());       // 客户地址
                invZzsHead.setKhyh(record.getKhyh());       // 开户银行
                invZzsHead.setKprq(new Date());             // 单据日期
                invZzsHead.setFpzl(fpzl);                   // 发票类别
                invZzsHead.setXrrq(new Date());             // 写入时间
                invZzsHead.setKpFlag(EnuZzsKpFlag.KP_FLAG_0.getCode()); // 开票标志
                invZzsHeadMapper.insert(invZzsHead);

                // 插入本地销售发票明细
                invZzsItem = new InvZzsItem();
                invZzsItem.setXsddm(String.valueOf(xsddm)); // 单据号码
                invZzsItem.setDmgs(dmgs);                   // 单据公司
                invZzsItem.setMxxh(mxxh);                   // 记录行号
                invZzsItem.setCpdm(record.getCpdm());       // 产品代码
                invZzsItem.setCpmc(record.getCpmc());       // 产品名称
                invZzsItem.setXh(record.getXh());           // 型号
                invZzsItem.setCpdw(record.getCpdw());       // 产品单位
                invZzsItem.setCpsl(record.getCpsl());       // 数量
                invZzsItem.setHsdj(record.getHsdj());       // 含税单价
                invZzsItem.setHsje(record.getHsje());       // 含税金额
                invZzsItem.setXxdj(record.getXxdj());       // 不含税单价
                invZzsItem.setBhsje(record.getBhsje());     // 不含税金额
                invZzsItem.setSe(record.getSe());           // 税额
                invZzsItem.setSl(record.getSl());           // 税率
                invZzsItem.setZbhsje(record.getZbhsje());   // 折扣不含税金额
                invZzsItem.setZse(record.getZse());         // 折扣税额
                invZzsItemMapper.insert(invZzsItem);

                // 插入远程销售发票头
                xwsqZzsHead = new XwsqZzsHead();
                xwsqZzsHead.setXsddm(String.valueOf(xsddm)); // 单据号码
                xwsqZzsHead.setDmgs(dmgs);                   // 单据公司
                xwsqZzsHead.setKhdm(record.getKhdm());       // 客户代码
                xwsqZzsHead.setKhswdjh(record.getKhswdjh()); // 客户税号
                xwsqZzsHead.setKhmc(record.getKhmc());       // 客户名称
                xwsqZzsHead.setKhsj(record.getKhsj());       // 客户手机号
                xwsqZzsHead.setKhdz(record.getKhdz());       // 客户地址
                xwsqZzsHead.setKhyh(record.getKhyh());       // 开户银行
                xwsqZzsHead.setKprq(new Date());             // 单据日期
                xwsqZzsHead.setFpzl(fpzl);                   // 发票类别
                xwsqZzsHead.setXrrq(new Date());             // 写入时间
                xwsqZzsHeadMapper.insert(xwsqZzsHead);


                // 插入远程销售发票明细
                xwsqZzsItem = new XwsqZzsItem();
                xwsqZzsItem.setXsddm(String.valueOf(xsddm)); // 单据号码
                xwsqZzsItem.setDmgs(dmgs);                   // 单据公司
                xwsqZzsItem.setMxxh(mxxh);                   // 记录行号
                xwsqZzsItem.setCpdm(record.getCpdm());       // 产品代码
                xwsqZzsItem.setCpmc(record.getCpmc());       // 产品名称
                xwsqZzsItem.setXh(record.getXh());           // 型号
                xwsqZzsItem.setCpdw(record.getCpdw());       // 产品单位
                xwsqZzsItem.setCpsl(record.getCpsl());       // 数量
                xwsqZzsItem.setHsdj(record.getHsdj());       // 含税单价
                xwsqZzsItem.setHsje(record.getHsje());       // 含税金额
                xwsqZzsItem.setXxdj(record.getXxdj());       // 不含税单价
                xwsqZzsItem.setBhsje(record.getBhsje());     // 不含税金额
                xwsqZzsItem.setSe(record.getSe());           // 税额
                xwsqZzsItem.setSl(record.getSl());           // 税率
                xwsqZzsItem.setZbhsje(record.getZbhsje());   // 折扣不含税金额
                xwsqZzsItem.setZse(record.getZse());         // 折扣税额
                xwsqZzsItemMapper.insert(xwsqZzsItem);

                // 更新原始数据表处理标志
                InvZzsSrc invZzsSrc = new InvZzsSrc();
                invZzsSrc.setFbtidx(record.getFbtidx());                     // 流水号
                invZzsSrc.setDatasrc(record.getDatasrc());                   // 数据来源
                invZzsSrc.setUpdDate(sysdate);                               // 修改日期YYYY-MM-DD
                invZzsSrc.setUpdTime(ToolUtil.getTimeColon());               // 修改时间HH:mm:ss
                invZzsSrc.setUpdOperId(operId);                              // 修改者ID
                invZzsSrc.setProcFlag(EnuZzsProcFlag.PROC_FLAG_1.getCode()); // 处理标志
                invZzsSrcMapper.updateByPrimaryKeySelective(invZzsSrc);
            }
            session.commit();
        } catch (Exception e) {
            session.rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }
}
