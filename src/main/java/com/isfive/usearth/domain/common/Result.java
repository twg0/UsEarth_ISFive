package com.isfive.usearth.domain.common;

import lombok.Data;

import java.util.List;

@Data
public class Result<T> {
	private List<T> data;

	public Result(List<T> data) {
		this.data = data;
	}
}
