package pfms.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 工具类
 */
public class ToolUtil {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 将传入xml字符串转化为java对象
     *
     * @param xmlStr
     * @param cls    xml对应的class类
     * @param <T>    xml对应的class类的实例对象
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
     * 取得系统日期带横线
     *
     * @return
     */
    public static String getDateDash() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    /**
     * 取得系统日期
     *
     * @return
     */
    public static String getDate() {
        return new SimpleDateFormat("yyyyMMdd").format(new Date());
    }

    /**
     * 取得系统时间
     *
     * @return
     */
    public static String getTime() {
        return new SimpleDateFormat("HHmmss").format(new Date());
    }

    /**
     * 取得系统时间带冒号
     *
     * @return
     */
    public static String getTimeColon() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }

    /**
     * 取得系统时间包含毫秒
     *
     * @return
     */
    public static String getTimeMsec() {
        return new SimpleDateFormat("HHmmssSSS").format(new Date());
    }

    /**
     * 取得系统日期和时间
     *
     * @return
     */
    public static String getDateTime() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }

    /**
     * 取得系统日期和时间包含横线和冒号
     *
     * @return
     */
    public static String getDateTimeDashColon() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    /**
     * 取得系统日期和时间包含毫秒
     *
     * @return
     */
    public static String getDateTimeMsec() {
        return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
    }
}
