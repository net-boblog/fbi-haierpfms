package pfms.repository.dao.custom;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pfms.enums.EnuZzsKpFlag;
import pfms.enums.EnuZzsPrintFlag;
import pfms.enums.EnuZzsProcFlag;
import pfms.enums.EnuZzsZfFlag;
import pfms.repository.model.custom.CustomInvZzsHead;
import pfms.repository.model.custom.CustomInvZzsSrc;

import java.util.Map;

/**
 * 自定义SQL
 */
public class CustomProvider {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 查询待处理数据
     *
     * @param parametersMap
     * @return
     */
    public String selectUnProc(Map<String, Object> parametersMap) {
        CustomInvZzsSrc customInvZzsSrc = (CustomInvZzsSrc) parametersMap.get("customInvZzsSrc");
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT");
        sql.append("    T1.FBTIDX, T1.DATASRC, T1.KHDM, T1.CPDM, T1.CPMC, T1.XH,");
        sql.append("    T1.CPDW, T1.CPSL, T1.HSDJ, T1.HSJE, T1.XXDJ, T1.BHSJE, T1.SE,");
        sql.append("    T1.SL, T1.ZBHSJE, T1.ZSE, T1.REMARKS,");
        sql.append("    T1.CRT_DATE crtDate,T1.CRT_TIME crtTime,T1.CRT_OPER_ID crtOperId,");
        sql.append("    T1.UPD_DATE updDate,T1.UPD_TIME updTime,T1.UPD_OPER_ID updOperId,");
        sql.append("    T1.PRINT_FLAG printFlag,T1.PROC_FLAG procFlag,T1.TXN_DATE txnDate,");
        sql.append("    DECODE(T1.DATASRC,'01',T1.PAYER_NAME,T2.KHMC) KHMC,");
        sql.append("    T2.KHSWDJH,T2.KHSJ,T2.KHDZ,T2.KHYH,T2.YHZH ");
        sql.append("FROM");
        sql.append("    INV_ZZS_SRC T1 LEFT JOIN INV_ZZS_CUST T2 ON T1.KHDM = T2.KHDM OR T1.PAYER_NAME = T2.KHMC ");
        sql.append("WHERE");
        sql.append("    T1.PROC_FLAG = '" + EnuZzsProcFlag.PROC_FLAG_0.getCode() + "' ");
        if (StringUtils.isNotEmpty(customInvZzsSrc.getTxnDateStart())) {
            sql.append("    AND T1.TXN_DATE >= '" + customInvZzsSrc.getTxnDateStart() + "' ");
        }
        if (StringUtils.isNotEmpty(customInvZzsSrc.getTxnDateEnd())) {
            sql.append("    AND T1.TXN_DATE <= '" + customInvZzsSrc.getTxnDateEnd() + "' ");
        }
        if (StringUtils.isNotEmpty(customInvZzsSrc.getKhmc())) {
            sql.append("    AND T2.KHMC like '%" + customInvZzsSrc.getKhmc() + "%' ");
        }
        if (StringUtils.isNotEmpty(customInvZzsSrc.getFbtidx())) {
            sql.append("    AND T1.FBTIDX like '%" + customInvZzsSrc.getFbtidx() + "%' ");
        }
        if (StringUtils.isNotEmpty(customInvZzsSrc.getDatasrc())) {
            sql.append("    AND T1.DATASRC = '" + customInvZzsSrc.getDatasrc() + "' ");
        }
        if (StringUtils.isNotEmpty(customInvZzsSrc.getPrintFlag())) {
            sql.append("    AND T1.PRINT_FLAG = '" + customInvZzsSrc.getPrintFlag() + "' ");
        }
        sql.append("ORDER BY");
        sql.append("    TXN_DATE DESC, DATASRC");

        logger.info(sql.toString());
        return sql.toString();
    }

