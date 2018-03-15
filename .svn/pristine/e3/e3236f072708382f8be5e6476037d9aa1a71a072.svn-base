package com.hgicreate.rno.xdr.backend.web.rest.util;


import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;


public class HttpConnectUtil {

    private static CloseableHttpClient httpClient;

    private HttpConnectUtil(){}

    static {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(200);
        cm.setDefaultMaxPerRoute(20);
        cm.setDefaultMaxPerRoute(50);
        httpClient = HttpClients.custom().setConnectionManager(cm).build();
    }


    public static CloseableHttpClient getHttpClient(){
        return httpClient;
    }

}
