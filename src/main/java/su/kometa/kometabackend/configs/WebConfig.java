package su.kometa.kometabackend.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import su.kometa.kometabackend.interceptors.AuthorizationInterceptor;
import su.kometa.kometabackend.interceptors.ChatInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final AuthorizationInterceptor authorizationInterceptor;

    private final ChatInterceptor chatInterceptor;

    @Autowired
    public WebConfig(AuthorizationInterceptor authorizationInterceptor, ChatInterceptor chatInterceptor) {
        this.authorizationInterceptor = authorizationInterceptor;
        this.chatInterceptor = chatInterceptor;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor).excludePathPatterns("/auth/signup", "/auth/login", "/info");
        registry.addInterceptor(chatInterceptor).excludePathPatterns("/auth/**", "/models/**", "/users/**", "/info");
    }
}