package io.xdata.group.security.repository

import io.xdata.group.security.entity.ExchangeUser
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<ExchangeUser, String>