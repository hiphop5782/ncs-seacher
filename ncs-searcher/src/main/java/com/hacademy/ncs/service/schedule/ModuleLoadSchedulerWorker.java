package com.hacademy.ncs.service.schedule;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hacademy.ncs.configuration.JsonConfiguration;
import com.hacademy.ncs.http.response.NcsModuleListItem;
import com.hacademy.ncs.http.response.NcsModuleListResponse;
import com.hacademy.ncs.http.response.NcsModuleSubList;
import com.hacademy.ncs.http.response.NcsModuleSubListResponse;
import com.hacademy.ncs.http.response.NcsTreeListItem;
import com.hacademy.ncs.http.response.NcsTreeListResponse;
import com.hacademy.ncs.service.NcsSearcherService;

@Service
public class ModuleLoadSchedulerWorker implements ModuleLoadScheduler {
	
	@Autowired
	private NcsSearcherService ncsSearcherService;
	
	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private File directory;
	
	@PostConstruct
	public void init() throws IOException {
		refresh();
	}
	
	@Scheduled(cron = "0 0 0 * * *")
	@Override
	public void refresh() throws IOException {
		NcsModuleListResponse response = ncsSearcherService.getNcsModuleList();
		
		NcsTreeListResponse result = new NcsTreeListResponse(); 
		for(NcsModuleListItem module : response) {
			if(module.is("20")) {
				NcsModuleSubListResponse subResponse = ncsSearcherService.getNcsModuleSubList(module.code());
				for(NcsModuleSubList subModule : subResponse) {
					result.add(NcsTreeListItem.builder()
							.ncsDegr(module.getNcsDegr())
							.ncsLclasCd(module.getNcsLclasCd())
							.ncsLclasCdNm(module.getNcsLclasCdNm())
							.ncsMclasCd(module.getNcsMclasCd())
							.ncsMclasCdNm(module.getNcsMclasCdNm())
							.ncsSclasCd(module.getNcsSclasCd())
							.ncsSclasCdNm(module.getNcsSclasCdNm())
							.ncsSubdCd(module.getNcsSubdCd())
							.ncsSubdCdNm(module.getNcsSubdCdNm())
							.dutyCd(subModule.getDutyCd())
							.dutySvcNo(subModule.getDutySvcNo())
							.ncsClCd(subModule.getNcsClCd())
							.compUnitCd(subModule.getCompUnitCd())
							.compUnitName(subModule.getCompUnitName())
							.compUnitDef(subModule.getCompUnitDef())
							.compUnitLevel(subModule.getCompUnitLevel())
							.build());
				}
			}
		}
		File target = new File(directory, JsonConfiguration.MODULE_JSON);
		mapper.writeValue(target, result);
	}
	
} 




