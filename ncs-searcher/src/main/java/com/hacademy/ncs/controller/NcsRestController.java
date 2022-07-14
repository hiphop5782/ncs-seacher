package com.hacademy.ncs.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.hacademy.ncs.http.response.NcsModuleCriteriaResponse;
import com.hacademy.ncs.http.response.NcsModuleDetailResponse;
import com.hacademy.ncs.http.response.NcsModuleListItem;
import com.hacademy.ncs.service.NcsSearcherService;

@CrossOrigin
@RestController
public class NcsRestController {
	
	@Autowired
	private NcsSearcherService ncsSearcherService;
	
	@GetMapping("/")
	public List<NcsModuleListItem> all() throws IOException{
		return ncsSearcherService.getNcsModuleList().getData();
	}
	
	@GetMapping("/detail/{main}/{sub}")
	public NcsModuleDetailResponse detail(@PathVariable String main, @PathVariable String sub) throws IOException {
		return ncsSearcherService.getNcsModuleDetail(main, sub);
	}
	
	@GetMapping("/criteria/{main}/{sub}")
	public NcsModuleCriteriaResponse criteria(@PathVariable String main, @PathVariable String sub) throws IOException {
		return ncsSearcherService.getNcsModuleCriteria(main, sub);
	}
}
