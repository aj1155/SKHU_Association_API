package kr.ac.skhu.service;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Manki Kim on 2017-01-24.
 */
@Service
public class HttpClientService {

    public void postRequest(String url,Map<String,Object> data){
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            //전달하고자 하는 PARAMETER를 List객체에 담는다
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("category",data.get("category").toString()));
            nvps.add(new BasicNameValuePair("dataType",data.get("dataType").toString()));
            nvps.add(new BasicNameValuePair("pk",data.get("pk").toString()));
            nvps.add(new BasicNameValuePair("dataStatus",data.get("dataStatus").toString()));
            //UTF-8은 한글
            httpPost.setEntity(new UrlEncodedFormEntity(nvps,"UTF-8"));
            CloseableHttpResponse response = httpclient.execute(httpPost);
            try {
                System.out.println(response.getStatusLine());
                //API서버로부터 받은 JSON 문자열 데이터
                System.out.println(EntityUtils.toString(response.getEntity()));
                HttpEntity entity = (HttpEntity) response.getEntity();
                EntityUtils.consume((org.apache.http.HttpEntity) entity);
            } finally {
                response.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
