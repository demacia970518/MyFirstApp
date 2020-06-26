/*package com.example.myfirstapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

public class UrlParam {
	static List <NameValuePair> nvps = new ArrayList <>();
	static UrlParam i=new UrlParam();
	public static UrlParam create()
	{
		nvps=new ArrayList<>();
		return i;
	}
	public static   UrlParam addParam(String key,String value)
	{
		nvps.add(new BasicNameValuePair(key, value));
		return i;
	}
	@Override
	public String toString() {
		String query = URLEncodedUtils.format(nvps, "UTF-8");		
		return "?"+query;
	}
	
}*/
