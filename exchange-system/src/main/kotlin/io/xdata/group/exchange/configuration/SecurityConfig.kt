package io.xdata.group.exchange.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoders
import org.springframework.security.web.server.SecurityWebFilterChain

@Configuration
@EnableWebFluxSecurity
class SecurityConfig {

    @Bean
    fun userDetailsService(http: ServerHttpSecurity): SecurityWebFilterChain {
        http.authorizeExchange {
            it.pathMatchers("/api/**")
                .hasAuthority("SCOPE_api.all")
        }.oauth2ResourceServer {
            it.jwt { jwt ->
                jwt.jwtDecoder(jwtDecoder())
            }
        }

        return http.build()
    }

    @Bean
    fun jwtDecoder(): ReactiveJwtDecoder =
        ReactiveJwtDecoders.fromIssuerLocation("http://localhost:9000")
}