package io.xdata.group.security.service

import io.xdata.group.security.repository.UserRepository
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class H2UserDetailsService(private val repo: UserRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails =
        repo.findById(username)
            .map {
                User.withUsername(it.username)
                    .password(it.password)
                    .authorities("USER")
                    .accountExpired(false)
                    .accountLocked(false)
                    .credentialsExpired(false)
                    .disabled(false)
                    .build()
            }.orElseThrow { BadCredentialsException("Bad credentials") }
}