package com.sobonus.hackaton.Util;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AssetJSON {
public JSONObject LoadAssetFile(Context context, String fileName)
{// Reading text file from assets folder
	StringBuffer sb = new StringBuffer();
	BufferedReader br = null;
	try {
	br = new BufferedReader(new InputStreamReader(context.getAssets().open(fileName)));
	String temp;
	while ((temp = br.readLine()) != null)
	sb.append(temp);
	} catch (IOException e) {
	e.printStackTrace();
	} finally {
	try {
	br.close(); // stop reading
	} catch (IOException e) {
	e.printStackTrace();
	}
	}
	 
	String myjsonstring = sb.toString();
	JSONObject jObj = null;
	try {
		jObj = new JSONObject(myjsonstring);
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return jObj;
	
}
}
