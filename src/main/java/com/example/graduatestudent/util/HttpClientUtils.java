package com.example.graduatestudent.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

@Slf4j
@Component
public class HttpClientUtils {


    @Autowired
    public com.example.graduatestudent.util.httpRequestUtil httpRequestUtil;
    @Autowired
    private ObjectMapper objectMapper;
    private String applicationJson = "application/json";
    private String applicationStr = "application/X-WWW-form-urlencoded";

    public static CloseableHttpClient acceptsUntrustedCertsHttpClient() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        HttpClientBuilder b = HttpClientBuilder.create();

        // setup a Trust Strategy that allows all certificates.
        //
        SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
            @Override
            public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                return true;
            }
        }).build();
        b.setSSLContext(sslContext);

        // don't check Hostnames, either.
        //      -- use SSLConnectionSocketFactory.getDefaultHostnameVerifier(), if you don't want to weaken
        HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;

        // here's the special part:
        //      -- need to create an SSL Socket Factory, to use our weakened "trust strategy";
        //      -- and create a Registry, to register it.
        //
        SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", sslSocketFactory)
                .build();

        // now, we create connection-manager using our Registry.
        //      -- allows multi-threaded use
        PoolingHttpClientConnectionManager connMgr = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        connMgr.setMaxTotal(200);
        connMgr.setDefaultMaxPerRoute(100);
        b.setConnectionManager(connMgr);

        // finally, build the HttpClient;
        //      -- done!
        CloseableHttpClient client = b.build();

        return client;
    }

    public String request(String url, int pageSize, int pageNum) {
        StringBuilder userUrl = new StringBuilder
                (url + "?school_name=&sort_type=1&school_type=-1&is_edu=-1&is_local=-1&is_center=-1")
                .append("&page=" + pageNum).append("&limit=" + pageSize);
        ObjectNode rootNode = objectMapper.createObjectNode();
        rootNode.put("notesId", "1001");
        rootNode.put("fex", "女");
        log.info(userUrl.toString());
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Content-Type", applicationJson);
            HttpEntity httpEntity = new HttpEntity(rootNode.toString(), headers);
            String result = httpRequestUtil.getRequest(userUrl.toString(), HttpMethod.GET, httpEntity, String.class);

            return result;
        } catch (Exception e) {
            log.error("调用接口失败:" + e.getMessage());
            return null;
        }
    }

    public String xueyuan(String url, Integer schoolId) {
        StringBuilder userUrl = new StringBuilder
                (url + schoolId + "/concact");
        ObjectNode rootNode = objectMapper.createObjectNode();
        log.info(userUrl.toString());
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Content-Type", applicationJson);
            HttpEntity httpEntity = new HttpEntity(rootNode.toString(), headers);
            String result = httpRequestUtil.getRequest(userUrl.toString(), HttpMethod.GET, httpEntity, String.class);

            return result;
        } catch (Exception e) {
            log.error("调用接口失败:" + e.getMessage());
            return null;
        }
    }

    public String study(String url, Integer schoolId, Integer sf, Integer fo, Integer math) {
        StringBuilder userUrl = new StringBuilder
                (url + "&school_id=" + schoolId + "&study_type=" + sf + "&foreign_type=" + fo + "&math_type=" + math);
        ObjectNode rootNode = objectMapper.createObjectNode();
        log.info(userUrl.toString());
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Content-Type", applicationJson);
            HttpEntity httpEntity = new HttpEntity(rootNode.toString(), headers);
            String result = httpRequestUtil.getRequest(userUrl.toString(), HttpMethod.GET, httpEntity, String.class);

            return result;
        } catch (Exception e) {
            log.error("调用接口失败:" + e.getMessage());
            return null;
        }
    }

    public String api(String url) {
        StringBuilder userUrl = new StringBuilder(url);
        ObjectNode rootNode = objectMapper.createObjectNode();
        log.info(userUrl.toString());
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Content-Type", applicationJson);
            HttpEntity httpEntity = new HttpEntity(rootNode.toString(), headers);
            String result = httpRequestUtil.getRequest(userUrl.toString(), HttpMethod.GET, httpEntity, String.class);

            return result;
        } catch (Exception e) {
            log.error("调用接口失败:" + e.getMessage());
            return null;
        }
    }


}