package com.hacademy.ncs.http.response;

import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties
public class NcsModuleDetailResponse implements Iterable<NcsModuleDetail> {
	private List<NcsModuleDetail> data;
	private DataInfo dataInfo;
	
	@Override
	public Iterator<NcsModuleDetail> iterator() {
		return data.iterator();
	}
}
