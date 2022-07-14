package com.hacademy.ncs.http.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data @JsonIgnoreProperties
public class NcsModuleCriteria {
	private String dutyCd;
	private String dutySvcNo;
	private String ncsClCd;
	private String compUnitCd;
	private String compUnitName;
	private int compUnitLevel;
	private int compUnitFactrNo;
	private String compUnitFactrName;
	private String gbnCd;
	private String gbnName;
	private String gbnVal;
}
