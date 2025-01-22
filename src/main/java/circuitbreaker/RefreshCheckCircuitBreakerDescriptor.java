package circuitbreaker;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;

import java.time.Duration;

public class RefreshCheckCircuitBreakerDescriptor implements DemoCircuitBreakerDescriptor {
    private final int failureRateThreshold = 50;
    private final int slowCallRateThreshold = 80;
    private final int slowCallDurationThreshold = 500;
    private final int waitDurationInOpenState = 60000;
    private final int permittedNumberOfCallsInHalfOpenState = 10;
    private final int minimumNumberOfCalls = 10;
    private final int slidingWindowSize = 30;

    private CircuitBreakerConfig buildCustomConfig() {
//        log.info("[RefreshCheckCircuitBreakerConfig] failureRateThreshold: {}", failureRateThreshold);
//        log.info("[RefreshCheckCircuitBreakerConfig] slowCallRateThreshold: {}", slowCallRateThreshold);
//        log.info("[RefreshCheckCircuitBreakerConfig] slowCallDurationThreshold: {}", slowCallDurationThreshold);
//        log.info("[RefreshCheckCircuitBreakerConfig] waitDurationInOpenState: {}", waitDurationInOpenState);
//        log.info("[RefreshCheckCircuitBreakerConfig] permittedNumberOfCallsInHalfOpenState: {}", permittedNumberOfCallsInHalfOpenState);
//        log.info("[RefreshCheckCircuitBreakerConfig] minimumNumberOfCalls: {}", minimumNumberOfCalls);
//        log.info("[RefreshCheckCircuitBreakerConfig] slidingWindowSize: {}", slidingWindowSize);

        return CircuitBreakerConfig.custom()
                .failureRateThreshold(failureRateThreshold)
                .slowCallRateThreshold(slowCallRateThreshold)
                .slowCallDurationThreshold(Duration.ofMillis(slowCallDurationThreshold))
                .waitDurationInOpenState(Duration.ofMillis(waitDurationInOpenState))
                .permittedNumberOfCallsInHalfOpenState(permittedNumberOfCallsInHalfOpenState)
                .minimumNumberOfCalls(minimumNumberOfCalls)
                .slidingWindowSize(slidingWindowSize)
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .enableAutomaticTransitionFromOpenToHalfOpen()
                .build();
    }

    @Override
    public DemoCircuitBreakerType getType() {
        return DemoCircuitBreakerType.REFRESH;
    }
}


