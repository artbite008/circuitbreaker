package circuitbreaker;

import io.github.resilience4j.micrometer.tagged.CircuitBreakerMetricNames;

public class DemoCircuitBreakerMetricNames {
    private static final String DEFAULT_PREFIX = "demo.resilience4j.circuitbreaker";
    public static final String DEFAULT_CIRCUIT_BREAKER_CALLS = DEFAULT_PREFIX + ".calls";
    public static final String DEFAULT_CIRCUIT_BREAKER_NOT_PERMITTED_CALLS = DEFAULT_PREFIX + ".not.permitted.calls";
    public static final String DEFAULT_CIRCUIT_BREAKER_STATE = DEFAULT_PREFIX + ".state";
    public static final String DEFAULT_CIRCUIT_BREAKER_BUFFERED_CALLS = DEFAULT_PREFIX + ".buffered.calls";
    public static final String DEFAULT_CIRCUIT_BREAKER_SLOW_CALLS = DEFAULT_PREFIX + ".slow.calls";
    public static final String DEFAULT_CIRCUIT_BREAKER_FAILURE_RATE = DEFAULT_PREFIX + ".failure.rate";
    public static final String DEFAULT_CIRCUIT_BREAKER_SLOW_CALL_RATE = DEFAULT_PREFIX + ".slow.call.rate";

    static CircuitBreakerMetricNames metricNames() {
        return CircuitBreakerMetricNames.custom()
                .callsMetricName(DEFAULT_CIRCUIT_BREAKER_CALLS)
                .notPermittedCallsMetricName(DEFAULT_CIRCUIT_BREAKER_NOT_PERMITTED_CALLS)
                .stateMetricName(DEFAULT_CIRCUIT_BREAKER_STATE)
                .bufferedCallsMetricName(DEFAULT_CIRCUIT_BREAKER_BUFFERED_CALLS)
                .failureRateMetricName(DEFAULT_CIRCUIT_BREAKER_FAILURE_RATE)
                //.slowCallsRateMetricName(DEFAULT_CIRCUIT_BREAKER_SLOW_CALLS)
                .build();
    }
}
