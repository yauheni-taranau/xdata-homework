package io.xdata.group.security.configuration

import org.h2.tools.Server
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class H2Config {

    @Bean(initMethod = "start", destroyMethod = "stop")
    fun inMemoryH2DBServer(): Server =
        Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9999")
}