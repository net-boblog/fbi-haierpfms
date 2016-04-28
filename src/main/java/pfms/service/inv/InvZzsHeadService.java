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
 * ��ֵ˰���۷�Ʊ
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
     * ��ѯ����Ʊ����
     *
     * @param customInvZzsHead
     * @return
     */
    public List<CustomInvZzsHead> selectUnPrint(CustomInvZzsHead customInvZzsHead) {
        return customMapper.selectUnPrint(customInvZzsHead);
    }

    /**
     * ��ѯ�ѿ�Ʊ����
     *
     * @param customInvZzsHead
     * @return
     */
    public List<CustomInvZzsHead> selectPrint(CustomInvZzsHead customInvZzsHead) {
        return customMapper.selectPrint(customInvZzsHead);
    }

    /**
     * ����
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
            // ���±������۷�Ʊͷ
            InvZzsHead invZzsHead = new InvZzsHead();
            invZzsHead.setXsddm(customInvZzsHead.getXsddm());       // ���ݺ���
            invZzsHead.setDmgs(customInvZzsHead.getDmgs());         // ���ݹ�˾
            invZzsHead.setZfFlag(EnuZzsZfFlag.ZF_FLAG_0.getCode()); // ���ϱ�־
            invZzsHead.setUpdDate(ToolUtil.getDateDash());          // �޸�����YYYY-MM-DD
            invZzsHead.setUpdTime(ToolUtil.getTimeColon());         // �޸�ʱ��HH:mm:ss
            invZzsHead.setUpdOperId(operId);                        // �޸���ID
            invZzsHeadMapper.updateByPrimaryKeySelective(invZzsHead);

            // ���뱾�����ϡ���췢Ʊ
            InvZzsZf invZzsZf = new InvZzsZf();
            invZzsZf.setInvoicecode(customInvZzsHead.getFphm()); // ����Ʊ��
            invZzsZf.setType(EnuZzsZfType.ZF_TYPE_1.getCode()); // ���� 1������  2 ���
            invZzsZf.setXrrq(new Date());                        // д��ʱ��
            invZzsZfMapper.insertSelective(invZzsZf);

            // ����Զ�����ϡ���췢Ʊ
            XwsqZzsZf xwsqZzsZf = new XwsqZzsZf();
            xwsqZzsZf.setInvoicecode(customInvZzsHead.getFphm()); // ����Ʊ��
            xwsqZzsZf.setType(EnuZzsZfType.ZF_TYPE_1.getCode()); // ���� 1������  2 ���
            xwsqZzsZf.setXrrq(new Date());                        // д��ʱ��
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
     * ��ѯ����������
     *
     * @param customInvZzsHead
     * @return
     */
    public List<CustomInvZzsHead> selectUnZuoFei(CustomInvZzsHead customInvZzsHead) {
        return customMapper.selectUnZuoFei(customInvZzsHead);
    }

    /**
     * ��ѯ����������
     *
     * @param customInvZzsHead
     * @return
     */
    public List<CustomInvZzsHead> selectZuoFei(CustomInvZzsHead customInvZzsHead) {
        return customMapper.selectZuoFei(customInvZzsHead);
    }

    /**
     * ͬ��Զ�����ݵ�����
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
            // ���ݴ���Ʊ���ݵĵ��ݺ����ѯԶ�̡�XWSQ_ZZS_HEAD��XWSQ_ZZS_KPHX���������
            if (unPrintXsddm.size() > 0) {
                // ��ѯԶ��XWSQ_ZZS_HEAD������
                XwsqZzsHeadMapper xwsqZzsHeadMapper = session.getMapper(XwsqZzsHeadMapper.class);
                XwsqZzsHeadExample example = new XwsqZzsHeadExample();
                XwsqZzsHeadExample.Criteria criteria = example.createCriteria();
                criteria.andXsddmIn(unPrintXsddm); // ���ݺ���
                criteria.andDmgsEqualTo(dmgs);     // ���ݹ�˾
                criteria.andFphmIsNotNull();       // ��Ʊ����
                List<XwsqZzsHead> xwsqZzsHeadList = xwsqZzsHeadMapper.selectByExample(example);
                InvZzsHead invZzsHead = null;
                for (XwsqZzsHead xwsqZzsHead : xwsqZzsHeadList) {
                    // ���±������۷�Ʊͷ
                    invZzsHead = new InvZzsHead();
                    invZzsHead.setXsddm(xwsqZzsHead.getXsddm());         // ���ݺ���
                    invZzsHead.setDmgs(xwsqZzsHead.getDmgs());           // ���ݹ�˾
                    invZzsHead.setFphm(xwsqZzsHead.getFphm());           // ��Ʊ����
                    invZzsHead.setMsg(xwsqZzsHead.getMsg());             // ���ӷ�Ʊ���ؽ����Ϣ
                    invZzsHead.setUrl(xwsqZzsHead.getUrl());             // ���ӷ�Ʊ���ص�URL
                    invZzsHead.setSkrq(xwsqZzsHead.getSkrq());           // ˰�ؿ�Ʊ����
                    invZzsHead.setXrrq(xwsqZzsHead.getXrrq());           // д��ʱ��
                    invZzsHead.setPzrq(xwsqZzsHead.getPzrq());
                    invZzsHead.setPzxx(xwsqZzsHead.getPzxx());
                    invZzsHead.setKpbz(xwsqZzsHead.getKpbz());
                    invZzsHead.setXrsj(xwsqZzsHead.getXrsj());
                    invZzsHead.setPzhxcs(xwsqZzsHead.getPzhxcs());
                    invZzsHead.setPzh(xwsqZzsHead.getPzh());
                    invZzsHead.setPzmessage(xwsqZzsHead.getPzmessage()); // ƾ֤������Ϣ
                    invZzsHead.setPzclbz(xwsqZzsHead.getPzclbz());       // ƾ֤�����־
                    invZzsHead.setTzdh(xwsqZzsHead.getTzdh());
                    invZzsHead.setChfph(xwsqZzsHead.getChfph());
                    invZzsHead.setClcs(xwsqZzsHead.getClcs());
                    invZzsHead.setKpFlag(EnuZzsKpFlag.KP_FLAG_1.getCode()); // ��Ʊ��־
                    invZzsHeadMapper.updateByPrimaryKeySelective(invZzsHead);
                }

                // ��ѯԶ��XWSQ_ZZS_KPHX������
                List<String> flagList = new ArrayList<String>();
                flagList.add(EnuZzsKpFlag.KP_FLAG_1.getCode());
                flagList.add(EnuZzsKpFlag.KP_FLAG_2.getCode());
                XwsqZzsKphxMapper xwsqZzsKphxMapper = session.getMapper(XwsqZzsKphxMapper.class);
                XwsqZzsKphxExample kphxExample = new XwsqZzsKphxExample();
                XwsqZzsKphxExample.Criteria kphxCriteria = kphxExample.createCriteria();
                kphxCriteria.andXsddmIn(unPrintXsddm); // ���ݺ���
                kphxCriteria.andDmgsEqualTo(dmgs);     // ���ݹ�˾
                kphxCriteria.andFlagIn(flagList);      // ��ʶ  0 δ��Ʊ 1 �ѿ�Ʊ 2 ��Ʊʧ��
                List<XwsqZzsKphx> xwsqZzsKphxList = xwsqZzsKphxMapper.selectByExample(kphxExample);
                InvZzsKphx invZzsKphx = null;
                InvZzsHeadKey invZzsHeadKey = null;
                InvZzsHead invZzsHeadSrc = null;
                InvZzsSrc invZzsSrc = null;
                for (XwsqZzsKphx xwsqZzsKphx : xwsqZzsKphxList) {
                    // ���뱾��INV_ZZS_KPHX
                    invZzsKphx = new InvZzsKphx();
                    invZzsKphx.setXsddm(xwsqZzsKphx.getXsddm());             // ���ݺ���
                    invZzsKphx.setFlag(xwsqZzsKphx.getFlag());               // ��ʶ  0 δ��Ʊ 1 �ѿ�Ʊ 2 ��Ʊʧ��
                    invZzsKphx.setInvoicecode(xwsqZzsKphx.getInvoicecode()); // ��Ʊ��
                    invZzsKphx.setKprq(xwsqZzsKphx.getKprq());               // ��Ʊ����
                    invZzsKphx.setFiscalcode(xwsqZzsKphx.getFiscalcode());   // ˰����
                    invZzsKphx.setNotaxamount(xwsqZzsKphx.getNotaxamount()); // ����˰�ܶ�
                    invZzsKphx.setTaxamount(xwsqZzsKphx.getTaxamount());     // ˰��
                    invZzsKphx.setTotalamount(xwsqZzsKphx.getTotalamount()); // ��˰�ܶ�
                    invZzsKphx.setDrawer(xwsqZzsKphx.getDrawer());           // ��Ʊ������
                    invZzsKphx.setViewurl(xwsqZzsKphx.getViewurl());         // ��Ʊ�鿴��ַ
                    invZzsKphx.setTag1(xwsqZzsKphx.getTag1());               // Ԥ���ֶ�1
                    invZzsKphx.setTag2(xwsqZzsKphx.getTag2());               // Ԥ���ֶ�2
                    invZzsKphx.setMsg(xwsqZzsKphx.getMsg());                 // ���ӷ�Ʊ���صı���
                    invZzsKphx.setDmgs(xwsqZzsKphx.getDmgs());               // ��˾
                    invZzsKphx.setXrrq(xwsqZzsKphx.getXrrq());               // ��¼д������
                    invZzsKphxMapper.insert(invZzsKphx);
                    // ��Ʊʧ�ܵĴ���
                    if (EnuZzsKpFlag.KP_FLAG_2.getCode().equals(xwsqZzsKphx.getFlag())) {
                        // ���±������۷�Ʊͷ��Ʊ��־Ϊ��Ʊʧ��
                        invZzsHead = new InvZzsHead();
                        invZzsHead.setXsddm(xwsqZzsKphx.getXsddm());         // ���ݺ���
                        invZzsHead.setDmgs(xwsqZzsKphx.getDmgs());           // ���ݹ�˾
                        invZzsHead.setKpFlag(EnuZzsKpFlag.KP_FLAG_2.getCode()); // ��Ʊ��־
                        invZzsHeadMapper.updateByPrimaryKeySelective(invZzsHead);

                        // ����ԭʼ���ݱ����־Ϊδ����ʹ�û������ٴδ�ӡ��Ʊ
                        // ������Ҫ���ݵ��ݺ��롢���ݹ�˾�ӡ�INV_ZZS_HEAD����ȡ����ˮ�š�������Դ
                        // Ȼ�������ˮ�š�������Դ���¡�INV_ZZS_SRC����Ĵ����־Ϊ��δ����
                        invZzsHeadKey = new InvZzsHeadKey();
                        invZzsHeadKey.setXsddm(xwsqZzsKphx.getXsddm());
                        invZzsHeadKey.setDmgs(xwsqZzsKphx.getDmgs());
                        invZzsHeadSrc = invZzsHeadMapper.selectByPrimaryKey(invZzsHeadKey);
                        invZzsSrc = new InvZzsSrc();
                        invZzsSrc.setFbtidx(invZzsHeadSrc.getFbtidx());
                        invZzsSrc.setDatasrc(invZzsHeadSrc.getDatasrc());
                        invZzsSrc.setProcFlag(EnuZzsProcFlag.PROC_FLAG_0.getCode()); // �����־
                        invZzsSrcMapper.updateByPrimaryKeySelective(invZzsSrc);
                    }
                }
            }
            // ���ݴ����Ϸ�Ʊ�����ѯԶ�̡�XWSQ_ZZS_ZF��XWSQ_ZZS_ZFHX���������
            if (unZuoFeiFphm.size() > 0) {
                // ��ѯԶ��XWSQ_ZZS_ZF������
                XwsqZzsZfMapper xwsqZzsZfMapper = session.getMapper(XwsqZzsZfMapper.class);
                XwsqZzsZfExample example = new XwsqZzsZfExample();
                XwsqZzsZfExample.Criteria criteria = example.createCriteria();
                criteria.andInvoicecodeIn(unZuoFeiFphm); // ����Ʊ��
                criteria.andClbzEqualTo(EnuZzsZfClbz.ZF_CLBZ_1.getCode()); // ϵͳ�����־(0�Ϳգ���ʾδ����1��ʾ�Ѵ���
                List<XwsqZzsZf> xwsqZzsZfList = xwsqZzsZfMapper.selectByExample(example);
                InvZzsZf invZzsZf = null;
                for (XwsqZzsZf xwsqZzsZf : xwsqZzsZfList) {
                    // ���±���INV_ZZS_ZF
                    invZzsZf = new InvZzsZf();
                    invZzsZf.setInvoicecode(xwsqZzsZf.getInvoicecode()); // ����Ʊ��
                    invZzsZf.setType(xwsqZzsZf.getType()); // ���� 1������  2 ���
                    invZzsZf.setTag1(xwsqZzsZf.getTag1()); // Ԥ���ֶ�1
                    invZzsZf.setTag2(xwsqZzsZf.getTag2()); // Ԥ���ֶ�2
                    invZzsZf.setMsg(xwsqZzsZf.getMsg());   // ���ӷ�Ʊ���صı�����Ϣ
                    invZzsZf.setClbz(xwsqZzsZf.getClbz()); // ϵͳ�����־(0�Ϳգ���ʾδ����1��ʾ�Ѵ���
                    invZzsZf.setSkrq(xwsqZzsZf.getSkrq());
                    invZzsZf.setClcs(xwsqZzsZf.getClcs());
                    invZzsZfMapper.updateByPrimaryKeySelective(invZzsZf);
                }

                // ��ѯԶ��XWSQ_ZZS_ZFHX������
                List<String> flagList = new ArrayList<String>();
                flagList.add(EnuZzsZfFlag.ZF_FLAG_1.getCode());
                flagList.add(EnuZzsZfFlag.ZF_FLAG_2.getCode());
                XwsqZzsZfhxMapper xwsqZzsZfhxMapper = session.getMapper(XwsqZzsZfhxMapper.class);
                XwsqZzsZfhxExample kphxExample = new XwsqZzsZfhxExample();
                XwsqZzsZfhxExample.Criteria zfhxCriteria = kphxExample.createCriteria();
                zfhxCriteria.andInvoicecodeIn(unZuoFeiFphm); // ����Ʊ��
                zfhxCriteria.andFlagIn(flagList);            // ��ʶ  0 δ���� 1 ������ 2 ����ʧ��
                List<XwsqZzsZfhx> xwsqZzsZfhxList = xwsqZzsZfhxMapper.selectByExample(kphxExample);
                InvZzsZfhx invZzsZfhx = null;
                InvZzsHead invZzsHead = null;
                for (XwsqZzsZfhx xwsqZzsZfhx : xwsqZzsZfhxList) {
                    // ���뱾��INV_ZZS_ZFHX
                    invZzsZfhx = new InvZzsZfhx();
                    invZzsZfhx.setInvoicecode(xwsqZzsZfhx.getInvoicecode()); // ����Ʊ��
                    invZzsZfhx.setFlag(xwsqZzsZfhx.getFlag());               // ��ʶ  1 �����ϡ����
                    invZzsZfhx.setKprq(xwsqZzsZfhx.getKprq());               // ���ϡ��������
                    invZzsZfhx.setRelatedcode(xwsqZzsZfhx.getRelatedcode()); // ��췢Ʊ��Ʊ��
                    invZzsZfhx.setViewurl(xwsqZzsZfhx.getViewurl());         // ��췢Ʊ�鿴��ַ
                    invZzsZfhx.setTag1(xwsqZzsZfhx.getTag1());               // Ԥ���ֶ�1
                    invZzsZfhx.setTag2(xwsqZzsZfhx.getTag2());               // Ԥ���ֶ�2
                    invZzsZfhx.setMsg(xwsqZzsZfhx.getMsg());                 // ���ӷ�Ʊ���صı���
                    invZzsZfhx.setXrrq(xwsqZzsZfhx.getXrrq());               // ��¼д������
                    invZzsZfhxMapper.insertSelective(invZzsZfhx);

                    // ���±������۷�Ʊͷ���ϱ�־
                    InvZzsHeadExample headExample = new InvZzsHeadExample();
                    InvZzsHeadExample.Criteria headCriteria = headExample.createCriteria();
                    headCriteria.andFphmEqualTo(xwsqZzsZfhx.getInvoicecode()); // ����Ʊ��
                    invZzsHead = new InvZzsHead();
                    invZzsHead.setZfFlag(xwsqZzsZfhx.getFlag());               // ���ϱ�־
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
