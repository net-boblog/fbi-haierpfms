package skyline.common;

/**
 * Created by Hanjianlong on 2015/11/17.
 */
public class XORHelper {
    private static final String SEED = "guangdaNew20151118";

    public static String encrypt(String strMingWenPara) {
        if (strMingWenPara == null)
            return "";
        if (strMingWenPara.length() == 0)
            return "";
        byte[] bytesMingWen = strMingWenPara.getBytes();
        byte[] bytesSeed = SEED.getBytes();

        try {
            if (bytesMingWen.length > bytesSeed.length) {
                int j = 0;
                for (int i = 0; i < bytesMingWen.length; i++) {
                    bytesMingWen[i] = (byte) (bytesMingWen[i] ^ bytesSeed[j]);
                    j++;
                    if (j == bytesSeed.length) {
                        j = 0;
                    }
                }
            } else {
                for (int i = 0; i < bytesMingWen.length; i++) {
                    bytesMingWen[i] = (byte) (bytesMingWen[i] ^ bytesSeed[i]);
                }
            }
            return new String(bytesMingWen, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String args1[]) {
        System.out.println(" encrypt password is " + encrypt("ser_no=125367&http://12.7.0.23/hr_index.aspx?id=home"));
        System.out.println(" decrypt password is " + encrypt(encrypt("ser_no=125367&http://12.7.0.23/hr_index.aspx?id=home")));
    }

}
