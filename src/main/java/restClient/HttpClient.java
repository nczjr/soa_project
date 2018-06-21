package restClient;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.IOException;

public class HttpClient {

  private static String url = "http://localhost:8080/web-client-1.0-SNAPSHOT/soa/catalog/1";

  public static void main(String[] args) {
    org.apache.commons.httpclient.HttpClient client = new org.apache.commons.httpclient.HttpClient();

    GetMethod method = new GetMethod(url);
    String userPassword = "admin" + ":" + "admin";
    byte[] encodeBase64 = Base64.encodeBase64(userPassword.getBytes());
    method.addRequestHeader("Authorization", "BASIC " + new String(encodeBase64));

    try {
      int statusCode = client.executeMethod(method);

      if (statusCode != HttpStatus.SC_OK) {
        System.err.println("Method failed: " + method.getStatusLine());
      }

      byte[] responseBody = method.getResponseBody();
      System.out.println(new String(responseBody));

    } catch (HttpException e) {
      System.err.println("Fatal protocol violation: " + e.getMessage());
      e.printStackTrace();
    } catch (IOException e) {
      System.err.println("Fatal transport error: " + e.getMessage());
      e.printStackTrace();
    }  finally {
      method.releaseConnection();
    }  
  }
}