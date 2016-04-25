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
 * HttpClient工具类
 */
public class HttpClientUtil {
    private static Log logger = LogFactory.getLog(HttpClientUtil.class);

    public static byte[] doPost(String url, String requestXml) throws ClientProtocolException,
            IOException {
        logger.info("----------[HttpClientUtil-doPost]开始：" + ToolUtil.getDateTimeDashColon() + "----------");
        byte[] response = null;

        // set the connection timeout value to 30 seconds (30000 milliseconds)
        final HttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 30000);
        HttpClient httpclient = new DefaultHttpClient(httpParams);
        HttpPost httppost = new HttpPost(url);

        httppost.setEntity(new StringEntity(requestXml));
        httppost.setHeader("Content-Type", "text/xml;charset=ISO8859-1");

        logger.info("执行: " + httppost.getRequestLine());

        HttpResponse httpResponse = httpclient.execute(httppost);
        HttpEntity httpEntity = httpResponse.getEntity();
        StatusLine statusLine = httpResponse.getStatusLine();// 返回状态码
        logger.info("----------[HttpClientUtil-doPost]返回状态码StatusCode=" + statusLine.getStatusCode() + "----------");
        if (statusLine.getStatusCode() >= 300) {
            logger.error("出错了" + statusLine.getReasonPhrase());
            return response;
        }
        // 返回值
        if (httpEntity != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            httpEntity.writeTo(baos);
            response = baos.toByteArray();
        }
        logger.info("----------[HttpClientUtil-doPost]结束：" + ToolUtil.getDateTimeDashColon() + "----------");
        return response;
    }
}
