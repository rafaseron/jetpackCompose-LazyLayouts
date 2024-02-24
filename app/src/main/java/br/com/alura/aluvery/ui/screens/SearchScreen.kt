package br.com.alura.aluvery.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.alura.aluvery.model.Product
import br.com.alura.aluvery.sampledata.sampleSections
import br.com.alura.aluvery.R
import br.com.alura.aluvery.ui.components.CardProductItem
import br.com.alura.aluvery.ui.components.ProductsSection
import br.com.alura.aluvery.ui.components.SearchSelection
import br.com.alura.aluvery.ui.theme.AluveryTheme

@Composable
fun SearchScreen (selections: Map<String, List<Product>>) {

    Column(modifier = Modifier
        .fillMaxSize(1f)
        .padding(all = 16.dp)) {

        var texto by remember {mutableStateOf(value = "")}

        OutlinedTextField(value = texto,
            onValueChange = {newValue -> texto = newValue},
            placeholder = { Text(text = "O que procura?")},
            modifier = Modifier
                .fillMaxWidth(1f),
            shape = RoundedCornerShape(percent = 100),
            leadingIcon = { Image(painter = painterResource(id = R.drawable.baseline_search_24), contentDescription = null)},
            label = { Text(text = "Produto")})

        /* Trabalhar com o SearchSelection faz o tratamento de
        um Map<String, List<Produt>> -> List<Product>
         Que por sua vez dentro do SearchSelection tem um 'for'
         que trata o List<Product> para objetos de Product */
        //CODIGO USANDO O SEARCHSELECTION:
        /*for (selection in selections){
            SearchSelection(cardlist = selection.value)
        }*/

        //CONSEGUIMOS FAZER SEM O SEARCHSELECTION, MAS TEMOS
        //QUE GARANTIR QUE VAMOS FAZER ESSE TRATAMENTO DE DADOS:
        LazyColumn(modifier = Modifier, contentPadding = PaddingValues(all = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)){


            //Esse primeiro 'for' transforma card em um List<Product>
            for (card in selections.values){

                /*Colocando um 'for' dentro desse primeiro 'for',
                transformamos o List<Product> em <Product> */

                /*for (product in card){
                    item { CardProductItem(product = product)}
                }*/

                //OU PODEMOS FAZER USANDO O ITEMS( ):
                /* Já que por debaixo dos panos ele funciona
                 igual um 'for' dentro de outro 'for' */
                items(card){
                    p ->
                    CardProductItem(product = p)
                }
            }

        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun SearchScreenPreview () {
    Surface {
        AluveryTheme {
            SearchScreen(sampleSections)
        }
    }
}