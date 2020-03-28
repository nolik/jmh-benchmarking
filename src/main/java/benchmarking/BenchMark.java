package benchmarking;

import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class BenchMark {

    @State(Scope.Benchmark)
    public static class ExecutionPlan {

        @Param({"10", "20", "30", "50", "100"})
        public int iterations;

        public int[] ids;

        @Setup(Level.Invocation)
        public void setUp() {
            ids = IntStream.generate(() -> new Random().nextInt(Integer.MAX_VALUE))
                    .limit(iterations)
                    .toArray();
        }
    }

    @Fork(value = 1, warmups = 1)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 5)
    public void collectionEqualBenchmark(ExecutionPlan plan) {
        final var firstCallection = Arrays.stream(plan.ids)
                .boxed()
                .collect(toList());
        final var secondCollection = List.copyOf(firstCallection);
        final var isEqualCollections = firstCallection.size() == secondCollection.size()
                && secondCollection.containsAll(firstCallection);
    }

    @Fork(value = 1, warmups = 1)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 5)
    public void collectionSetEqualBenchmark(ExecutionPlan plan) {
        final var firstCallection = Arrays.stream(plan.ids)
                .boxed()
                .collect(toSet());
        final var secondCollection = Set.copyOf(firstCallection);
        final var isEqualCollections = secondCollection.equals(firstCallection);
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @BenchmarkMode(Mode.Throughput)
    public void init() {
        // Do nothing
    }

}
