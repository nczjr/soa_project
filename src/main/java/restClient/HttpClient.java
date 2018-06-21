package restClient;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.primefaces.json.JSONObject;

import java.io.IOException;

public class HttpClient {

  private static String url = "http://localhost:8080/web-client-1.0-SNAPSHOT/soa/catalog";

  public static void main(String[] args) throws IOException {
    org.apache.commons.httpclient.HttpClient client = new org.apache.commons.httpclient.HttpClient();

    String userPassword = "admin" + ":" + "admin";
    byte[] encodeBase64 = Base64.encodeBase64(userPassword.getBytes());
      executePost(client, new String(encodeBase64));
      executeGet(client, new String(encodeBase64));


  }

  public static void executeGet(org.apache.commons.httpclient.HttpClient client, String pass) throws IOException {
      GetMethod get = new GetMethod(url + "/1");
      get.addRequestHeader("Authorization", "BASIC " + pass);
      int statusCode = client.executeMethod(get);
      if (statusCode != HttpStatus.SC_OK) {
          System.err.println("Method failed: " + get.getStatusLine());
      }
      byte[] responseBody = get.getResponseBody();
      System.out.println(new String(responseBody));
}

  public static void executePost(org.apache.commons.httpclient.HttpClient client, String pass) throws IOException {
      PostMethod post = new PostMethod(url + "/1/56");
      post.addRequestHeader("Authorization", "BASIC " + pass);
      post.addRequestHeader("Accept", "application/json");
      post.addRequestHeader("Content-type", "application/json");
      JSONObject data=new JSONObject();
      data.put("name", "tymek");
      data.put("intValue1", "21");
      data.put("intValue2", "30");
      data.put("powerValue", "30");
      String jsonData=data.toString();
      StringRequestEntity entity = new StringRequestEntity(jsonData,"application/json", "UTF-8");

      post.setRequestEntity(entity);
      if (client.executeMethod(post) != HttpStatus.SC_OK) {
          System.err.println("Method failed: " + post.getStatusLine());
      }
      byte[] responseBody = post.getResponseBody();
      System.out.println(new String(responseBody));

  }
}