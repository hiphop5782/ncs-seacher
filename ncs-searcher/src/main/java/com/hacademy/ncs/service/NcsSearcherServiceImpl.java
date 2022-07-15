package com.hacademy.ncs.service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hacademy.ncs.configuration.JsonConfiguration;
import com.hacademy.ncs.http.HttpUtils;
import com.hacademy.ncs.http.response.NcsModuleCriteriaResponse;
import com.hacademy.ncs.http.response.NcsModuleDetailResponse;
import com.hacademy.ncs.http.response.NcsModuleListResponse;
import com.hacademy.ncs.http.response.NcsModuleSubListResponse;
import com.hacademy.ncs.http.response.NcsTreeListResponse;
import com.hacademy.ncs.property.CustomProperties;

import okhttp3.Response;

@Service
public class NcsSearcherServiceImpl implements NcsSearcherService{
	
	@Autowired
	private CustomProperties props;
	
	@Autowired
	private HttpUtils httpUtils;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private File directory;
	
	private void dutyCdCheck(String dutyCd) {
		if(dutyCd == null || dutyCd.length() != 8) throw new IllegalArgumentException("분류코드는 8자리로 작성되어야 합니다");
	}
	private void compUnitCd(String compUnitCd) {
		if(compUnitCd == null || compUnitCd.length() != 2) throw new IllegalArgumentException("세분류코드는 2자리로 작성되어야 합니다");
	}
	
	@Override
	public NcsModuleDetailResponse getNcsModuleDetail(String dutyCd, String compUnitCd) throws IOException {
		dutyCdCheck(dutyCd);
		compUnitCd(compUnitCd);
		
		String url = "https://www.ncs.go.kr/api/openapi4.do";
		Response response = httpUtils.get(url, 
				Map.of(
							"serviceKey", props.getServiceKey(),
							"returnType", "json",
							"dutyCd", dutyCd,
							"compUnitCd", compUnitCd,
							"pageNo", 1,
							"numOfRows", 100
						)
		);
		return mapper.readValue(response.body().string(), NcsModuleDetailResponse.class);
	}
	@Override
	public NcsModuleListResponse getNcsModuleList(int page, int size) throws IOException {
		String url = "https://www.ncs.go.kr/api/openapi1.do";
		Response response = httpUtils.get(url, 
				Map.of(
							"serviceKey", props.getServiceKey(),
							"returnType", "json",
							"pageNo", page,
							"numOfRows", size
						)
		);
		return mapper.readValue(response.body().string(), NcsModuleListResponse.class);
	}
	@Override
	public NcsModuleListResponse getNcsModuleList() throws IOException {
		int page = 1, size = 100;
		NcsModuleListResponse response = getNcsModuleList(page, size);
		int total = response.getDataInfo().getTotalPage();
		for(int i=page+1; i <= total; i++) {
			response.getData().addAll(getNcsModuleList(i, size).getData());
		}
		return response;
	}
	@Override
	public NcsTreeListResponse getNcsModuleListAtJson() throws IOException {
		File target = new File(directory, JsonConfiguration.MODULE_JSON);
		return mapper.readValue(target, NcsTreeListResponse.class);
	}
	@Override
	public NcsModuleCriteriaResponse getNcsModuleCriteria(String dutyCd, String compUnitCd) throws IOException {
		dutyCdCheck(dutyCd);
		compUnitCd(compUnitCd);
		
		String url = "https://www.ncs.go.kr/api/openapi5.do";
		Response response = httpUtils.get(url, 
				Map.of(
							"serviceKey", props.getServiceKey(),
							"returnType", "json",
							"dutyCd", dutyCd,
							"compUnitCd", compUnitCd,
							"pageNo", 1,
							"numOfRows", 100
						)
		);
		
		return mapper.readValue(response.body().string(), NcsModuleCriteriaResponse.class);
	}
	@Override
	public NcsModuleSubListResponse getNcsModuleSubList(String dutyCd) throws IOException {
		String url = "https://www.ncs.go.kr/api/openapi3.do";
		Response response = httpUtils.get(url, 
				Map.of(
							"serviceKey", props.getServiceKey(),
							"returnType", "json",
							"dutyCd", dutyCd,
							"pageNo", 1,
							"numOfRows", 100
						)
		);
		return mapper.readValue(response.body().string(), NcsModuleSubListResponse.class);
	}
}
