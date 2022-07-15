package com.hacademy.ncs.http.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data @JsonIgnoreProperties
public class NcsModuleListItem {
	private int ncsDegr;
	private String ncsLclasCd, ncsLclasCdNm;
	private String ncsMclasCd, ncsMclasCdNm;
	private String ncsSclasCd, ncsSclasCdNm;
	private String ncsSubdCd, ncsSubdCdNm;
	
	public String code() {
		return ncsLclasCd + ncsMclasCd + ncsSclasCd + ncsSubdCd;
	}
	public boolean is(String code) {
		return this.code().startsWith(code);
	}
}
