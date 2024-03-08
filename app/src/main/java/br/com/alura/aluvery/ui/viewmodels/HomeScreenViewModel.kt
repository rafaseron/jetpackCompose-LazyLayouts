package br.com.alura.aluvery.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import br.com.alura.aluvery.dao.ProductDao
import br.com.alura.aluvery.model.Product
import br.com.alura.aluvery.sampledata.sampleCandies
import br.com.alura.aluvery.sampledata.sampleDrinks
import br.com.alura.aluvery.sampledata.sampleProducts

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

    val mapSections:Map<String, List<Product>> = mapOf(
        "Todos produtos" to daoList,
        "Salgados" to sampleProducts,
        "Doces" to sampleCandies,
        "Bebidas" to sampleDrinks
    )

    var uiState: HomeScreenUiState by mutableStateOf(HomeScreenUiState(
        onSearchChange = {
            uiState = uiState.copy(texto = it, produtosPesquisados = searchedProducts(it))},
        sections = mapSections)

    )
        private set

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