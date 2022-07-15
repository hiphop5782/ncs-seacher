package com.hacademy.ncs.http.response;

import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data @JsonIgnoreProperties
public class NcsModuleSubListResponse implements Iterable<NcsModuleSubList>{
	private List<NcsModuleSubList> data;
	private DataInfo dataInfo;
	@Override
	public Iterator<NcsModuleSubList> iterator() {
		return data.iterator();
	}
}
