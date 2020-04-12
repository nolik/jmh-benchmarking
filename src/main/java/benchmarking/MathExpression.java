package benchmarking;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

import static java.lang.Math.*;

public class MathExpression {
    private static final BinaryOperator<Double> BINARY_NUMERATOR = (n, m) -> 0.9 * n - 0.4 * m;
    private static final BinaryOperator<Double> BINARY_POW = (n, m) -> pow(m, 5) + pow(n, 2);
    private static final BinaryOperator<Double> BINARY_DIVISION = (n, m) -> n / m;
    private static final UnaryOperator<Double> LN_DECREMENT = (a) -> a - log10(cos(0.6) + sin(0.2 + PI));
    private static final UnaryOperator<Double> SQRT_FUNC = Math::sqrt;

    static double resolve(double m, double n) {
        var numerator = cos(exp(0.9 * n - 0.4 * m));
        var ln = log10(cos(0.6) + sin(0.2 + PI));
        var denominator = pow(m, 5) + pow(n, 2) - ln;
        return sqrt(abs(numerator / denominator));
    }

    static double funSolve(double m, double n) {
        return sqrt(
                abs(BINARY_DIVISION
                        .apply((BINARY_NUMERATOR
                                        .andThen(Math::exp)
                                        .andThen(Math::cos)
                                        .apply(n, m)),
                                LN_DECREMENT.apply(
                                        BINARY_POW.apply(n, m)))));
    }

}
