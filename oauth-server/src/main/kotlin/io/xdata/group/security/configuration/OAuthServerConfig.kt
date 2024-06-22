package io.xdata.group.security.configuration

import com.nimbusds.jose.jwk.JWKSet
import com.nimbusds.jose.jwk.RSAKey
import com.nimbusds.jose.jwk.source.JWKSource
import com.nimbusds.jose.proc.SecurityContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.core.AuthorizationGrantType
import org.springframework.security.oauth2.core.oidc.OidcScopes
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings
import java.security.KeyPairGenerator
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.util.*


@Configuration(proxyBeanMethods = false)
class OAuthServerConfig {

    @Bean
    fun clientRepository(): InMemoryRegisteredClientRepository {
        val client = RegisteredClient.withId(UUID.randomUUID().toString())
            .clientId("frontend")
            .clientSecret("frontend")
            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
            .redirectUri("http://127.0.0.1:8080/api/test")
            .scope(OidcScopes.OPENID)
            .scope("api.all")
            .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
            .build()

        return InMemoryRegisteredClientRepository(client)
    }

    @Bean
    fun jwkSource(): JWKSource<SecurityContext> {
        val key = generateRsa()
        val set = JWKSet(key)
        return JWKSource { selector, _ -> selector.select(set) }
    }

    private fun generateRsa(): RSAKey {
        val keyPair = generateRsaKey()
        val publicKey = keyPair.public as RSAPublicKey
        val privateKey = keyPair.private as RSAPrivateKey
        return RSAKey.Builder(publicKey)
            .privateKey(privateKey)
            .keyID(UUID.randomUUID().toString())
            .build()

    }

    private fun generateRsaKey() = try {
        val generator = KeyPairGenerator.getInstance("RSA")
        generator.initialize(2048)
        generator.generateKeyPair()
    } catch (e: Exception) {
        throw IllegalArgumentException()
    }

    @Bean
    fun jwtDecoder(source: JWKSource<SecurityContext>): JwtDecoder =
        OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource())

    @Bean
    fun authorizationServerSettings(): AuthorizationServerSettings = AuthorizationServerSettings.builder().build()

    @Bean
    fun passwordEncoder(): PasswordEncoder = NoOpPasswordEncoder.getInstance()
}