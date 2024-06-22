package io.xdata.group.security.service

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service

@Service
class CustomAuthenticationProvider(
    private val customUserDetailsService: H2UserDetailsService
) : AuthenticationProvider {
    override fun authenticate(authentication: Authentication): Authentication {
        val username = authentication.name
        val pass = authentication.credentials.toString()
        val user = customUserDetailsService.loadUserByUsername(username)

        if (user.password == pass) {
            return UsernamePasswordAuthenticationToken(
                user.username,
                user.password,
                user.authorities
            )
        } else {
            throw BadCredentialsException("Bad credentials for user ${user.username}")
        }
    }

    override fun supports(authentication: Class<*>): Boolean =
        UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication)

}