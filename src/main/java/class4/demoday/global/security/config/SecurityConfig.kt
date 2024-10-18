package class4.demoday.global.security.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig(
    private val domainAuthorizationConfig: DomainAuthorizationConfig,
    private val corsConfig: CorsConfig
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .cors { cors -> cors.configurationSource(corsConfig.configureCors()) }
            .csrf { csrf -> csrf.disable() }
            .formLogin { form -> form.disable() }
            .authorizeHttpRequests { domainAuthorizationConfig.configure(it) }
        return http.build()
    }
}