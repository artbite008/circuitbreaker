package circuitbreaker;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Slf4j
@Component
@RequiredArgsConstructor
public class DemoCircuitRouter {
    private final DemoCircuitBreakerRegistry circuitBreakerRegistry;

    public boolean isCircuitBreakerOpen(DemoCircuitBreakerType type) {
        CircuitBreaker.State circuitBreakerState = circuitBreakerRegistry.circuitBreaker(type).getState();
        if (CircuitBreaker.State.OPEN.equals(circuitBreakerState) ||
                CircuitBreaker.State.HALF_OPEN.equals(circuitBreakerState) ||
                CircuitBreaker.State.FORCED_OPEN.equals(circuitBreakerState)) {
            return true;
        }
        return false;
    }

//    public <T> T route(DemoCircuitBreakerType type, Supplier<T> supplier, Supplier<T> fallback, String tag) {
//        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker(type);
//        Long start = System.nanoTime();
//        return Try.ofSupplier(() -> CircuitBreaker.decorateSupplier(circuitBreaker, supplier))
//                .onSuccess(it -> handleSuccess(tag, start))
//                .onFailure(throwable -> handleFailure(throwable, type, tag))
//                .recover(exception -> handleRecover(fallback, exception, type, tag))
//                .get();
//    }

//    public void route(DemoCircuitBreakerType type, Runnable runnable, Runnable fallback, String tag) {
//        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker(type);
//        Long start = System.nanoTime();
//        Try.run(() -> CircuitBreaker.decorateRunnable(circuitBreaker, runnable))
//                .onSuccess(it -> handleSuccess(tag, start))
//                .onFailure(throwable -> handleFailure(throwable, type, tag))
//                //.recover(exception -> handleRecover(fallback, exception, type, tag));
//    }


    private void handleSuccess(String tag, Long start) {
        Long end = System.nanoTime();
    }

    private void handleFailure(Throwable throwable, DemoCircuitBreakerType type, String tag) {
        //log.("[MemberCircuitRouter] handleFailure for type {} tag {}", type.getMetricKey(), tag);
    }

    private void handleRecover(Runnable fallback, Exception e, DemoCircuitBreakerType type, String tag) {
        logCircuitRouteException(type, tag, e);
        Long startRecover = System.nanoTime();
        Try.runRunnable(fallback).get();
        Long endRecover = System.nanoTime();
    }

    private <T> T handleRecover(Supplier<T> fallback, Exception e, DemoCircuitBreakerType type, String tag) {
        logCircuitRouteException(type, tag, e);
        Long startRecover = System.nanoTime();
        T fallbackResult = fallback.get();
        Long endRecover = System.nanoTime();
        return fallbackResult;
    }

    private void logCircuitRouteException(DemoCircuitBreakerType type, String tag, Exception e) {
        //log.warn("[MemberCircuitRouter] Type: {}, Tag: {}", type, tag, e);
    }
}
