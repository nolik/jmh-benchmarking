package com.benchmarking.benchmark;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

public class EnumBenchmark {

	@State(Scope.Benchmark)
	public static class ExecutionPlan {

		@Param({"100", "1000", "10000"})
		private int iterations;

		private Stream<TestedEnumFetchThoughStream> enums;

		@Setup(Level.Invocation)
		public void setUp() {
			enums = IntStream.generate(() -> new Random().nextInt(TestedEnumFetchThoughStream.values().length))
				.limit(iterations)
				.mapToObj(v -> TestedEnumFetchThoughStream.values()[v]);
		}
	}

	@Benchmark
	@BenchmarkMode(Mode.Throughput)
	@Warmup(iterations = 5)
	public void benchFetchingThoughStream(ExecutionPlan plan) {
		 plan.enums
			.map(e -> TestedEnumFetchThoughStream.fetchEnumByValue(e.langName))
			.toList();
	}

	@Benchmark
	@BenchmarkMode(Mode.Throughput)
	@Warmup(iterations = 5)
	public void benchFetchingThoughMap(ExecutionPlan plan) {
		plan.enums
			.map(e -> TestedEnumFetchThoughMap.fetchEnumByValue(e.langName))
			.toList();
	}
}
