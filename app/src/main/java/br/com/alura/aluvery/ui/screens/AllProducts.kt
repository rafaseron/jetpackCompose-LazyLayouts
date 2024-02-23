package br.com.alura.aluvery.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.alura.aluvery.sampledata.todosProdutos
import br.com.alura.aluvery.ui.components.ProductItem

@Composable
fun AllProducts() {

    LazyVerticalGrid(modifier = Modifier.fillMaxSize(1f),
        contentPadding = PaddingValues(top = 16.dp,
            start = 16.dp, end = 16.dp, bottom = 90.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        columns = GridCells.Fixed(2)) {

        item(span = { GridItemSpan(maxLineSpan) }){
            Text(text = "Todos produtos", fontSize = 30.sp,
                fontWeight = FontWeight(400),
                modifier = Modifier.offset(x = 16.dp))
        }

        /*for (produto in todosProdutos){
            item {
                ProductItem(product = produto) }
        }*/

        items(todosProdutos){p ->
            ProductItem(product = p)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun AllProductsPreview() {
    AllProducts()
}