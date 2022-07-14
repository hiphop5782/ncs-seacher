package com.hacademy.ncs.http.response;

import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data @JsonIgnoreProperties
public class NcsModuleListResponse implements Iterable<NcsModuleListItem>{
	private List<NcsModuleListItem> data;
	private DataInfo dataInfo;
	
	@Override
	public Iterator<NcsModuleListItem> iterator() {
		return data.iterator();
	}
}

