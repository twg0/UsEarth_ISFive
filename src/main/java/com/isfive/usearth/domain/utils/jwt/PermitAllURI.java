package com.isfive.usearth.domain.utils.jwt;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

@Getter
public enum PermitAllURI {
	login("/login"),
	token("/token"),
	view("/views"),
	home("/");

	PermitAllURI(String uri) {
		this.uri = uri;
	}
	private final String uri;

	public static List<String> getList() {
		return Arrays.stream(PermitAllURI.values()).map(PermitAllURI::getUri).toList();
	}
}
