package com.benchmarking.benchmark;

import java.util.Arrays;
import java.util.Objects;

public enum TestedEnumFetchThoughStream {
	JAVA("java", "javafun"),
	KOTLIN("kotlin", "kotlinist"),
	RUST("rust", "rustaman"),
	;

	public final String langName;
	public final String usersNickname;

	TestedEnumFetchThoughStream(String langName, String usersNickname) {
		this.langName = langName;
		this.usersNickname = usersNickname;
	}

	public static TestedEnumFetchThoughStream fetchEnumByValue(String langName) {
		return Arrays.stream(TestedEnumFetchThoughStream.values())
			.filter(v -> Objects.equals(v.langName, langName))
			.findFirst()
			.orElseThrow(IllegalArgumentException::new);
	}
}
