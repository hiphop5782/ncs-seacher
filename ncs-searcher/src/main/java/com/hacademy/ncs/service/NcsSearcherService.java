package com.hacademy.ncs.service;

import java.io.IOException;

import com.hacademy.ncs.http.response.NcsModuleCriteriaResponse;
import com.hacademy.ncs.http.response.NcsModuleDetailResponse;
import com.hacademy.ncs.http.response.NcsModuleListResponse;
import com.hacademy.ncs.http.response.NcsModuleSubListResponse;
import com.hacademy.ncs.http.response.NcsTreeListResponse;

public interface NcsSearcherService {
	NcsModuleListResponse getNcsModuleList(int page, int size) throws IOException;
	NcsModuleListResponse getNcsModuleList() throws IOException;
	NcsModuleSubListResponse getNcsModuleSubList(String dutyCd) throws IOException;
	NcsTreeListResponse getNcsModuleListAtJson() throws IOException;
	NcsModuleDetailResponse getNcsModuleDetail(String dutyCd, String compUnitCd) throws IOException;
	NcsModuleCriteriaResponse getNcsModuleCriteria(String dutyCd, String compUnitCd) throws IOException;
}
