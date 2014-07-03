package com.example.shadowduel;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

public class JSONActivity {
	
	private DefaultHttpClient httpClient;
	private HttpPost httpPost;

	public JSONActivity(){
		
	}
	
//    public String sendLoginAndGetJsonFromUrl(String url) {
//        
//    	String json = null;
// 
//        try {
//  			httpClient = new DefaultHttpClient();
//  			httpPost = new HttpPost(url);
//  			
//  			user = usernameView.getText().toString();
//  			pass = passwordView.getText().toString();
//
//  		    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
//  		    nameValuePairs.add(new BasicNameValuePair("username", user));
//  		    nameValuePairs.add(new BasicNameValuePair("password", pass));
//  		    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
//
//  		    // Execute HTTP Post Request
//  		    HttpResponse httpResponse = httpClient.execute(httpPost);
//            HttpEntity httpEntity = httpResponse.getEntity();
//            json = EntityUtils.toString(httpEntity);
// 
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return json;
//    }
//    
//    
//  public void fetchJSON(JSONArray jsonArray){
//
//      	try {
//      	   for (int i = 0; i < jsonArray.length(); i++) {
//      	       username = jsonArray.getJSONObject(i).getString("username");
//      	       password = jsonArray.getJSONObject(i).getString("password");
//      	    }
//      	} catch (Exception e) {
//      	     e.printStackTrace();
//      	}
//      	
//   }
//    
//  public static String md5(String string) {
//	    byte[] hash;
//
//	    try {
//	        hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
//	    } catch (NoSuchAlgorithmException e) {
//	        throw new RuntimeException("Huh, MD5 should be supported?", e);
//	    } catch (UnsupportedEncodingException e) {
//	        throw new RuntimeException("Huh, UTF-8 should be supported?", e);
//	    }
//
//	    StringBuilder hex = new StringBuilder(hash.length * 2);
//
//	    for (byte b : hash) {
//	        int i = (b & 0xFF);
//	        if (i < 0x10) hex.append('0');
//	        hex.append(Integer.toHexString(i));
//	    }
//
//	    return hex.toString();
//	}
}
