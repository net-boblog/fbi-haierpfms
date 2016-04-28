package pfms.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 工具类
 */
public class ToolUtil {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /* 从国结取得增值税数据接口响应报文 */
    public static final String RET_CODE_SUCCESS = "0000"; // 响应码（0000：成功）
    public static final String RESULT_SUCCESS = "B001";   // 结果（B001：成功）

    private static XStream xstream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out, new XmlFriendlyNameCoder("__", "_")) {
                // 对所有xml节点的转换都增加CDATA标记
                boolean cdata = true;

                @SuppressWarnings("unchecked")
                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }

                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });

    /**
     * java对象转化成xml字符串
     *
     * @param obj java对象实例
     * @return String xml字符串
     */
    public static String beanToXml(Object obj) {
        //指定编码解析器,直接用jaxp dom来解释
        //XStream xstream = new XStream(new DomDriver("utf-8"));
        //如果没有这句，xml中的根元素会是<包.类名>
        //或者说：注解根本就没生效，所以的元素名就是类的属性
        xstream.processAnnotations(obj.getClass());
        StringBuffer xmlstrbuf = new StringBuffer();
        xmlstrbuf.append("<?xml version=\"1.0\" encoding=\"GBK\"?>");
        //xmlstrbuf.append("\r\n");
        xmlstrbuf.append(xstream.toXML(obj));
        return xmlstrbuf.toString();
    }

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
