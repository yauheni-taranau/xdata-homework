package io.xdata.group.exchange.controller

import io.xdata.group.exchange.repository.ExchangeUserRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController(
    private val rep: ExchangeUserRepository
) {

    @GetMapping("/api/test")
    fun test(): String {
        return "TEST"
    }
}