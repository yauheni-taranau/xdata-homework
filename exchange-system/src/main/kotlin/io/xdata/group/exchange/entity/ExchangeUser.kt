package io.xdata.group.exchange.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "users")
open class ExchangeUser {

    @Column(nullable = false)
    @Id
    open var username: String? = null

    @Column(nullable = false)
    open var password: String? = null
}
