package circuitbreaker;


public interface DemoCircuitBreakerDescriptor {
    DemoCircuitBreakerType getType();

    default void apply(DemoCircuitBreakerRegistry registry) {
        registry.register(getType());
    }
}
