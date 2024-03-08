package br.com.alura.aluvery.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.alura.aluvery.dao.ProductDao
import br.com.alura.aluvery.model.Product
import br.com.alura.aluvery.sampledata.sampleCandies
import br.com.alura.aluvery.sampledata.sampleDrinks
import br.com.alura.aluvery.sampledata.sampleProducts
import kotlinx.coroutines.launch

data class HomeScreenUiState(val sections: Map<String, List<Product>> = emptyMap(),
                        val produtosPesquisados: List<Product> = emptyList(),
                        val texto: String = "",
                        val onSearchChange: (String) -> Unit = {}){
    fun textoIsBlank(): Boolean{
        return texto.isBlank()
    }
}
class HomeScreenViewModel: ViewModel(){

    private val dao = ProductDao()
    val daoList = dao.listProducts()

    var uiState: HomeScreenUiState by mutableStateOf(HomeScreenUiState(
        onSearchChange = {
            uiState = uiState.copy(texto = it, produtosPesquisados = searchedProducts(it) )
        } )
    )
        private set

    init {
        //como o .collect é uma Coroutine, precisamos colocar dentro desse Escopo para que seja executado
        viewModelScope.launch {

            //usa-se o collect para conseguir pegar o valor de daoList (que tem o type de StateFlow<List<Product>>)
            daoList.collect{listaColetada ->
                uiState = uiState.copy(
                    sections = mapOf(
                        "Todos produtos" to listaColetada,
                        "Salgados" to sampleProducts,
                        "Doces" to sampleCandies,
                        "Bebidas" to sampleDrinks)
                )
            }

        }
    }

    private fun getSearchedProducts(texto: String): List<Product>{
        return daoList.filter { p ->
            p.name.contains(texto, ignoreCase = true) ||
                    p.description?.contains(texto, ignoreCase = true) ?: false
        }
    }

    private fun searchedProducts(texto: String): List<Product> {
        return if (uiState.texto.isNotBlank()){
            getSearchedProducts(texto)
        }else{
            emptyList()
        }
    }


    fun textoIsBlank(): Boolean{
        return uiState.texto.isBlank()
    }
}