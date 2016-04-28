package pfms.service.inv;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pfms.enums.*;
import pfms.repository.dao.*;
import pfms.repository.dao.custom.CustomMapper;
import pfms.repository.dao.rwkj.XwsqZzsHeadMapper;
import pfms.repository.dao.rwkj.XwsqZzsKphxMapper;
import pfms.repository.dao.rwkj.XwsqZzsZfMapper;
import pfms.repository.dao.rwkj.XwsqZzsZfhxMapper;
import pfms.repository.model.*;
import pfms.repository.model.custom.CustomInvZzsHead;
import pfms.repository.model.rwkj.*;
import pfms.util.ToolUtil;
import skyline.common.MybatisFactory;
import skyline.common.PropertyManager;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 增值税销售发票
 */
@Service
public class InvZzsHeadService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private InvZzsHeadMapper invZzsHeadMapper;

    @Resource
    private InvZzsKphxMapper invZzsKphxMapper;

    @Resource
    private InvZzsZfMapper invZzsZfMapper;

    @Resource
    private InvZzsZfhxMapper invZzsZfhxMapper;

    @Resource
    private InvZzsSrcMapper invZzsSrcMapper;

    @Resource
    private CustomMapper customMapper;

    /**
     * 查询待开票数据
     *
     * @param customInvZzsHead
     * @return
     */
    public List<CustomInvZzsHead> selectUnPrint(CustomInvZzsHead customInvZzsHead) {
        return customMapper.selectUnPrint(customInvZzsHead);
    }

    /**
     * 查询已开票数据
     *
     * @param customInvZzsHead
     * @return
     */
    public List<CustomInvZzsHead> selectPrint(CustomInvZzsHead customInvZzsHead) {
        return customMapper.selectPrint(customInvZzsHead);
    }

    /**
     * 作废
     *
     * @param customInvZzsHead
     * @param operId
     */
    @Transactional
    public void zuoFei(CustomInvZzsHead customInvZzsHead, String operId) throws Exception {
        SqlSessionFactory factory = MybatisFactory.ORACLE.getInstance();
        SqlSession session = factory.openSession();
        XwsqZzsZfMapper xwsqZzsZfMapper = session.getMapper(XwsqZzsZfMapper.class);
        try {
            // 更新本地销售发票头
            InvZzsHead invZzsHead = new InvZzsHead();
            invZzsHead.setXsddm(customInvZzsHead.getXsddm());       // 单据号码
            invZzsHead.setDmgs(customInvZzsHead.getDmgs());         // 单据公司
            invZzsHead.setZfFlag(EnuZzsZfFlag.ZF_FLAG_0.getCode()); // 作废标志
            invZzsHead.setUpdDate(ToolUtil.getDateDash());          // 修改日期YYYY-MM-DD
            invZzsHead.setUpdTime(ToolUtil.getTimeColon());         // 修改时间HH:mm:ss
            invZzsHead.setUpdOperId(operId);                        // 修改者ID
            invZzsHeadMapper.updateByPrimaryKeySelective(invZzsHead);

            // 插入本地作废、冲红发票
            InvZzsZf invZzsZf = new InvZzsZf();
            invZzsZf.setInvoicecode(customInvZzsHead.getFphm()); // 正向发票号
            invZzsZf.setType(EnuZzsZfType.ZF_TYPE_1.getCode()); // 类型 1：作废  2 冲红
            invZzsZf.setXrrq(new Date());                        // 写入时间
            invZzsZfMapper.insertSelective(invZzsZf);

            // 插入远程作废、冲红发票
            XwsqZzsZf xwsqZzsZf = new XwsqZzsZf();
            xwsqZzsZf.setInvoicecode(customInvZzsHead.getFphm()); // 正向发票号
            xwsqZzsZf.setType(EnuZzsZfType.ZF_TYPE_1.getCode()); // 类型 1：作废  2 冲红
            xwsqZzsZf.setXrrq(new Date());                        // 写入时间
            xwsqZzsZfMapper.insertSelective(xwsqZzsZf);

            session.commit();
        } catch (Exception e) {
            session.rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    /**
     * 查询待作废数据
     *
     * @param customInvZzsHead
     * @return
     */
    public List<CustomInvZzsHead> selectUnZuoFei(CustomInvZzsHead customInvZzsHead) {
        return customMapper.selectUnZuoFei(customInvZzsHead);
    }

    /**
     * 查询已作废数据
     *
     * @param customInvZzsHead
     * @return
     */
    public List<CustomInvZzsHead> selectZuoFei(CustomInvZzsHead customInvZzsHead) {
        return customMapper.selectZuoFei(customInvZzsHead);
    }

    /**
     * 同步远程数据到本地
     *
     * @param unPrintXsddm
     * @param unZuoFeiFphm
     * @throws Exception
     */
    @Transactional
    public void syncRemote(List<String> unPrintXsddm, List<String> unZuoFeiFphm) throws Exception {
        SqlSessionFactory factory = MybatisFactory.ORACLE.getInstance();
        SqlSession session = factory.openSession();
        try {
            String dmgs = PropertyManager.getProperty("DMGS");
            // 根据待开票数据的单据号码查询远程【XWSQ_ZZS_HEAD、XWSQ_ZZS_KPHX】表的数据
            if (unPrintXsddm.size() > 0) {
                // 查询远程XWSQ_ZZS_HEAD表数据
                XwsqZzsHeadMapper xwsqZzsHeadMapper = session.getMapper(XwsqZzsHeadMapper.class);
                XwsqZzsHeadExample example = new XwsqZzsHeadExample();
                XwsqZzsHeadExample.Criteria criteria = example.createCriteria();
                criteria.andXsddmIn(unPrintXsddm); // 单据号码
                criteria.andDmgsEqualTo(dmgs);     // 单据公司
                criteria.andFphmIsNotNull();       // 发票号码
                List<XwsqZzsHead> xwsqZzsHeadList = xwsqZzsHeadMapper.selectByExample(example);
                InvZzsHead invZzsHead = null;
                for (XwsqZzsHead xwsqZzsHead : xwsqZzsHeadList) {
                    // 更新本地销售发票头
                    invZzsHead = new InvZzsHead();
                    invZzsHead.setXsddm(xwsqZzsHead.getXsddm());         // 单据号码
                    invZzsHead.setDmgs(xwsqZzsHead.getDmgs());           // 单据公司
                    invZzsHead.setFphm(xwsqZzsHead.getFphm());           // 发票号码
                    invZzsHead.setMsg(xwsqZzsHead.getMsg());             // 电子发票返回结果信息
                    invZzsHead.setUrl(xwsqZzsHead.getUrl());             // 电子发票返回的URL
                    invZzsHead.setSkrq(xwsqZzsHead.getSkrq());           // 税控开票日期
                    invZzsHead.setXrrq(xwsqZzsHead.getXrrq());           // 写入时间
                    invZzsHead.setPzrq(xwsqZzsHead.getPzrq());
                    invZzsHead.setPzxx(xwsqZzsHead.getPzxx());
                    invZzsHead.setKpbz(xwsqZzsHead.getKpbz());
                    invZzsHead.setXrsj(xwsqZzsHead.getXrsj());
                    invZzsHead.setPzhxcs(xwsqZzsHead.getPzhxcs());
                    invZzsHead.setPzh(xwsqZzsHead.getPzh());
                    invZzsHead.setPzmessage(xwsqZzsHead.getPzmessage()); // 凭证返回信息
                    invZzsHead.setPzclbz(xwsqZzsHead.getPzclbz());       // 凭证处理标志
                    invZzsHead.setTzdh(xwsqZzsHead.getTzdh());
                    invZzsHead.setChfph(xwsqZzsHead.getChfph());
                    invZzsHead.setClcs(xwsqZzsHead.getClcs());
                    invZzsHead.setKpFlag(EnuZzsKpFlag.KP_FLAG_1.getCode()); // 开票标志
                    invZzsHeadMapper.updateByPrimaryKeySelective(invZzsHead);
                }

                // 查询远程XWSQ_ZZS_KPHX表数据
                List<String> flagList = new ArrayList<String>();
                flagList.add(EnuZzsKpFlag.KP_FLAG_1.getCode());
                flagList.add(EnuZzsKpFlag.KP_FLAG_2.getCode());
                XwsqZzsKphxMapper xwsqZzsKphxMapper = session.getMapper(XwsqZzsKphxMapper.class);
                XwsqZzsKphxExample kphxExample = new XwsqZzsKphxExample();
                XwsqZzsKphxExample.Criteria kphxCriteria = kphxExample.createCriteria();
                kphxCriteria.andXsddmIn(unPrintXsddm); // 单据号码
                kphxCriteria.andDmgsEqualTo(dmgs);     // 单据公司
                kphxCriteria.andFlagIn(flagList);      // 标识  0 未开票 1 已开票 2 开票失败
                List<XwsqZzsKphx> xwsqZzsKphxList = xwsqZzsKphxMapper.selectByExample(kphxExample);
                InvZzsKphx invZzsKphx = null;
                InvZzsHeadKey invZzsHeadKey = null;
                InvZzsHead invZzsHeadSrc = null;
                InvZzsSrc invZzsSrc = null;
                for (XwsqZzsKphx xwsqZzsKphx : xwsqZzsKphxList) {
                    // 插入本地INV_ZZS_KPHX
                    invZzsKphx = new InvZzsKphx();
                    invZzsKphx.setXsddm(xwsqZzsKphx.getXsddm());             // 单据号码
                    invZzsKphx.setFlag(xwsqZzsKphx.getFlag());               // 标识  0 未开票 1 已开票 2 开票失败
                    invZzsKphx.setInvoicecode(xwsqZzsKphx.getInvoicecode()); // 发票号
                    invZzsKphx.setKprq(xwsqZzsKphx.getKprq());               // 开票日期
                    invZzsKphx.setFiscalcode(xwsqZzsKphx.getFiscalcode());   // 税控码
                    invZzsKphx.setNotaxamount(xwsqZzsKphx.getNotaxamount()); // 不含税总额
                    invZzsKphx.setTaxamount(xwsqZzsKphx.getTaxamount());     // 税金
                    invZzsKphx.setTotalamount(xwsqZzsKphx.getTotalamount()); // 含税总额
                    invZzsKphx.setDrawer(xwsqZzsKphx.getDrawer());           // 开票人名称
                    invZzsKphx.setViewurl(xwsqZzsKphx.getViewurl());         // 发票查看地址
                    invZzsKphx.setTag1(xwsqZzsKphx.getTag1());               // 预留字段1
                    invZzsKphx.setTag2(xwsqZzsKphx.getTag2());               // 预留字段2
                    invZzsKphx.setMsg(xwsqZzsKphx.getMsg());                 // 电子发票返回的报文
                    invZzsKphx.setDmgs(xwsqZzsKphx.getDmgs());               // 公司
                    invZzsKphx.setXrrq(xwsqZzsKphx.getXrrq());               // 记录写入日期
                    invZzsKphxMapper.insert(invZzsKphx);
                    // 开票失败的处理
                    if (EnuZzsKpFlag.KP_FLAG_2.getCode().equals(xwsqZzsKphx.getFlag())) {
                        // 更新本地销售发票头开票标志为开票失败
                        invZzsHead = new InvZzsHead();
                        invZzsHead.setXsddm(xwsqZzsKphx.getXsddm());         // 单据号码
                        invZzsHead.setDmgs(xwsqZzsKphx.getDmgs());           // 单据公司
                        invZzsHead.setKpFlag(EnuZzsKpFlag.KP_FLAG_2.getCode()); // 开票标志
                        invZzsHeadMapper.updateByPrimaryKeySelective(invZzsHead);

                        // 更新原始数据表处理标志为未处理，使用户可以再次打印发票
                        // 首先需要根据单据号码、单据公司从【INV_ZZS_HEAD】表取得流水号、数据来源
                        // 然后根据流水号、数据来源更新【INV_ZZS_SRC】表的处理标志为【未处理】
                        invZzsHeadKey = new InvZzsHeadKey();
                        invZzsHeadKey.setXsddm(xwsqZzsKphx.getXsddm());
                        invZzsHeadKey.setDmgs(xwsqZzsKphx.getDmgs());
                        invZzsHeadSrc = invZzsHeadMapper.selectByPrimaryKey(invZzsHeadKey);
                        invZzsSrc = new InvZzsSrc();
                        invZzsSrc.setFbtidx(invZzsHeadSrc.getFbtidx());
                        invZzsSrc.setDatasrc(invZzsHeadSrc.getDatasrc());
                        invZzsSrc.setProcFlag(EnuZzsProcFlag.PROC_FLAG_0.getCode()); // 处理标志
                        invZzsSrcMapper.updateByPrimaryKeySelective(invZzsSrc);
                    }
                }
            }
            // 根据待作废发票号码查询远程【XWSQ_ZZS_ZF、XWSQ_ZZS_ZFHX】表的数据
            if (unZuoFeiFphm.size() > 0) {
                // 查询远程XWSQ_ZZS_ZF表数据
                XwsqZzsZfMapper xwsqZzsZfMapper = session.getMapper(XwsqZzsZfMapper.class);
                XwsqZzsZfExample example = new XwsqZzsZfExample();
                XwsqZzsZfExample.Criteria criteria = example.createCriteria();
                criteria.andInvoicecodeIn(unZuoFeiFphm); // 正向发票号
                criteria.andClbzEqualTo(EnuZzsZfClbz.ZF_CLBZ_1.getCode()); // 系统处理标志(0和空，表示未处理，1表示已处理）
                List<XwsqZzsZf> xwsqZzsZfList = xwsqZzsZfMapper.selectByExample(example);
                InvZzsZf invZzsZf = null;
                for (XwsqZzsZf xwsqZzsZf : xwsqZzsZfList) {
                    // 更新本地INV_ZZS_ZF
                    invZzsZf = new InvZzsZf();
                    invZzsZf.setInvoicecode(xwsqZzsZf.getInvoicecode()); // 正向发票号
                    invZzsZf.setType(xwsqZzsZf.getType()); // 类型 1：作废  2 冲红
                    invZzsZf.setTag1(xwsqZzsZf.getTag1()); // 预留字段1
                    invZzsZf.setTag2(xwsqZzsZf.getTag2()); // 预留字段2
                    invZzsZf.setMsg(xwsqZzsZf.getMsg());   // 电子发票返回的报文信息
                    invZzsZf.setClbz(xwsqZzsZf.getClbz()); // 系统处理标志(0和空，表示未处理，1表示已处理）
                    invZzsZf.setSkrq(xwsqZzsZf.getSkrq());
                    invZzsZf.setClcs(xwsqZzsZf.getClcs());
                    invZzsZfMapper.updateByPrimaryKeySelective(invZzsZf);
                }

                // 查询远程XWSQ_ZZS_ZFHX表数据
                List<String> flagList = new ArrayList<String>();
                flagList.add(EnuZzsZfFlag.ZF_FLAG_1.getCode());
                flagList.add(EnuZzsZfFlag.ZF_FLAG_2.getCode());
                XwsqZzsZfhxMapper xwsqZzsZfhxMapper = session.getMapper(XwsqZzsZfhxMapper.class);
                XwsqZzsZfhxExample kphxExample = new XwsqZzsZfhxExample();
                XwsqZzsZfhxExample.Criteria zfhxCriteria = kphxExample.createCriteria();
                zfhxCriteria.andInvoicecodeIn(unZuoFeiFphm); // 正向发票号
                zfhxCriteria.andFlagIn(flagList);            // 标识  0 未作废 1 已作废 2 作废失败
                List<XwsqZzsZfhx> xwsqZzsZfhxList = xwsqZzsZfhxMapper.selectByExample(kphxExample);
                InvZzsZfhx invZzsZfhx = null;
                InvZzsHead invZzsHead = null;
                for (XwsqZzsZfhx xwsqZzsZfhx : xwsqZzsZfhxList) {
                    // 插入本地INV_ZZS_ZFHX
                    invZzsZfhx = new InvZzsZfhx();
                    invZzsZfhx.setInvoicecode(xwsqZzsZfhx.getInvoicecode()); // 正向发票号
                    invZzsZfhx.setFlag(xwsqZzsZfhx.getFlag());               // 标识  1 已作废、冲红
                    invZzsZfhx.setKprq(xwsqZzsZfhx.getKprq());               // 作废、冲红日期
                    invZzsZfhx.setRelatedcode(xwsqZzsZfhx.getRelatedcode()); // 冲红发票发票号
                    invZzsZfhx.setViewurl(xwsqZzsZfhx.getViewurl());         // 冲红发票查看地址
                    invZzsZfhx.setTag1(xwsqZzsZfhx.getTag1());               // 预留字段1
                    invZzsZfhx.setTag2(xwsqZzsZfhx.getTag2());               // 预留字段2
                    invZzsZfhx.setMsg(xwsqZzsZfhx.getMsg());                 // 电子发票返回的报文
                    invZzsZfhx.setXrrq(xwsqZzsZfhx.getXrrq());               // 记录写入日期
                    invZzsZfhxMapper.insertSelective(invZzsZfhx);

                    // 更新本地销售发票头作废标志
                    InvZzsHeadExample headExample = new InvZzsHeadExample();
                    InvZzsHeadExample.Criteria headCriteria = headExample.createCriteria();
                    headCriteria.andFphmEqualTo(xwsqZzsZfhx.getInvoicecode()); // 正向发票号
                    invZzsHead = new InvZzsHead();
                    invZzsHead.setZfFlag(xwsqZzsZfhx.getFlag());               // 作废标志
                    invZzsHeadMapper.updateByExampleSelective(invZzsHead, headExample);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }
}