    /**
     * 查询不开票的数据
     *
     * @param parametersMap
     * @return
     */
    public String selectNoPrint(Map<String, Object> parametersMap) {
        CustomInvZzsSrc customInvZzsSrc = (CustomInvZzsSrc) parametersMap.get("customInvZzsSrc");
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT");
        sql.append("    T1.FBTIDX, T1.DATASRC, T1.KHDM, T1.CPDM, T1.CPMC, T1.XH,");
        sql.append("    T1.CPDW, T1.CPSL, T1.HSDJ, T1.HSJE, T1.XXDJ, T1.BHSJE, T1.SE,");
        sql.append("    T1.SL, T1.ZBHSJE, T1.ZSE, T1.REMARKS,");
        sql.append("    T1.CRT_DATE crtDate,T1.CRT_TIME crtTime,T1.CRT_OPER_ID crtOperId,");
        sql.append("    T1.UPD_DATE updDate,T1.UPD_TIME updTime,T1.UPD_OPER_ID updOperId,");
        sql.append("    T1.PRINT_FLAG printFlag,T1.PROC_FLAG procFlag,T1.TXN_DATE txnDate,");
        sql.append("    DECODE(T1.DATASRC,'01',T1.PAYER_NAME,T2.KHMC) KHMC,");
        sql.append("    T2.KHSWDJH,T2.KHSJ,T2.KHDZ,T2.KHYH,T2.YHZH ");
        sql.append("FROM");
        sql.append("    INV_ZZS_SRC T1 LEFT JOIN INV_ZZS_CUST T2 ON T1.KHDM = T2.KHDM OR T1.PAYER_NAME = T2.KHMC ");
        sql.append("WHERE");
        sql.append("    T1.PROC_FLAG = '" + EnuZzsProcFlag.PROC_FLAG_1.getCode() + "' ");
        sql.append("    AND T1.PRINT_FLAG = '" + EnuZzsPrintFlag.PRINT_FLAG_0.getCode() + "' ");
        if (StringUtils.isNotEmpty(customInvZzsSrc.getTxnDateStart())) {
            sql.append("    AND T1.TXN_DATE >= '" + customInvZzsSrc.getTxnDateStart() + "' ");
        }
        if (StringUtils.isNotEmpty(customInvZzsSrc.getTxnDateEnd())) {
            sql.append("    AND T1.TXN_DATE <= '" + customInvZzsSrc.getTxnDateEnd() + "' ");
        }
        if (StringUtils.isNotEmpty(customInvZzsSrc.getKhmc())) {
            sql.append("    AND T2.KHMC like '%" + customInvZzsSrc.getKhmc() + "%' ");
        }
        if (StringUtils.isNotEmpty(customInvZzsSrc.getFbtidx())) {
            sql.append("    AND T1.FBTIDX like '%" + customInvZzsSrc.getFbtidx() + "%' ");
        }
        if (StringUtils.isNotEmpty(customInvZzsSrc.getDatasrc())) {
            sql.append("    AND T1.DATASRC = '" + customInvZzsSrc.getDatasrc() + "' ");
        }
        if (StringUtils.isNotEmpty(customInvZzsSrc.getPrintFlag())) {
            sql.append("    AND T1.PRINT_FLAG = '" + customInvZzsSrc.getPrintFlag() + "' ");
        }
        sql.append("ORDER BY");
        sql.append("    TXN_DATE DESC, DATASRC");

        logger.info(sql.toString());
        return sql.toString();
    }

    /**
     * 查询待开票数据
     *
     * @param parametersMap
     * @return
     */
    public String selectUnPrint(Map<String, Object> parametersMap) {
        CustomInvZzsHead customInvZzsHead = (CustomInvZzsHead) parametersMap.get("customInvZzsHead");
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT");
        sql.append("    T1.*, T2.* ");
        sql.append("FROM");
        sql.append("    INV_ZZS_HEAD T1, INV_ZZS_ITEM T2 ");
        sql.append("WHERE");
        sql.append("    T1.XSDDM = T2.XSDDM ");
        sql.append("    AND T1.DMGS = T2.DMGS ");
        if (StringUtils.isNotEmpty(customInvZzsHead.getKhmc())) {
            sql.append("    AND T1.KHMC like '%" + customInvZzsHead.getKhmc() + "%' ");
        }
        if (StringUtils.isNotEmpty(customInvZzsHead.getFbtidx())) {
            sql.append("    AND T1.FBTIDX like '%" + customInvZzsHead.getFbtidx() + "%' ");
        }
        if (StringUtils.isNotEmpty(customInvZzsHead.getDatasrc())) {
            sql.append("    AND T1.DATASRC = '" + customInvZzsHead.getDatasrc() + "' ");
        }
        if (StringUtils.isNotEmpty(customInvZzsHead.getFpzl())) {
            sql.append("    AND T1.FPZL = '" + customInvZzsHead.getFpzl() + "' ");
        }
        sql.append("    AND T1.KP_FLAG = '" + EnuZzsKpFlag.KP_FLAG_0.getCode() + "' ");
        sql.append("    AND T1.ZF_FLAG IS NULL ");
        sql.append("    AND T1.CH_FLAG IS NULL ");
        sql.append("ORDER BY");
        sql.append("    KPRQ desc");

        logger.info(sql.toString());
        return sql.toString();
    }

