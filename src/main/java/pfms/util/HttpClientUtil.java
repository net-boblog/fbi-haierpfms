package pfms.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import skyline.common.PropertyManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * HttpClient工具类
 */
public class HttpClientUtil {
    private static Log logger = LogFactory.getLog(HttpClientUtil.class);

    public static byte[] doPost(String url, Map<String, String> paramsMap) throws ClientProtocolException,
            IOException {
        logger.info("----------[HttpClientUtil-doPost]开始：" + ToolUtil.getDateTimeDashColon() + "----------");
        byte[] response = null;

        // set the connection timeout value to 30 seconds (30000 milliseconds)
        final HttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 30000);
        HttpClient httpclient = new DefaultHttpClient(httpParams);
        HttpPost httppost = new HttpPost(url);

        List<NameValuePair> nvps = getParamsList(paramsMap);// 设置参数
        httppost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));// 设置编码

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

    /**
     * 发送请求报文并解析响应报文
     *
     * @param tradeDate 交易日期
     * @return 响应报文xml
     */
    public static String exchange(String tradeDate) {
        String responseXml = "";
        try {
            String http_url_guojie = PropertyManager.getProperty("HTTP_URL_GUOJIE");
            Map<String, String> paramsMap = new HashMap<String, String>();
            paramsMap = new HashMap<String, String>();
            paramsMap.put("trade_date", tradeDate); // 交易日期
            byte[] response = HttpClientUtil.doPost(http_url_guojie, paramsMap);
            responseXml = new String(response, "utf-8");
        } catch (Exception e) {
            logger.error("发送请求报文并解析响应报文失败！" + e.getMessage() == null ? "" : e.getMessage());
        }
        return responseXml;
    }

    /**
     * 将传入的键/值对参数转换为NameValuePair参数集
     *
     * @param paramsMap 参数集, 键/值对
     * @return NameValuePair参数集
     */
    private static List<NameValuePair> getParamsList(Map<String, String> paramsMap) {
        if (paramsMap == null || paramsMap.size() == 0) {
            return new ArrayList<NameValuePair>();
        }
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> map : paramsMap.entrySet()) {
            params.add(new BasicNameValuePair(map.getKey(), map.getValue()));
        }
        if (params == null) {
            return new ArrayList<NameValuePair>();
        }
        return params;
    }
}
