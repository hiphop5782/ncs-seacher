package com.hacademy.ncs.http.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class NcsTreeListItem {
	private int ncsDegr;
	private String ncsLclasCd, ncsLclasCdNm;
	private String ncsMclasCd, ncsMclasCdNm;
	private String ncsSclasCd, ncsSclasCdNm;
	private String ncsSubdCd, ncsSubdCdNm;
	
	private String dutyCd;
	private String dutySvcNo;
	private String ncsClCd;
	private String compUnitCd;
	private String compUnitName;
	private String compUnitDef;
	private int compUnitLevel;
}
