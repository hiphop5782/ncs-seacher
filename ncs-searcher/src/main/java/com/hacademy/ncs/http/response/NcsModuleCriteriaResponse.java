package com.hacademy.ncs.http.response;

import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data @JsonIgnoreProperties
public class NcsModuleCriteriaResponse implements Iterable<NcsModuleCriteria>{
	private List<NcsModuleCriteria> data;
	private DataInfo dataInfo;
	@Override
	public Iterator<NcsModuleCriteria> iterator() {
		return data.iterator();
	}
}
