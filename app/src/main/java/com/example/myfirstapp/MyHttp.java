/*package com.example.myfirstapp;


import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.StatusLine;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

import com.example.myfirstapp.UrlParam;

public class MyHttp {
	CloseableHttpClient httpclient;
	int timeout = 3000;
	CookieStore cookieStore = new BasicCookieStore();
	RequestConfig requestConfig;
	public MyHttp()
	{
		//return MyHttp(this.timeout);
		httpclient = HttpClients.custom()
				.setDefaultCookieStore(cookieStore)
				.build();
		
		requestConfig = RequestConfig.custom()
				.setSocketTimeout(timeout) 
				.setConnectTimeout(timeout) 
				.build();
	}
	
	public MyHttp(int timeout)
	{
		httpclient = HttpClients.custom()
				.setDefaultCookieStore(cookieStore)
				.build();
		
		requestConfig = RequestConfig.custom()
				.setSocketTimeout(timeout) 
				.setConnectTimeout(timeout) 
				.build();
	}
	
	public String post(String url,List<Object> datas)
	{

		HttpPost post=new HttpPost(url);

		post.setConfig(requestConfig);
		//post.s
		JSONArray arr=new JSONArray(datas);
		String a=arr.toString();
		
		return post(url,a);
	}
	
	public String post(String url,JSONArray arr)
	{
		String a=arr.toString();
		return post(url,a);
	}
	
	public String post(String url,String str)
	{
		HttpPost post=new HttpPost(url);
		post.setConfig(requestConfig);
		
		
		StringEntity entity=new StringEntity(str,"utf-8");
		entity.setContentType("text/plain");
		
		post.setEntity(entity);
		CloseableHttpResponse response=null ;
		try {
			 response = httpclient.execute(post);
		} catch (IOException e) {
			System.out.println("获取回复错误");
			e.printStackTrace();
		}
		
		StatusLine statusLine = response.getStatusLine();
		int status = statusLine.getStatusCode();
		if(status==200)
		{
			System.out.println("成功！！");
		}
		else {
			System.out.println("HTTP Error! Status=" + status);	
		}
		
		HttpEntity content=response.getEntity();
		String re=null;
		try {
			re=EntityUtils.toString(content);
		} catch (ParseException | IOException e) {
			System.out.println("HttpEntity 转化为String 错误");
			e.printStackTrace();
		}
		
		try {
			response.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		return re;
		
	}
	
	public String get(String url)
	{
		return get(url,null);
	}
	
	public String get(String url,UrlParam  params)
	{
		if(params!=null)
		{
			url+=params.toString();
		}
		HttpGet get=new HttpGet(url);
		get.setConfig(requestConfig);
		
		CloseableHttpResponse response =null;
		
		try {
			response= httpclient.execute(get);
		} catch (IOException e) {
			System.out.println("get请求执行错误！！");
			e.printStackTrace();
			return null;
		}
		
		
		StatusLine statusLine = response.getStatusLine();
		int status = statusLine.getStatusCode();
		if(status==200)
		{
			System.out.println("成功！！");
		}
		else {
				System.out.println("HTTP Error! Status=" + status);	
				return null;
		}
		
		HttpEntity dataRecv = response.getEntity();
		String content=null;
		try {
			content= EntityUtils.toString(dataRecv);
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			response.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		return content;
	}

}*/
