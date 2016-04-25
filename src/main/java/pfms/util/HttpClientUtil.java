package pfms.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * HttpClient������
 */
public class HttpClientUtil {
    private static Log logger = LogFactory.getLog(HttpClientUtil.class);

    public static byte[] doPost(String url, String requestXml) throws ClientProtocolException,
            IOException {
        logger.info("----------[HttpClientUtil-doPost]��ʼ��" + ToolUtil.getDateTimeDashColon() + "----------");
        byte[] response = null;

        // set the connection timeout value to 30 seconds (30000 milliseconds)
        final HttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 30000);
        HttpClient httpclient = new DefaultHttpClient(httpParams);
        HttpPost httppost = new HttpPost(url);

        httppost.setEntity(new StringEntity(requestXml));
        httppost.setHeader("Content-Type", "text/xml;charset=ISO8859-1");

        logger.info("ִ��: " + httppost.getRequestLine());

        HttpResponse httpResponse = httpclient.execute(httppost);
        HttpEntity httpEntity = httpResponse.getEntity();
        StatusLine statusLine = httpResponse.getStatusLine();// ����״̬��
        logger.info("----------[HttpClientUtil-doPost]����״̬��StatusCode=" + statusLine.getStatusCode() + "----------");
        if (statusLine.getStatusCode() >= 300) {
            logger.error("������" + statusLine.getReasonPhrase());
            return response;
        }
        // ����ֵ
        if (httpEntity != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            httpEntity.writeTo(baos);
            response = baos.toByteArray();
        }
        logger.info("----------[HttpClientUtil-doPost]������" + ToolUtil.getDateTimeDashColon() + "----------");
        return response;
    }
}
