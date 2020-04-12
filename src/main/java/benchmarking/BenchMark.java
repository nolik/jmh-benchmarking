package benchmarking;

import org.apache.commons.math3.util.Pair;
import org.openjdk.jmh.annotations.*;

import java.util.Random;
import java.util.stream.Stream;

public class BenchMark {

    @State(Scope.Benchmark)
    public static class ExecutionPlan {
//
//        @Param({"10", "100", "1000"})
//        public int iterations;

        public Stream<Pair<Integer, Integer>> pairs;

        @Setup(Level.Invocation)
        public void setUp() {
            pairs = Stream.generate(this::createValuePair)
                    .limit(100);
        }

        private Pair<Integer, Integer> createValuePair() {
            return new Pair<>(new Random().nextInt(Integer.MAX_VALUE),
                    new Random().nextInt(Integer.MAX_VALUE));
        }
    }

    @Fork(value = 1, warmups = 1)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
//    @Warmup(iterations = 5)
    public void procedureStyle(ExecutionPlan plan) {
        plan.pairs.forEach(pair ->
                MathExpression.resolve(pair.getKey(), pair.getValue())
        );
    }

    @Fork(value = 1, warmups = 1)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
//    @Warmup(iterations = 5)
    public void functionalStyle(ExecutionPlan plan) {
        plan.pairs.forEach(pair ->
                MathExpression.funSolve(pair.getKey(), pair.getValue())
        );
    }

}
