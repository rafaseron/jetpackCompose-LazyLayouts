package br.com.alura.aluvery.ui.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import br.com.alura.aluvery.dao.ProductDao
import br.com.alura.aluvery.model.Product
import br.com.alura.aluvery.sampledata.sampleCandies
import br.com.alura.aluvery.sampledata.sampleDrinks
import br.com.alura.aluvery.sampledata.sampleProducts

class HomeScreenUiState(val sections: Map<String, List<Product>> = emptyMap(),
                        val produtosPesquisados: List<Product> = emptyList(),
                        val texto: String = "",
                        val onSearchChange: (String) -> Unit = {}) {

    /*private val dao = ProductDao()

    val mapSections:Map<String, List<Product>> = mapOf(
        "Todos produtos" to dao.listProducts(),
        "Salgados" to sampleProducts,
        "Doces" to sampleCandies,
        "Bebidas" to sampleDrinks
    )

    var texto by mutableStateOf("")
    private set

    //ESSA FUNCAO FAZ A MESMA COISA QUE A PROPERTY ABAIXO DELA:
    /*
    fun alterarTexto(novoValor: String){
        texto = novoValor
    }   */
    //ESSA PROPERTY FAZ A MESMA COISA QUE A FUNCAO ACIMA
     val alterarTexto: (String) -> Unit = {novoValor -> texto = novoValor}

    fun getSearchedProducts(): List<Product>{
        return dao.listProducts().filter { p ->
            p.name.contains(texto, ignoreCase = true) ||
                    p.description?.contains(texto, ignoreCase = true) ?: false
        }
    }

     */

    fun textoIsBlank(): Boolean{
        return texto.isBlank()
    }

}