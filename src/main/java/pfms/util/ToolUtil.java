package pfms.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ������
 */
public class ToolUtil {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * ������xml�ַ���ת��Ϊjava����
     *
     * @param xmlStr
     * @param cls    xml��Ӧ��class��
     * @param <T>    xml��Ӧ��class���ʵ������
     * @return
     */
    public static <T> T xmlToBean(String xmlStr, Class<T> cls) {
        XStream xStream = new XStream(new DomDriver());
        xStream.ignoreUnknownElements();
        xStream.processAnnotations(cls);
        T t = (T) xStream.fromXML(xmlStr);
        return t;
    }

    /**
     * ȡ��ϵͳ���ڴ�����
     *
     * @return
     */
    public static String getDateDash() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    /**
     * ȡ��ϵͳ����
     *
     * @return
     */
    public static String getDate() {
        return new SimpleDateFormat("yyyyMMdd").format(new Date());
    }

    /**
     * ȡ��ϵͳʱ��
     *
     * @return
     */
    public static String getTime() {
        return new SimpleDateFormat("HHmmss").format(new Date());
    }

    /**
     * ȡ��ϵͳʱ���ð��
     *
     * @return
     */
    public static String getTimeColon() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }

    /**
     * ȡ��ϵͳʱ���������
     *
     * @return
     */
    public static String getTimeMsec() {
        return new SimpleDateFormat("HHmmssSSS").format(new Date());
    }

    /**
     * ȡ��ϵͳ���ں�ʱ��
     *
     * @return
     */
    public static String getDateTime() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }

    /**
     * ȡ��ϵͳ���ں�ʱ��������ߺ�ð��
     *
     * @return
     */
    public static String getDateTimeDashColon() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    /**
     * ȡ��ϵͳ���ں�ʱ���������
     *
     * @return
     */
    public static String getDateTimeMsec() {
        return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
    }
}
