package com.hacademy.ncs.http;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Component
public class HttpUtils {
	
	@Autowired
	private OkHttpClient client;
	
	public HttpUrl createUrl(String url, Map<String, Object> params) {
		HttpUrl.Builder builder = HttpUrl.parse("http://"+url).newBuilder();
		for(Entry<String, Object> entry : params.entrySet()) {
			builder.addQueryParameter(entry.getKey(), entry.getValue().toString());
		}
		return builder.build(); 
	}
	public Request getRequest(String url, Map<String, Object> params) {
		return new Request.Builder().url(createUrl(url, params)).build();
	}
	
	public Response get(String url, Map<String, Object> params) throws IOException {
		return client.newCall(getRequest(url, params)).execute();
	}
	
}
