package com.fzc.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class HttpUtils {

    private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    CloseableHttpClient httpClient = null;
    HttpPost httpPost = null;
    String responseBody = null;

    private static int SocketTimeout = 6000;//6秒
    private static int ConnectTimeout = 6000;//6秒
    private int status = 0;

    /**
     * @param url
     * @return
     * @throws Exception
     */
    public String doGet(String url, int socketTimeout, int connectTimeout) throws Exception {
        httpClient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet(url);
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(socketTimeout)
                .setConnectTimeout(connectTimeout).build();//设置请求和传输超时时间
        httpGet.setConfig(requestConfig);
        ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

            @Override
            public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            }

        };
        responseBody = httpClient.execute(httpGet, responseHandler);

        return responseBody;

    }

    public String doGet(String url) throws Exception {
        return doGet(url, ConnectTimeout, SocketTimeout);
    }

    /**
     * @param url     请求URL地址
     * @param map     参数集合
     * @param charset 编码方式
     * @return
     * @throws Exception
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public String doPost(String url, Map<String, Object> map, String charset, int socketTimeout, int connectTimeout) {
        httpClient = HttpClients.createDefault();
        try {
            httpPost = new HttpPost(url);

            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(socketTimeout)
                    .setConnectTimeout(connectTimeout).build();//设置请求和传输超时时间
            httpPost.setConfig(requestConfig);

            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            Iterator iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Entry<String, Object> elem = (Entry<String, Object>) iterator.next();
                nvps.add(new BasicNameValuePair(elem.getKey(), elem.getValue().toString()));
            }
            if (nvps.size() > 0) {
                UrlEncodedFormEntity entity = null;
                try {
                    entity = new UrlEncodedFormEntity(nvps, charset);
                } catch (UnsupportedEncodingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                httpPost.setEntity(entity);
            }

            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                @Override
                public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }

            };
            try {
                responseBody = httpClient.execute(httpPost, responseHandler);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return responseBody;

    }

    public String post(String url,Map<String, Object> map){
        httpClient = HttpClients.createDefault();
        try {
            httpPost = new HttpPost(url);

            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(SocketTimeout)
                    .setConnectTimeout(ConnectTimeout).build();//设置请求和传输超时时间
            httpPost.setConfig(requestConfig);

            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            Iterator<Entry<String, Object>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Entry<String, Object> elem = (Entry<String, Object>) iterator.next();
                nvps.add(new BasicNameValuePair(elem.getKey(), elem.getValue().toString()));
            }
            if (nvps.size() > 0) {
                UrlEncodedFormEntity entity = null;
                try {
                    entity = new UrlEncodedFormEntity(nvps, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                httpPost.setEntity(entity);
            }

            try {
                CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpPost);
                responseBody = convertStreamToString(closeableHttpResponse.getEntity().getContent());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return responseBody;
    }

    private String convertStreamToString(InputStream is) {
        StringBuilder sb1 = new StringBuilder();
        byte[] bytes = new byte[4096];
        int size = 0;

        try {
            while ((size = is.read(bytes)) > 0) {
                String str = new String(bytes, 0, size, "UTF-8");
                sb1.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb1.toString();
    }

    /**
     * 默认 UTF-8 编码请求
     *
     * @param url
     * @param map
     * @return
     */
    public String doPostUTF8(String url, Map<String, Object> map) throws Exception {
        return doPost(url, map, "utf-8", SocketTimeout, ConnectTimeout);

    }

    /**
     * @param url
     * @param map
     * @return 返回值 为 Map
     * @throws Exception
     */
    public Map<?, ?> doPostUTF8ReturnMap(String url, Map<String, Object> map) throws Exception {
        System.out.println("接口入参数据=========>" + map);
        String rs = doPostUTF8(url, map);
        System.out.println("接口返回原始数据=========>" + rs);
        return new ObjectMapper().readValue(rs, java.util.Map.class);

    }

    public Map<?, ?> doPostGBKReturnMap(String url, Map<String, Object> map) throws Exception {
        String rs = doPostUTF8(url, map);
        System.out.println("接口返回原始数据=========>" + rs);
        return new ObjectMapper().readValue(doPost(url, map, "GBK", SocketTimeout, ConnectTimeout), java.util.Map.class);

    }
    public Map<?, ?> postForJson(String url, Map<String, Object> map)  throws Exception{
        String body = null;
        String  parameters = new ObjectMapper().writeValueAsString(map);
        httpClient = HttpClients.createDefault();
        httpPost = new HttpPost(url);
        if (httpPost != null & parameters != null
                && !"".equals(parameters.trim())) {
            try {

                // 建立一个NameValuePair数组，用于存储欲传送的参数
                httpPost.addHeader("Content-type","application/json; charset=utf-8");
                httpPost.setHeader("Accept", "application/json");
                httpPost.setEntity(new StringEntity(parameters, Charset.forName("UTF-8")));
                long startTime = System.currentTimeMillis();

                HttpResponse response = httpClient.execute(httpPost);

                long endTime = System.currentTimeMillis();
                int statusCode = response.getStatusLine().getStatusCode();

                logger.info("入参:{},statusCode:{},调用API 花费时间(单位：毫秒):{}" ,parameters,statusCode,(endTime - startTime));
                if (statusCode != HttpStatus.SC_OK) {
                    logger.error("Method failed:" + response.getStatusLine());
                    status = 1;
                }

                // Read the response body
                body = EntityUtils.toString(response.getEntity());

            } catch (IOException e) {
                // 网络错误
                logger.info("调用接口异常：" + e);
                status = 3;
            } finally {
                logger.info("调用接口出参:{},状态:{}" ,body,status);
            }

        }
        return new ObjectMapper().readValue(body, java.util.Map.class);
    }


}
