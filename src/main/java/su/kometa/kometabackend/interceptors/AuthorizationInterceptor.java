package su.kometa.kometabackend.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import su.kometa.kometabackend.constants.AttributesConstants;
import su.kometa.kometabackend.models.User;
import su.kometa.kometabackend.services.AuthorizationService;

import java.util.Objects;

@Slf4j
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    private final AuthorizationService authorizationService;

    @Autowired
    public AuthorizationInterceptor(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) {
        if (Objects.equals(request.getMethod(), HttpMethod.OPTIONS.name())) return true;

        String accessToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        User user = authorizationService.authUser(accessToken);

        request.setAttribute(AttributesConstants.USER, user);
        return true;
    }
}
