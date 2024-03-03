package br.com.alura.aluvery.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.alura.aluvery.model.Product
import br.com.alura.aluvery.sampledata.sampleSections
import br.com.alura.aluvery.ui.components.ProductsSection
import br.com.alura.aluvery.ui.theme.AluveryTheme
import br.com.alura.aluvery.R
import br.com.alura.aluvery.ui.components.CardProductItem
import br.com.alura.aluvery.ui.state.HomeScreenUiState

@Composable
fun HomeScreen(stateHolder: HomeScreenUiState) {
    //COMO FAZER CASO VOCE QUEIRA PASSAR O STATE HOLDER CRIANDO UMA VARIAVEL COMO OBJETO:
      //val stateHolder = remember { HomeScreenUiState() }
        /* e ai pode tirar o stateHolder do parametro que funciona numa boa
         - so nao pode esquecer de ter o remember dentro da funcao Composable */

    val sections = stateHolder.mapSections

    Column {

        OutlinedTextField(value = stateHolder.texto,
            onValueChange = stateHolder.alterarTexto /*
            USANDO A FUNCAO FICARIA ASSIM:
            {newValue -> stateHolder.alterarTexto(newValue)}*/,

            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(start = 30.dp, top = 16.dp, end = 30.dp),
            shape = RoundedCornerShape(percent = 100),
            leadingIcon = {Image(painter = painterResource(id = R.drawable.baseline_search_24), contentDescription = "Search")},
            placeholder = { Text(text = "O que você procura?")},
            label = { Text(text = "Produto")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search))

        LazyColumn(Modifier.fillMaxSize(),
            contentPadding = PaddingValues(top = 16.dp,
                bottom = 90.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)) {

            if (stateHolder.textoIsBlank() == true){
                for (section in sections){
                    item {
                        ProductsSection(title = section.key, products = section.value)
                    }
                }
            }else{
                for (product in stateHolder.getSearchedProducts()){
                    item { CardProductItem(product = product) }
                }
            }
        }

    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    AluveryTheme {
        Surface {
            HomeScreen(stateHolder = HomeScreenUiState())
        }
    }
}