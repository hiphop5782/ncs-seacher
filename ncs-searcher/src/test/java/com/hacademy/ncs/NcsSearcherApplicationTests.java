package com.hacademy.ncs;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hacademy.ncs.http.HttpUtils;
import com.hacademy.ncs.http.response.NcsModuleCriteria;
import com.hacademy.ncs.http.response.NcsModuleCriteriaResponse;
import com.hacademy.ncs.http.response.NcsModuleDetail;
import com.hacademy.ncs.http.response.NcsModuleDetailResponse;
import com.hacademy.ncs.http.response.NcsModuleListItem;
import com.hacademy.ncs.http.response.NcsModuleListResponse;
import com.hacademy.ncs.property.CustomProperties;
import com.hacademy.ncs.service.NcsSearcherService;
import com.hacademy.ncs.service.schedule.ModuleLoadScheduler;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@SpringBootTest
class NcsSearcherApplicationTests {
	
	@Autowired
	private CustomProperties prop;
	
	OkHttpClient client = new OkHttpClient().newBuilder()
										.connectTimeout(10, TimeUnit.SECONDS)
										.readTimeout(30, TimeUnit.SECONDS)
										.build();

//	@Test
	void contextLoads() throws IOException {
		String url = "https://www.ncs.go.kr/api/openapi13.do";
		Request request = new Request.Builder().url(
				HttpUrl.parse(url).newBuilder()
					.addQueryParameter("serviceKey", prop.getServiceKey())
					.addQueryParameter("returnType", "json")
					.addQueryParameter("dutyCd", "02020202")
					.addQueryParameter("compUnitCd", "01")
				.build()
		).build();
		Response response = client.newCall(request).execute();
		System.out.println(response.body().string());
	}
	
	@Autowired
	private HttpUtils httpUtils;
	
//	@Test
	public void moduleTest() throws IOException {
		String url = "https://www.ncs.go.kr/api/openapi4.do";
		Response response = httpUtils.get(url, 
				Map.of(
							"serviceKey", prop.getServiceKey(),
							"returnType", "json",
							"dutyCd", "20010202",
							"compUnitCd", "01",
							"pageNo", 1,
							"numOfRows", 100
						)
		);
		System.out.println(response.body().string());
	}
	
	@Autowired
	private NcsSearcherService searchService;
	
//	@Test
	public void serviceTest() throws IOException {
		NcsModuleDetailResponse response = searchService.getNcsModuleDetail("20010202", "01");
		for(NcsModuleDetail detail : response) {
			System.out.println(detail.getCompUnitFactrName());
		}
	}
	
//	@Test
	public void list() throws IOException {
		NcsModuleListResponse response = searchService.getNcsModuleList();
		System.out.println(response.getDataInfo());
		for(NcsModuleListItem item : response) {
			System.out.println(item);
		}
	}
	
//	@Test
	public void creteria() throws IOException {
		NcsModuleCriteriaResponse response = searchService.getNcsModuleCriteria("20010202", "01");
		for(NcsModuleCriteria criteria : response) {
			System.out.println(criteria);
		}
	}
	
	@Autowired
	private ModuleLoadScheduler scheduler;
	
	@Test
	public void autoloadAndSave() throws IOException {
		scheduler.refresh();
	}

}
