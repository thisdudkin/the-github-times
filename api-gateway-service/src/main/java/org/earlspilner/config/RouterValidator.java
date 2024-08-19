package org.earlspilner.config;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author Alexander Dudkin
 */
@Service
public class RouterValidator {

    private static final List<String> openEndpoints = List.of(
            "/auth/register"
    );

    public final Predicate<ServerHttpRequest> isSecured = request -> openEndpoints.stream()
            .noneMatch(uri -> request.getURI().getPath().contains(uri));

}
