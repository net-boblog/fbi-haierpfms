package skyline.common;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * User: zhanrui
 * Date: 12-5-10
 */
public class MD5Helper {
    private static MessageDigest messagedigest = null;

    static {
        try {
            messagedigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            System.err.println(MD5Helper.class.getName() + "MD5初始失败!");
            throw new RuntimeException(e);
        }
    }

    public static String getMD5String(String s) {
        return getMD5String(s.getBytes());
    }

    public static String getMD5String(byte[] bytes) {
        messagedigest.update(bytes);
        byte[] tmp = messagedigest.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : tmp) {
            sb.append(Integer.toHexString(b & 0xff));
        }
        return sb.toString();
    }


    public static void encryptXor(byte[] srcBuf, byte[] keyBuf) {
        int keyIndex = 0 ;
        for(int srcIndex = 0; srcIndex < srcBuf.length ; srcIndex++) {
            srcBuf[srcIndex] = (byte)(srcBuf[srcIndex] ^ keyBuf[keyIndex]);
            keyIndex++;
            if (keyIndex == keyBuf.length){
                keyIndex = 0;
            }
        }
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, MalformedURLException {
        String md5 = MD5Helper.getMD5String("1111");
        System.out.println("pwd:1111  md5:" + md5);  //b59c67bf196a4758191e42f76670ceba
        md5 = MD5Helper.getMD5String("8888");
        System.out.println("pwd:8888  md5:" + md5);   //cf79ae6addba60ad018347359bd144d2
        md5 = MD5Helper.getMD5String("111111");
        System.out.println("pwd:111111  md5:" + md5);  //96e79218965eb72c92a549dd5a330112

        md5 = MD5Helper.getMD5String("9  1111999");  //dceb17e5e939471f33277dd0c4295bc3
        System.out.println("pwd:1111  md5:" + md5);


//        String key = "12111212121212生生世世1212";
        String key = "d但事实上事情我11212121212生生世世1212";
        String url = "9999=http://10.1.1.1/asdasdasd_1.asp?xxx=1&nnn=1212";
        byte[] urlBuf = url.getBytes();
        MD5Helper.encryptXor(urlBuf, key.getBytes());
        byte[] b64 = Base64.getEncoder().encode(urlBuf);
//        byte[] b64 = Base64.getUrlEncoder().encode(urlBuf);
        String b64Str = new String(b64);
        System.out.println(b64Str);
        b64Str = b64Str.replace("+", "-");
        b64Str = b64Str.replace("/", "*");
        System.out.println(b64Str);

        b64Str = b64Str.replace("-", "+");
        b64Str = b64Str.replace("*", "/");
        byte[] buf = Base64.getDecoder().decode(b64Str.getBytes());
        MD5Helper.encryptXor(buf, key.getBytes());
        System.out.println(new String(buf));

        URL u = new URL("http://10.7.88.7:80/fzyx/fzyx_main.aspx?id=home ");
        System.out.println(u.getHost());
        System.out.println(u.getProtocol());
        System.out.println(u.getPort());

        URL u1 = new URL(u.getProtocol(), u.getHost(), u.getPort(), "/aaa.asp");
        System.out.println(u1);

    }
}
