package circuitbreaker;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.micrometer.tagged.TaggedCircuitBreakerMetrics;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Component
public class DemoCircuitBreakerRegistry {
    private static final int DEFAULT_FAILURE_RATE_THRESHOLD = 50;
    private static final Duration DEFAULT_SLOW_CALL_DURATION = Duration.ofMillis(1000);
    private static final int DEFAULT_SLOW_CALL_RATE_THRESHOLD = 50;
    private static final Duration DEFAULT_WAIT_DURATION_IN_OPEN_STATE = Duration.ofMinutes(5);
    private static final int DEFAULT_PERMITTED_NUMBER_OF_CALLS_IN_HALF_OPEN_STATE = 10;
    private static final int DEFAULT_SLIDING_WINDOW_SIZE = 100;
    private static final int DEFAULT_MINIMUM_NUMBER_OF_CALLS = 20;
    private static final CircuitBreakerConfig DEFAULT_CONFIG = CircuitBreakerConfig.custom()
            .failureRateThreshold(DEFAULT_FAILURE_RATE_THRESHOLD)
            .slowCallRateThreshold(DEFAULT_SLOW_CALL_RATE_THRESHOLD)
            .slowCallDurationThreshold(DEFAULT_SLOW_CALL_DURATION)
            .waitDurationInOpenState(DEFAULT_WAIT_DURATION_IN_OPEN_STATE)
            .permittedNumberOfCallsInHalfOpenState(DEFAULT_PERMITTED_NUMBER_OF_CALLS_IN_HALF_OPEN_STATE)
            .slidingWindowSize(DEFAULT_SLIDING_WINDOW_SIZE)
            .minimumNumberOfCalls(DEFAULT_MINIMUM_NUMBER_OF_CALLS)
            .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
            .enableAutomaticTransitionFromOpenToHalfOpen()
            .build();
    private final CircuitBreakerRegistry registry;

    @Autowired
    public DemoCircuitBreakerRegistry(List<DemoCircuitBreakerDescriptor> descriptors, MeterRegistry meterRegistry) {
        registry = CircuitBreakerRegistry.ofDefaults();
        validateAndApply(descriptors);
        activateCircuitBreakerMetrics(meterRegistry, registry);
    }

    private void activateCircuitBreakerMetrics(MeterRegistry meterRegistry, CircuitBreakerRegistry registry) {
        TaggedCircuitBreakerMetrics
                .ofCircuitBreakerRegistry(DemoCircuitBreakerMetricNames.metricNames(), registry);
                //.bindTo(registry);
    }

    private void validateAndApply(List<DemoCircuitBreakerDescriptor> descriptors) {
        Map<DemoCircuitBreakerType, DemoCircuitBreakerDescriptor> descriptorIndex = descriptors.stream()
                .collect(Collectors.toMap(DemoCircuitBreakerDescriptor::getType, Function.identity()));

        Stream.of(DemoCircuitBreakerType.values()).forEach(type -> {
            DemoCircuitBreakerDescriptor descriptor = descriptorIndex.get(type);
            //checkState(descriptor != null, "Please implement circuit breaker descriptor for " + type);
            descriptor.apply(this);
        });
    }

    public CircuitBreaker circuitBreaker(DemoCircuitBreakerType type) {
        return registry.circuitBreaker(type.getMetricKey());
    }

    public void register(DemoCircuitBreakerType type) {
        register(type, DEFAULT_CONFIG);
    }

    public void register(DemoCircuitBreakerType type, CircuitBreakerConfig config) {
        registry.circuitBreaker(type.getMetricKey(), config);
        activateEventHandlers(type);
    }

    private void activateEventHandlers(DemoCircuitBreakerType type) {
//        registry.getEventPublisher().onStateTransition(event -> {
//            //log.warn("[Circuit breaker] {}: {}", type, event.getStateTransition());
//        });
    }
}