package com.hacademy.ncs.http.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data @JsonIgnoreProperties
public class NcsModuleSubList {
	private String dutyCd;
	private String dutySvcNo;
	private String ncsClCd;
	private String compUnitCd;
	private String compUnitName;
	private String compUnitDef;
	private int compUnitLevel;
}
