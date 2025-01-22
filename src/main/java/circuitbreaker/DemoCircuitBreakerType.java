package circuitbreaker;


import lombok.Getter;

@Getter
public enum DemoCircuitBreakerType {
    HEALTHY,
    REFRESH;

    private final String metricKey;
    private final String switchName;

    private static final String SWITCH_PREFIX = "CircuitBreakerSwitch|";

    DemoCircuitBreakerType() {
        this.metricKey = name().toLowerCase().replaceAll("_", "-");
        this.switchName = SWITCH_PREFIX + name();
    }
}