    /**
     * 查询已开票数据
     *
     * @param parametersMap
     * @return
     */
    public String selectPrint(Map<String, Object> parametersMap) {
        CustomInvZzsHead customInvZzsHead = (CustomInvZzsHead) parametersMap.get("customInvZzsHead");
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT");
        sql.append("    T1.*, T2.*, T3.INVOICECODE, T3.KPRQ KPHXRQ ");
        sql.append("FROM");
        sql.append("    INV_ZZS_HEAD T1, INV_ZZS_ITEM T2, INV_ZZS_KPHX T3 ");
        sql.append("WHERE");
        sql.append("    T1.XSDDM = T2.XSDDM ");
        sql.append("    AND T1.DMGS = T2.DMGS ");
        sql.append("    AND T1.XSDDM = T3.XSDDM ");
        sql.append("    AND T1.DMGS = T3.DMGS ");

        if (StringUtils.isNotEmpty(customInvZzsHead.getKprqStart())) {
            sql.append("    AND TO_CHAR(T1.KPRQ, 'yyyy-MM-dd') >= '" + customInvZzsHead.getKprqStart() + "' ");
        }
        if (StringUtils.isNotEmpty(customInvZzsHead.getKprqEnd())) {
            sql.append("    AND TO_CHAR(T1.KPRQ, 'yyyy-MM-dd') <= '" + customInvZzsHead.getKprqEnd() + "' ");
        }
        if (StringUtils.isNotEmpty(customInvZzsHead.getKhmc())) {
            sql.append("    AND T1.KHMC like '%" + customInvZzsHead.getKhmc() + "%' ");
        }
        if (StringUtils.isNotEmpty(customInvZzsHead.getFbtidx())) {
            sql.append("    AND T1.FBTIDX like '%" + customInvZzsHead.getFbtidx() + "%' ");
        }
        if (StringUtils.isNotEmpty(customInvZzsHead.getDatasrc())) {
            sql.append("    AND T1.DATASRC = '" + customInvZzsHead.getDatasrc() + "' ");
        }
        if (StringUtils.isNotEmpty(customInvZzsHead.getFpzl())) {
            sql.append("    AND T1.FPZL = '" + customInvZzsHead.getFpzl() + "' ");
        }
        sql.append("    AND T1.KP_FLAG = '" + EnuZzsKpFlag.KP_FLAG_1.getCode() + "' ");
        sql.append("    AND T1.ZF_FLAG IS NULL ");
        sql.append("    AND T1.CH_FLAG IS NULL ");
        sql.append("ORDER BY");
        sql.append("    T1.KPRQ desc");

        logger.info(sql.toString());
        return sql.toString();
    }

    /**
     * 查询已开票失败数据
     *
     * @param parametersMap
     * @return
     */
    public String selectPrintFail(Map<String, Object> parametersMap) {
        CustomInvZzsHead customInvZzsHead = (CustomInvZzsHead) parametersMap.get("customInvZzsHead");
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT");
        sql.append("    T1.*, T2.* ");
        sql.append("FROM");
        sql.append("    INV_ZZS_HEAD T1, INV_ZZS_ITEM T2 ");
        sql.append("WHERE");
        sql.append("    T1.XSDDM = T2.XSDDM ");
        sql.append("    AND T1.DMGS = T2.DMGS ");

        if (StringUtils.isNotEmpty(customInvZzsHead.getKprqStart())) {
            sql.append("    AND TO_CHAR(T1.KPRQ, 'yyyy-MM-dd') >= '" + customInvZzsHead.getKprqStart() + "' ");
        }
        if (StringUtils.isNotEmpty(customInvZzsHead.getKprqEnd())) {
            sql.append("    AND TO_CHAR(T1.KPRQ, 'yyyy-MM-dd') <= '" + customInvZzsHead.getKprqEnd() + "' ");
        }
        if (StringUtils.isNotEmpty(customInvZzsHead.getKhmc())) {
            sql.append("    AND T1.KHMC like '%" + customInvZzsHead.getKhmc() + "%' ");
        }
        if (StringUtils.isNotEmpty(customInvZzsHead.getFbtidx())) {
            sql.append("    AND T1.FBTIDX like '%" + customInvZzsHead.getFbtidx() + "%' ");
        }
        if (StringUtils.isNotEmpty(customInvZzsHead.getDatasrc())) {
            sql.append("    AND T1.DATASRC = '" + customInvZzsHead.getDatasrc() + "' ");
        }
        if (StringUtils.isNotEmpty(customInvZzsHead.getFpzl())) {
            sql.append("    AND T1.FPZL = '" + customInvZzsHead.getFpzl() + "' ");
        }
        sql.append("    AND T1.KP_FLAG = '" + EnuZzsKpFlag.KP_FLAG_2.getCode() + "' ");
        sql.append("    AND T1.ZF_FLAG IS NULL ");
        sql.append("    AND T1.CH_FLAG IS NULL ");
        sql.append("ORDER BY");
        sql.append("    T1.KPRQ desc");

        logger.info(sql.toString());
        return sql.toString();
    }

