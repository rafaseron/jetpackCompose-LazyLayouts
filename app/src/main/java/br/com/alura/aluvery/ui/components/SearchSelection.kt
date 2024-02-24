package br.com.alura.aluvery.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.alura.aluvery.model.Product
import br.com.alura.aluvery.sampledata.todosProdutos
import br.com.alura.aluvery.ui.theme.AluveryTheme

@Composable
fun SearchSelection(cardlist: List<Product>) {

    LazyColumn(modifier = Modifier, contentPadding = PaddingValues(all = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)){

        /*items(cardlist){
            p ->
            CardProductItem(product = p)
        }*/

        for (card in cardlist){
            item {CardProductItem(product = card)}
        }

    }

}

@Preview
@Composable
fun SearchSelectionPreview() {
    Surface {
        AluveryTheme {
            SearchSelection(todosProdutos)
        }
    }
}