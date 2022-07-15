package com.hacademy.ncs.http.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class NcsTreeListResponse {
	private List<NcsTreeListItem> list = new ArrayList<>();
	public void add(NcsTreeListItem item) {
		list.add(item);
	}
}
