package io.xdata.group.exchange

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ExchangeServerApplication

fun main(args: Array<String>) {
    runApplication<ExchangeServerApplication>(*args)
}