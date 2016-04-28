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
 * ������
 */
public class ToolUtil {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /* �ӹ���ȡ����ֵ˰���ݽӿ���Ӧ���� */
    public static final String RET_CODE_SUCCESS = "0000"; // ��Ӧ�루0000���ɹ���
    public static final String RESULT_SUCCESS = "B001";   // �����B001���ɹ���

    private static XStream xstream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out, new XmlFriendlyNameCoder("__", "_")) {
                // ������xml�ڵ��ת��������CDATA���
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
     * java����ת����xml�ַ���
     *
     * @param obj java����ʵ��
     * @return String xml�ַ���
     */
    public static String beanToXml(Object obj) {
        //ָ�����������,ֱ����jaxp dom������
        //XStream xstream = new XStream(new DomDriver("utf-8"));
        //���û����䣬xml�еĸ�Ԫ�ػ���<��.����>
        //����˵��ע�������û��Ч�����Ե�Ԫ���������������
        xstream.processAnnotations(obj.getClass());
        StringBuffer xmlstrbuf = new StringBuffer();
        xmlstrbuf.append("<?xml version=\"1.0\" encoding=\"GBK\"?>");
        //xmlstrbuf.append("\r\n");
        xmlstrbuf.append(xstream.toXML(obj));
        return xmlstrbuf.toString();
    }

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
