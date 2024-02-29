package br.com.alura.aluvery.sampledata

import br.com.alura.aluvery.model.Product
import java.math.BigDecimal

val sampleCandies = listOf(
    Product(
        name = "Chocolate",
        price = BigDecimal("3.99"),
        image = "https://images.pexels.com/photos/65882/chocolate-dark-coffee-confiserie-65882.jpeg",
        description = null
    ),
    Product(
        name = "Sorvete",
        price = BigDecimal("5.99"),
        image = "https://images.pexels.com/photos/1352278/pexels-photo-1352278.jpeg",
        description = null
    ),
    Product(
        name = "Bolo",
        price = BigDecimal("11.99"),
        image = "https://images.pexels.com/photos/291528/pexels-photo-291528.jpeg",
        description = null
    )
)

val sampleDrinks = listOf(
    Product(
        name = "Cerveja",
        price = BigDecimal("5.99"),
        image = "https://images.pexels.com/photos/1552630/pexels-photo-1552630.jpeg",
        description = null
    ),
    Product(
        name = "Refrigerante",
        price = BigDecimal("4.99"),
        image = "https://images.pexels.com/photos/2775860/pexels-photo-2775860.jpeg",
        description = null
    ),
    Product(
        name = "Suco",
        price = BigDecimal("7.99"),
        image = "https://images.pexels.com/photos/96974/pexels-photo-96974.jpeg",
        description = null
    ),
    Product(
        name = "Água",
        price = BigDecimal("2.99"),
        image = "https://images.pexels.com/photos/327090/pexels-photo-327090.jpeg",
        description = null
    )
)

val sampleProducts: List<Product> = listOf(
    Product(
        name = "Hamburguer",
        price = BigDecimal("12.99"),
        image = "https://images.pexels.com/photos/1639557/pexels-photo-1639557.jpeg",
        description = null
    ),
    Product(
        name = "Pizza",
        price = BigDecimal("19.99"),
        image = "https://images.pexels.com/photos/825661/pexels-photo-825661.jpeg",
        description = null
    ),
    Product(
        name = "Batata frita",
        price = BigDecimal("7.99"),
        image = "https://images.pexels.com/photos/1583884/pexels-photo-1583884.jpeg",
        description = null
    )
)

val addedProducts: MutableList<Product> = mutableListOf()

/*val todosProdutos: MutableList<Product> = mutableListOf(*sampleProducts.toTypedArray(),*sampleDrinks.toTypedArray(),
    *sampleCandies.toTypedArray(), *addedProducts.toTypedArray()*/
/*  para seguir o conteudo da Alura, vamos manter todosProdutos como List e alterar ele de List
    para mutableList apenas no DAO  */
val todosProdutos: List<Product> = listOf(*sampleProducts.toTypedArray(),*sampleDrinks.toTypedArray(),
    *sampleCandies.toTypedArray(), *addedProducts.toTypedArray())

val sampleSections = mapOf("Recentemente adicionados" to addedProducts,
    "Salgados" to sampleProducts,
    "Doces" to sampleCandies, "Bebidas" to sampleDrinks)