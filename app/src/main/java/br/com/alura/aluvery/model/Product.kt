package br.com.alura.aluvery.model

import java.math.BigDecimal
import java.util.UUID

data class Product(val name: String, val price: BigDecimal,
    val image: String? = null, val description: String? = null, val iD: String = UUID.randomUUID().toString())