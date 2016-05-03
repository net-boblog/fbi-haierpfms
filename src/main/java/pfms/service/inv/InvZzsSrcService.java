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
import pfms.repository.model.*;
import pfms.repository.model.custom.CustomInvZzsSrc;
import pfms.repository.model.custom.ResGuojie;
import pfms.repository.model.rwkj.XwsqZzsHead;
import pfms.repository.model.rwkj.XwsqZzsHeadExample;
import pfms.repository.model.rwkj.XwsqZzsItem;
import pfms.util.ToolUtil;
import skyline.common.MybatisFactory;
import skyline.common.PropertyManager;
import skyline.service.PlatformService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ��ֵ˰ԭʼ����ά��
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
    public void insertBySbs(String txnDate, List<T467.Bean> dataList) {
        InvZzsSrc invZzsSrc;
        String sysdate = ToolUtil.getDateDash();
        for (T467.Bean bean : dataList) {
            // ��֤��������ˮ�š�������Դ���Ƿ��ظ�
            if (isExist(bean.getFBTIDX(), EnuZzsSrc.SRC_00.getCode())) {
                continue;
            }

            invZzsSrc = new InvZzsSrc();
            invZzsSrc.setFbtidx(bean.getFBTIDX());                       // ��ˮ��
            invZzsSrc.setDatasrc(EnuZzsSrc.SRC_00.getCode());            // ������Դ
            invZzsSrc.setKhdm(bean.getCUSIDT());                         // �ͻ�����
            invZzsSrc.setCpdm(bean.getPRDCDE());                         // ��Ʒ����
            invZzsSrc.setCpmc(bean.getPRDNAM());                         // ��Ʒ����
            invZzsSrc.setXh(bean.getPRDTYP());                           // �ͺ�
            invZzsSrc.setCpdw(bean.getPRDUNT());                         // ��Ʒ��λ
            if (StringUtils.isNotEmpty(bean.getPRDCNT())) {
                invZzsSrc.setCpsl(new BigDecimal(bean.getPRDCNT()));     // ����
            }
            if (StringUtils.isNotEmpty(bean.getTOTPRI())) {
                invZzsSrc.setHsdj(new BigDecimal(bean.getTOTPRI()));     // ��˰����
            }
            if (StringUtils.isNotEmpty(bean.getTOTAMT())) {
                invZzsSrc.setHsje(new BigDecimal(bean.getTOTAMT()));     // ��˰���
            }
            if (StringUtils.isNotEmpty(bean.getCLNPRI())) {
                invZzsSrc.setXxdj(new BigDecimal(bean.getCLNPRI()));     // ����˰����
            }
            if (StringUtils.isNotEmpty(bean.getCLNAMT())) {
                invZzsSrc.setBhsje(new BigDecimal(bean.getCLNAMT()));    // ����˰���
            }
            if (StringUtils.isNotEmpty(bean.getTAXAMT())) {
                invZzsSrc.setSe(new BigDecimal(bean.getTAXAMT()));       // ˰��
            }
            if (StringUtils.isNotEmpty(bean.getTAXRAT())) {
                invZzsSrc.setSl(new BigDecimal(bean.getTAXRAT()));       // ˰��
            }
            if (StringUtils.isNotEmpty(bean.getOFCAMT())) {
                invZzsSrc.setZbhsje(new BigDecimal(bean.getOFCAMT()));   // �ۿ۲���˰���
            }
            if (StringUtils.isNotEmpty(bean.getOFCTAX())) {
                invZzsSrc.setZse(new BigDecimal(bean.getOFCTAX()));      // �ۿ�˰��
            }
            invZzsSrc.setCrtDate(sysdate);                               // ��������YYYY-MM-DD
            invZzsSrc.setCrtTime(ToolUtil.getTimeColon());               // ����ʱ��HH:mm:ss
            invZzsSrc.setCrtOperId("sbs");                               // ������ID
            invZzsSrc.setProcFlag(EnuZzsProcFlag.PROC_FLAG_0.getCode()); // �����־
            invZzsSrc.setTxnDate(txnDate);                               // ��������
            invZzsSrcMapper.insertSelective(invZzsSrc);
        }
    }

    @Transactional
    public void insertByGuojie(String txnDate, List<ResGuojie.Bean> dataList) {
        InvZzsSrc invZzsSrc;
        String sysdate = ToolUtil.getDateDash();
        for (ResGuojie.Bean bean : dataList) {
            // ��֤��������ˮ�š�������Դ���Ƿ��ظ�
            if (isExist(bean.getIBMSNO(), EnuZzsSrc.SRC_01.getCode())) {
                continue;
            }

            invZzsSrc = new InvZzsSrc();
            invZzsSrc.setFbtidx(bean.getIBMSNO());                             // ��ˮ��
            invZzsSrc.setDatasrc(EnuZzsSrc.SRC_01.getCode());                  // ������Դ
            invZzsSrc.setKhdm(bean.getCUS_NO());                               // �ͻ�����
            invZzsSrc.setCpdm(bean.getUPC_CODE());                             // ��Ʒ����
            invZzsSrc.setCpmc(bean.getUPC_NAME());                             // ��Ʒ����
            invZzsSrc.setXh(bean.getMODEL());                                  // �ͺ�
            invZzsSrc.setCpdw(bean.getPRODUCT_UNIT());                         // ��Ʒ��λ
            if (StringUtils.isNotEmpty(bean.getQTY())) {
                invZzsSrc.setCpsl(new BigDecimal(bean.getQTY()));              // ����
            }
            if (StringUtils.isNotEmpty(bean.getUNIT_PRICE())) {
                invZzsSrc.setHsdj(new BigDecimal(bean.getUNIT_PRICE()));       // ��˰����
            }
            if (StringUtils.isNotEmpty(bean.getAMOUNT())) {
                invZzsSrc.setHsje(new BigDecimal(bean.getAMOUNT()));           // ��˰���
            }
            if (StringUtils.isNotEmpty(bean.getNOUNIT_PRICE())) {
                invZzsSrc.setXxdj(new BigDecimal(bean.getNOUNIT_PRICE()));     // ����˰����
            }
            if (StringUtils.isNotEmpty(bean.getNOTAX_AMOUNT())) {
                invZzsSrc.setBhsje(new BigDecimal(bean.getNOTAX_AMOUNT()));    // ����˰���
            }
            if (StringUtils.isNotEmpty(bean.getTAX_AMOUNT())) {
                invZzsSrc.setSe(new BigDecimal(bean.getTAX_AMOUNT()));         // ˰��
            }
            if (StringUtils.isNotEmpty(bean.getTAX_RATE())) {
                invZzsSrc.setSl(new BigDecimal(bean.getTAX_RATE()));           // ˰��
            }
            if (StringUtils.isNotEmpty(bean.getDISCOUNT_NOTAX())) {
                invZzsSrc.setZbhsje(new BigDecimal(bean.getDISCOUNT_NOTAX())); // �ۿ۲���˰���
            }
            if (StringUtils.isNotEmpty(bean.getDISCOUNT_TAX())) {
                invZzsSrc.setZse(new BigDecimal(bean.getDISCOUNT_TAX()));      // �ۿ�˰��
            }
            invZzsSrc.setPayerName(bean.getPAYER_NAME());                      // ���������ƣ������ã�
            invZzsSrc.setCrtDate(sysdate);                                     // ��������YYYY-MM-DD
            invZzsSrc.setCrtTime(ToolUtil.getTimeColon());                     // ����ʱ��HH:mm:ss
            invZzsSrc.setCrtOperId("guojie");                                 // ������ID
            invZzsSrc.setProcFlag(EnuZzsProcFlag.PROC_FLAG_0.getCode());       // �����־
            invZzsSrc.setTxnDate(txnDate);                                     // ��������
            invZzsSrcMapper.insertSelective(invZzsSrc);
        }
    }

    /**
     * ��֤INV_ZZS_SRC����������ˮ�š�������Դ���Ƿ��ظ�
     *
     * @param fbtidx  ��ˮ��
     * @param datasrc ������Դ
     * @return
     */
    private boolean isExist(String fbtidx, String datasrc) {
        InvZzsSrcExample example = new InvZzsSrcExample();
        InvZzsSrcExample.Criteria criteria = example.createCriteria();
        criteria.andFbtidxEqualTo(fbtidx);
        criteria.andDatasrcEqualTo(datasrc);
        int count = invZzsSrcMapper.countByExample(example);
        if (count > 0) {
            logger.error("��������ˮ�š�������Դ���ظ�����ˮ�ţ�" + fbtidx +
                    " ������Դ��" + datasrc);
            return true;
        }
        return false;
    }

    /**
     * ��ѯ����������
     *
     * @param customInvZzsSrc
     * @return
     */
    public List<CustomInvZzsSrc> selectUnProc(CustomInvZzsSrc customInvZzsSrc) {
        return customMapper.selectUnProc(customInvZzsSrc);
    }

    /**
     * ��ӡ��Ʊ
     *
     * @param selectedRecords
     * @param fpzl
     * @param operId
     */
    @Transactional
    public Map<String, Integer> printFp(CustomInvZzsSrc[] selectedRecords, String fpzl, String operId) throws Exception {
        Map<String, Integer> rtn = new HashMap<String, Integer>();
        int totalCnt = 0;    // �ܱ���
        int successCnt = 0;  // �ɹ�����
        int errorCnt = 0;    // ʧ�ܱ���
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
            totalCnt = selectedRecords.length;
            String crtDate = ToolUtil.getDateDash();
            for (CustomInvZzsSrc record : selectedRecords) {
                String xsddm = ToolUtil.getDateTimeMsec() +
                        StringUtils.leftPad(String.valueOf(platformService.obtainSeqNo("xsddm")), 7, "0");

                // ��֤�������۷�Ʊͷ�Ƿ��ظ�
                InvZzsHeadExample example = new InvZzsHeadExample();
                InvZzsHeadExample.Criteria criteria = example.createCriteria();
                criteria.andXsddmEqualTo(xsddm);
                criteria.andDmgsEqualTo(dmgs);
                int count = invZzsHeadMapper.countByExample(example);
                if (count > 0) {
                    errorCnt++;
                    logger.error("INV_ZZS_HEAD�����������ݺ��롢���ݹ�˾���ظ������ݺ��룺" + xsddm +
                            " ���ݹ�˾��" + dmgs);
                    continue;
                }

                // ��֤Զ�����۷�Ʊͷ�Ƿ��ظ�
                XwsqZzsHeadExample xwsqExample = new XwsqZzsHeadExample();
                XwsqZzsHeadExample.Criteria xwsqCriteria = xwsqExample.createCriteria();
                xwsqCriteria.andXsddmEqualTo(xsddm);
                xwsqCriteria.andDmgsEqualTo(dmgs);
                count = xwsqZzsHeadMapper.countByExample(xwsqExample);
                if (count > 0) {
                    errorCnt++;
                    logger.error("XWSQ_ZZS_HEAD�����������ݺ��롢���ݹ�˾���ظ������ݺ��룺" + xsddm +
                            " ���ݹ�˾��" + dmgs);
                    continue;
                }

                // ���뱾�����۷�Ʊͷ
                invZzsHead = new InvZzsHead();
                invZzsHead.setFbtidx(record.getFbtidx());   // ��ˮ��
                invZzsHead.setDatasrc(record.getDatasrc()); // ������Դ
                invZzsHead.setXsddm(xsddm);                 // ���ݺ���
                invZzsHead.setDmgs(dmgs);                   // ���ݹ�˾
                invZzsHead.setKhdm(record.getKhdm());       // �ͻ�����
                invZzsHead.setKhswdjh(record.getKhswdjh()); // �ͻ�˰��
                invZzsHead.setKhmc(record.getKhmc());       // �ͻ�����
                invZzsHead.setKhsj(record.getKhsj());       // �ͻ��ֻ���
                if (StringUtils.isEmpty(record.getKhsj())) {
                    invZzsHead.setKhdz(record.getKhdz());   // �ͻ���ַ
                } else {
                    invZzsHead.setKhdz(record.getKhdz() + record.getKhsj());
                }
                invZzsHead.setKhyh(record.getKhyh() + record.getYhzh()); // ��������
                invZzsHead.setKprq(new Date());             // ��������
                invZzsHead.setFpzl(fpzl);                   // ��Ʊ���
                invZzsHead.setXrrq(new Date());             // д��ʱ��
                invZzsHead.setKpFlag(EnuZzsKpFlag.KP_FLAG_0.getCode()); // ��Ʊ��־
                invZzsHead.setCrtDate(crtDate);                 // ��������YYYY-MM-DD
                invZzsHead.setCrtTime(ToolUtil.getTimeColon()); // ����ʱ��HH:mm:ss
                invZzsHead.setCrtOperId(operId);                // ������ID
                invZzsHeadMapper.insert(invZzsHead);

                // ���뱾�����۷�Ʊ��ϸ
                invZzsItem = new InvZzsItem();
                invZzsItem.setXsddm(xsddm);                 // ���ݺ���
                invZzsItem.setDmgs(dmgs);                   // ���ݹ�˾
                invZzsItem.setMxxh(mxxh);                   // ��¼�к�
                invZzsItem.setCpdm(record.getCpdm());       // ��Ʒ����
                invZzsItem.setCpmc(record.getCpmc());       // ��Ʒ����
                invZzsItem.setXh(record.getXh());           // �ͺ�
                invZzsItem.setCpdw(record.getCpdw());       // ��Ʒ��λ
                invZzsItem.setCpsl(record.getCpsl());       // ����
                invZzsItem.setHsdj(record.getHsdj());       // ��˰����
                invZzsItem.setHsje(record.getHsje());       // ��˰���
                invZzsItem.setXxdj(record.getXxdj());       // ����˰����
                invZzsItem.setBhsje(record.getBhsje());     // ����˰���
                invZzsItem.setSe(record.getSe());           // ˰��
                invZzsItem.setSl(record.getSl());           // ˰��
                invZzsItem.setZbhsje(record.getZbhsje());   // �ۿ۲���˰���
                invZzsItem.setZse(record.getZse());         // �ۿ�˰��
                invZzsItemMapper.insert(invZzsItem);

                // ����Զ�����۷�Ʊͷ
                xwsqZzsHead = new XwsqZzsHead();
                xwsqZzsHead.setXsddm(xsddm);                 // ���ݺ���
                xwsqZzsHead.setDmgs(dmgs);                   // ���ݹ�˾
                xwsqZzsHead.setKhdm(record.getKhdm());       // �ͻ�����
                xwsqZzsHead.setKhswdjh(record.getKhswdjh()); // �ͻ�˰��
                xwsqZzsHead.setKhmc(record.getKhmc());       // �ͻ�����
                xwsqZzsHead.setKhsj(record.getKhsj());       // �ͻ��ֻ���
                if (StringUtils.isEmpty(record.getKhsj())) {
                    xwsqZzsHead.setKhdz(record.getKhdz());   // �ͻ���ַ
                } else {
                    xwsqZzsHead.setKhdz(record.getKhdz() + record.getKhsj());
                }
                xwsqZzsHead.setKhyh(record.getKhyh() + record.getYhzh()); // ��������
                xwsqZzsHead.setKprq(new Date());             // ��������
                xwsqZzsHead.setFpzl(fpzl);                   // ��Ʊ���
                xwsqZzsHead.setXrrq(new Date());             // д��ʱ��
                xwsqZzsHeadMapper.insert(xwsqZzsHead);

                // ����Զ�����۷�Ʊ��ϸ
                xwsqZzsItem = new XwsqZzsItem();
                xwsqZzsItem.setXsddm(xsddm);                 // ���ݺ���
                xwsqZzsItem.setDmgs(dmgs);                   // ���ݹ�˾
                xwsqZzsItem.setMxxh(mxxh);                   // ��¼�к�
                xwsqZzsItem.setCpdm(record.getCpdm());       // ��Ʒ����
                xwsqZzsItem.setCpmc(record.getCpmc());       // ��Ʒ����
                xwsqZzsItem.setXh(record.getXh());           // �ͺ�
                xwsqZzsItem.setCpdw(record.getCpdw());       // ��Ʒ��λ
                xwsqZzsItem.setCpsl(record.getCpsl());       // ����
                xwsqZzsItem.setHsdj(record.getHsdj());       // ��˰����
                xwsqZzsItem.setHsje(record.getHsje());       // ��˰���
                xwsqZzsItem.setXxdj(record.getXxdj());       // ����˰����
                xwsqZzsItem.setBhsje(record.getBhsje());     // ����˰���
                xwsqZzsItem.setSe(record.getSe());           // ˰��
                xwsqZzsItem.setSl(record.getSl());           // ˰��
                xwsqZzsItem.setZbhsje(record.getZbhsje());   // �ۿ۲���˰���
                xwsqZzsItem.setZse(record.getZse());         // �ۿ�˰��
                xwsqZzsItemMapper.insert(xwsqZzsItem);

                // ����ԭʼ���ݱ����־
                InvZzsSrc invZzsSrc = new InvZzsSrc();
                invZzsSrc.setFbtidx(record.getFbtidx());                     // ��ˮ��
                invZzsSrc.setDatasrc(record.getDatasrc());                   // ������Դ
                invZzsSrc.setUpdDate(sysdate);                               // �޸�����YYYY-MM-DD
                invZzsSrc.setUpdTime(ToolUtil.getTimeColon());               // �޸�ʱ��HH:mm:ss
                invZzsSrc.setUpdOperId(operId);                              // �޸���ID
                invZzsSrc.setProcFlag(EnuZzsProcFlag.PROC_FLAG_1.getCode()); // �����־
                invZzsSrcMapper.updateByPrimaryKeySelective(invZzsSrc);
                successCnt++;
            }
            session.commit();
        } catch (Exception e) {
            session.rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
        rtn.put("totalCnt", totalCnt);
        rtn.put("successCnt", successCnt);
        rtn.put("errorCnt", errorCnt);
        return rtn;
    }
}