    /**
     * 查询待作废数据
     *
     * @param parametersMap
     * @return
     */
    public String selectUnZuoFei(Map<String, Object> parametersMap) {
        CustomInvZzsHead customInvZzsHead = (CustomInvZzsHead) parametersMap.get("customInvZzsHead");
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT");
        sql.append("    T1.*, T2.*, T3.INVOICECODE, T3.KPRQ KPHXRQ ");
        sql.append("FROM");
        sql.append("    INV_ZZS_HEAD T1, INV_ZZS_ITEM T2, INV_ZZS_KPHX T3 ");
        sql.append("WHERE");
        sql.append("    T1.XSDDM = T2.XSDDM ");
        sql.append("    AND T1.DMGS = T2.DMGS ");
        sql.append("    AND T1.XSDDM = T3.XSDDM ");
        sql.append("    AND T1.DMGS = T3.DMGS ");
        if (StringUtils.isNotEmpty(customInvZzsHead.getKhmc())) {
            sql.append("    AND T1.KHMC like '%" + customInvZzsHead.getKhmc() + "%' ");
        }
        if (StringUtils.isNotEmpty(customInvZzsHead.getFbtidx())) {
            sql.append("    AND T1.FBTIDX like '%" + customInvZzsHead.getFbtidx() + "%' ");
        }
        if (StringUtils.isNotEmpty(customInvZzsHead.getDatasrc())) {
            sql.append("    AND T1.DATASRC = '" + customInvZzsHead.getDatasrc() + "' ");
        }
        if (StringUtils.isNotEmpty(customInvZzsHead.getFpzl())) {
            sql.append("    AND T1.FPZL = '" + customInvZzsHead.getFpzl() + "' ");
        }
        sql.append("    AND T1.KP_FLAG = '" + EnuZzsKpFlag.KP_FLAG_1.getCode() + "' ");
        sql.append("    AND T1.ZF_FLAG = '" + EnuZzsZfFlag.ZF_FLAG_0.getCode() + "' ");
        sql.append("    AND T1.CH_FLAG IS NULL ");
        sql.append("ORDER BY");
        sql.append("    T1.KPRQ desc");

        logger.info(sql.toString());
        return sql.toString();
    }

    /**
     * 查询已作废数据
     *
     * @param parametersMap
     * @return
     */
    public String selectZuoFei(Map<String, Object> parametersMap) {
        CustomInvZzsHead customInvZzsHead = (CustomInvZzsHead) parametersMap.get("customInvZzsHead");
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT");
        sql.append("    T1.*, T2.*, T3.INVOICECODE, T3.KPRQ KPHXRQ ");
        sql.append("FROM");
        sql.append("    INV_ZZS_HEAD T1, INV_ZZS_ITEM T2, INV_ZZS_KPHX T3 ");
        sql.append("WHERE");
        sql.append("    T1.XSDDM = T2.XSDDM ");
        sql.append("    AND T1.DMGS = T2.DMGS ");
        sql.append("    AND T1.XSDDM = T3.XSDDM ");
        sql.append("    AND T1.DMGS = T3.DMGS ");

        if (StringUtils.isNotEmpty(customInvZzsHead.getKprqStart())) {
            sql.append("    AND TO_CHAR(T1.KPRQ, 'yyyy-MM-dd') >= '" + customInvZzsHead.getKprqStart() + "' ");
        }
        if (StringUtils.isNotEmpty(customInvZzsHead.getKprqEnd())) {
            sql.append("    AND TO_CHAR(T1.KPRQ, 'yyyy-MM-dd') <= '" + customInvZzsHead.getKprqEnd() + "' ");
        }
        if (StringUtils.isNotEmpty(customInvZzsHead.getKhmc())) {
            sql.append("    AND T1.KHMC like '%" + customInvZzsHead.getKhmc() + "%' ");
        }
        if (StringUtils.isNotEmpty(customInvZzsHead.getFbtidx())) {
            sql.append("    AND T1.FBTIDX like '%" + customInvZzsHead.getFbtidx() + "%' ");
        }
        if (StringUtils.isNotEmpty(customInvZzsHead.getDatasrc())) {
            sql.append("    AND T1.DATASRC = '" + customInvZzsHead.getDatasrc() + "' ");
        }
        if (StringUtils.isNotEmpty(customInvZzsHead.getFpzl())) {
            sql.append("    AND T1.FPZL = '" + customInvZzsHead.getFpzl() + "' ");
        }
        sql.append("    AND T1.KP_FLAG = '" + EnuZzsKpFlag.KP_FLAG_1.getCode() + "' ");
        sql.append("    AND (T1.ZF_FLAG = '" + EnuZzsZfFlag.ZF_FLAG_1.getCode() + "' OR ");
        sql.append("         T1.ZF_FLAG = '" + EnuZzsZfFlag.ZF_FLAG_2.getCode() + "') ");
        sql.append("    AND T1.CH_FLAG IS NULL ");
        sql.append("ORDER BY");
        sql.append("    T1.KPRQ desc");

        logger.info(sql.toString());
        return sql.toString();
    }
}
