package io.xdata.group.exchange.repository

import io.xdata.group.exchange.entity.ExchangeUser
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ExchangeUserRepository : CrudRepository<ExchangeUser, String>