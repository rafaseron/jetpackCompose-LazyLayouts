package br.com.alura.aluvery.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.alura.aluvery.dao.ProductDao
import br.com.alura.aluvery.model.Product
import br.com.alura.aluvery.sampledata.sampleCandies
import br.com.alura.aluvery.sampledata.sampleDrinks
import br.com.alura.aluvery.sampledata.sampleProducts
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
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

    private var _uiState: MutableStateFlow<HomeScreenUiState> = MutableStateFlow(value = HomeScreenUiState())
    var uiState = _uiState.asStateFlow()

    init {
        _uiState.update {
            state ->
            state.copy(onSearchChange = {
                _uiState.value = _uiState.value.copy(texto = it,
                    produtosPesquisados = searchedProducts(it))
                }
            )
        }

        //como o .collect é uma Coroutine, precisamos colocar dentro desse Escopo para que seja executado
        viewModelScope.launch {

            //usa-se o collect para conseguir pegar o valor de daoList (que tem o type de StateFlow<List<Product>>)
            daoList.collect{listaColetada ->
                _uiState.value = _uiState.value.copy(
                    sections = mapOf(
                        "Todos produtos" to listaColetada,
                        "Salgados" to sampleProducts,
                        "Doces" to sampleCandies,
                        "Bebidas" to sampleDrinks),
                    //forcando uma nova busca do filtro após adicionar novos elementos a Lista
                    produtosPesquisados = searchedProducts(_uiState.value.texto)
                )
            }

        }
    }

    private fun getSearchedProducts(texto: String): List<Product>{
        return daoList.value.filter { p ->
            p.name.contains(texto, ignoreCase = true) ||
                    p.description?.contains(texto, ignoreCase = true) ?: false
        }
    }

    //funcao que utiliza a getSearchedProduts
    //funcao dela é fazer o filtro apenas quando o texto não está em branco
    private fun searchedProducts(texto: String): List<Product> {
        return if (_uiState.value.texto.isNotBlank()){
            getSearchedProducts(texto)
        }else{
            emptyList()
        }
    }


    fun textoIsBlank(): Boolean{
        return _uiState.value.texto.isBlank()
    }
}