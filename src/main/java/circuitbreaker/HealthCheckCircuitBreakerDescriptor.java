package circuitbreaker;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;

import java.time.Duration;

public class HealthCheckCircuitBreakerDescriptor implements DemoCircuitBreakerDescriptor {
    private final int failureRateThreshold = 50;
    private final int slowCallRateThreshold = 80;
    private final int slowCallDurationThreshold = 500;
    private final int waitDurationInOpenState = 60000;
    private final int permittedNumberOfCallsInHalfOpenState = 10;
    private final int minimumNumberOfCalls = 10;
    private final int slidingWindowSize = 30;

    private CircuitBreakerConfig buildCustomConfig() {
//        log.info("[HealthCheckCircuitBreakerConfig] failureRateThreshold: {}", failureRateThreshold);
//        log.info("[HealthCheckCircuitBreakerConfig] slowCallRateThreshold: {}", slowCallRateThreshold);
//        log.info("[HealthCheckCircuitBreakerConfig] slowCallDurationThreshold: {}", slowCallDurationThreshold);
//        log.info("[HealthCheckCircuitBreakerConfig] waitDurationInOpenState: {}", waitDurationInOpenState);
//        log.info("[HealthCheckCircuitBreakerConfig] permittedNumberOfCallsInHalfOpenState: {}", permittedNumberOfCallsInHalfOpenState);
//        log.info("[HealthCheckCircuitBreakerConfig] minimumNumberOfCalls: {}", minimumNumberOfCalls);
//        log.info("[HealthCheckCircuitBreakerConfig] slidingWindowSize: {}", slidingWindowSize);

        return CircuitBreakerConfig.custom()
                .failureRateThreshold(failureRateThreshold)
                .slowCallRateThreshold(slowCallRateThreshold)
                .slowCallDurationThreshold(Duration.ofMillis(slowCallDurationThreshold))
                .waitDurationInOpenState(Duration.ofMillis(waitDurationInOpenState))
                .permittedNumberOfCallsInHalfOpenState(permittedNumberOfCallsInHalfOpenState)
                .minimumNumberOfCalls(minimumNumberOfCalls)
                .slidingWindowSize(slidingWindowSize)
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.TIME_BASED)
                .enableAutomaticTransitionFromOpenToHalfOpen()
                .build();
    }

    @Override
    public DemoCircuitBreakerType getType() {
        return DemoCircuitBreakerType.HEALTHY;
    }

    @Override
    public void apply(DemoCircuitBreakerRegistry registry) {
        registry.register(getType(), this.buildCustomConfig());
    }
}
