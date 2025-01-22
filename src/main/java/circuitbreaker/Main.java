package circuitbreaker;

public class Main {
//    public Boolean checkDemoSystemHealth() {
//        return circuitRouter.route(DemoCircuitBreakerType.ORY_HEALTHY,
//                () -> oryHydraHealthCheck(),
//                () -> false,
//                "oryHealthCheck");
//    }

//    private boolean isOryCircuitBreakerOpen() {
//        return demoCircuitRouter.isCircuitBreakerOpen(DemoCircuitBreakerType.REFRESH)
//                || DemoCircuitRouter.isCircuitBreakerOpen(DemoCircuitBreakerType.HEALTHY);
//    }

//    public Either<AuthError, OAuthResponse> renewAccessTokenWithCB(SSOLocalSessionEntity localSessionEntity, String oldAccessToken) {
//        return circuitRouter.route(DemoCircuitBreakerType.ORY_REFRESH,
//                () -> renewAccessToken(localSessionEntity, oldAccessToken),
//                () -> Either.left(new AuthError(ErrorCode.HYDRATORA_SYSTEM_ERROR, "fallback_by_circuitbreaker")),
//                "oryRefreshToken");
//    }


    public static void main(String[] args) {
    }
}
