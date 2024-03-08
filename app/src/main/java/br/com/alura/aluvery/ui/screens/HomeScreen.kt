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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.alura.aluvery.model.Product
import br.com.alura.aluvery.ui.components.ProductsSection
import br.com.alura.aluvery.ui.theme.AluveryTheme
import br.com.alura.aluvery.R
import br.com.alura.aluvery.dao.ProductDao
import br.com.alura.aluvery.sampledata.sampleCandies
import br.com.alura.aluvery.sampledata.sampleDrinks
import br.com.alura.aluvery.sampledata.sampleProducts
import br.com.alura.aluvery.ui.components.CardProductItem
import br.com.alura.aluvery.ui.viewmodels.HomeScreenUiState
import br.com.alura.aluvery.ui.viewmodels.HomeScreenViewModel

@Composable
fun HomeScreen(viewModel: HomeScreenViewModel) {

    var texto by rememberSaveable { mutableStateOf("") }

    val state = viewModel.uiState

    /*val state = remember (daoList, texto) {
        HomeScreenUiState(
            sections = mapSections,
            produtosPesquisados = searchedProducts,
            texto = texto,
            onSearchChange = {texto = it}
        )
    } */

    HomeScreen(stateHolder = state)
}
@Composable
fun HomeScreen(stateHolder: HomeScreenUiState){

    Column {
        val sections = stateHolder.sections

        OutlinedTextField(value = stateHolder.texto,
            onValueChange = stateHolder.onSearchChange /*
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
                for (product in stateHolder.produtosPesquisados){
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
            HomeScreen(HomeScreenViewModel())
        }
    }
}