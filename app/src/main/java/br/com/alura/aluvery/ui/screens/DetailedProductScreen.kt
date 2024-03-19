package br.com.alura.aluvery.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.com.alura.aluvery.model.Product
import br.com.alura.aluvery.ui.components.CardProductItem
import java.math.BigDecimal
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DetailedProduct(product: Product) {

    Box(modifier = Modifier.fillMaxSize(1f)){
        Column(modifier = Modifier. padding(all = 20.dp)) {
            CardProductItem(product = product)
        }
    }
}

@Preview
@Composable
private fun DetailedProductPreview() {
    DetailedProduct(
        Product(
        name = "Teste",
        price = BigDecimal.ZERO,
        image = null,
        description = null
    )
    )
}