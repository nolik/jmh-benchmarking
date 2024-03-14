package com.benchmarking.benchmark;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

public class EnumBenchmark {

	@State(Scope.Benchmark)
	public static class ExecutionPlan {

		@Param({"100", "10000"})
		private int iterations;

		private List<TestedEnumFetchThoughStream> enums;

		@Setup()
		public void setUp() {
			enums = IntStream.generate(() -> new Random().nextInt(TestedEnumFetchThoughStream.values().length))
				.limit(iterations)
				.mapToObj(v -> TestedEnumFetchThoughStream.values()[v])
				.toList();
		}
	}

	@Benchmark
	@BenchmarkMode(Mode.Throughput)
	public void benchFetchingThoughStream(ExecutionPlan plan) {
		 plan.enums.stream()
			.map(e -> TestedEnumFetchThoughStream.fetchEnumByValue(e.langName))
			 .count();
	}

	@Benchmark
	@BenchmarkMode(Mode.Throughput)
	public void benchFetchingThoughMap(ExecutionPlan plan) {
		plan.enums.stream()
			.map(e -> TestedEnumFetchThoughMap.fetchEnumByValue(e.langName))
			.count();
	}
}
