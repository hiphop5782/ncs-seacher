package com.hacademy.ncs.http.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties
public class DataInfo {
	private String code;
	private String message;
	private int totalPage;
	private int pageNo;
	private int totCnt;
}
