package com.sobonus.hackaton.Util;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JSONfunction {
	
	

public static JSONObject getJSONfromURL(String url){
	 InputStream is = null;
	 JSONObject jArray = null;
	 String result = "";
	
	try{
	HttpClient httpClient = new DefaultHttpClient();
	HttpPost httpPost = new HttpPost(url);
	//httpPost.setEntity(new UrlEncodedFormEntity(params));

	HttpResponse response = httpClient.execute(httpPost);
	HttpEntity entity = response.getEntity();
	is = entity.getContent();
	} catch (Exception e) {
		Log.e("log_tag", e.toString());
	} 
	
	
	try {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				is, "iso-8859-1"), 8);
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			sb.append(line + "\n");
		}
		is.close();
		
		result = sb.toString();
	} catch (Exception e) {
		Log.e("Buffer Error", "Error converting result " + e.toString());
	}
	try{
		jArray = new JSONObject(result);
		
	}catch(JSONException e){
		Log.e("log_tag ", e.toString());
		
	}
	return jArray;
}
}
