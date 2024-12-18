package class4.demoday.global.security.config

import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer
import org.springframework.stereotype.Component

@Component
class DomainAuthorizationConfig {

    fun configure(authorizeRequests: AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry) {
        authorizeRequests
            .requestMatchers(
                "/admin/**"
            ).hasRole("ADMIN")
            .requestMatchers(
                "/manager/**"
            ).hasRole("WARD")
            .requestMatchers(
                "/protector/**"
            ).hasRole("PROTECTOR")
            .requestMatchers(
                "/user/**"
            ).hasAnyRole("WARD", "PROTECTOR")
            .requestMatchers(
                "/api/v1/auth/signin",
                "/api/v1/auth/signup",
                "/api/v1/auth/refresh",
                "/swagger-ui/**",
                "/v3/api-docs/**",
                "/graphiql"
            ).permitAll()
            .anyRequest().permitAll()
    }
}
