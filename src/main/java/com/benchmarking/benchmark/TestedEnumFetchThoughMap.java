package com.benchmarking.benchmark;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum TestedEnumFetchThoughMap {
	JAVA("java", "javafun"),
	KOTLIN("kotlin", "kotlinist"),
	RUST("rust", "rustaman"),
	;

	private static final Map<String, TestedEnumFetchThoughMap> ENUM_VALUE_MAP =
		Arrays.stream(TestedEnumFetchThoughMap.values())
			.collect(
				Collectors.toMap(v -> v.langName, Function.identity())
			);

	public final String langName;
	public final String usersNickname;

	TestedEnumFetchThoughMap(String langName, String usersNickname) {
		this.langName = langName;
		this.usersNickname = usersNickname;
	}

	public static TestedEnumFetchThoughMap fetchEnumByValue(String langName) {
		return Optional.ofNullable(ENUM_VALUE_MAP.get(langName))
			.orElseThrow(IllegalArgumentException::new);
	}
}
