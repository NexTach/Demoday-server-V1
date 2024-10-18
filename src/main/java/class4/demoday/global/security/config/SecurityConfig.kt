package class4.demoday.global.security.config

import class4.demoday.global.security.jwt.filter.JwtFilter
import class4.demoday.global.security.jwt.service.JwtAuthenticationService
import class4.demoday.global.security.jwt.service.JwtTokenService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class SecurityConfig(
    private val domainAuthorizationConfig: DomainAuthorizationConfig,
    private val corsConfig: CorsConfig,
) {

    @Bean
    fun securityFilterChain(
        http: HttpSecurity, jwtTokenService: JwtTokenService, jwtAuthenticationService: JwtAuthenticationService
    ): SecurityFilterChain {
        http
            .cors { cors -> cors.configurationSource(corsConfig.configureCors()) }
            .csrf { csrf -> csrf.disable() }
            .formLogin { form -> form.disable() }
            .authorizeHttpRequests { domainAuthorizationConfig.configure(it) }
            .addFilterBefore(
                JwtFilter(jwtTokenService, jwtAuthenticationService),
                UsernamePasswordAuthenticationFilter::class.java
            )
        return http.build()
    }
}