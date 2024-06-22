package io.xdata.group.security.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "users")
open class ExchangeUser {

    @Id
    @Column
    open var username: String? = null

    @Column
    open var password: String? = null
}
