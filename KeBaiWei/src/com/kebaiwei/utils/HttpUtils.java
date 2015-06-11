package com.kebaiwei.utils;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

import com.kebaiwei.entity.User;

public class HttpUtils {
	public static void getNetData(String url) {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet request;
		try {
			request = new HttpGet(url);
			HttpResponse response = client.execute(request);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					String out = EntityUtils.toString(entity);
					JSONArray ja = new JSONArray(out);
					String data = ja.get(1).toString();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static String insertUserInfo(String url, User user) {
		String out = null;
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost httpPost;
		try {
			httpPost = new HttpPost(url);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("username", user.getUsername()));
			nvps.add(new BasicNameValuePair("pwd", user.getPwd()));
			nvps.add(new BasicNameValuePair("tel", user.getTel()));
			nvps.add(new BasicNameValuePair("email", user.getEmail()));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			HttpResponse response = client.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					out = EntityUtils.toString(entity);

				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return out;
	}

	public static String sendDataTOserverByPost(String actionUrl,
			Map<String, String> params) {
		HttpPost httpPost = new HttpPost(actionUrl);
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> entry : params.entrySet()) {// 构建表单字段内容
			list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(list, HTTP.UTF_8));
			System.out.println(httpPost.getURI().getPath());
			System.out.println(httpPost.getRequestLine().getUri());
			System.out.println(httpPost.getParams().getParameter("username")+"paramsname");
			HttpResponse httpResponse = new DefaultHttpClient()
					.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				return EntityUtils.toString(httpResponse.getEntity());
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return null;
	}
}
