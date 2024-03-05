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

    fun textoIsBlank(): Boolean{
        return texto.isBlank()
    }

    /* ESSA CLASSE FUNCIONA COMO UMA CLASSE 'COBAIA', ONDE OS DADOS
        SAO PASSADOS VIA PARAMETRO PARA SEREM PUXADOS DEPOIS
        NA FUNCAO COMPOSABLE QUE EXIGIR UM OBJETO DESSA CLASSE 'HOMESCREENUISTATE'
        COMO UM PARAMETRO PARA CHAMA-LO

     ESSE É O CASO DA SEGUNDA FUNCAO @COMOSABLE HomeScreen

     A Arquitetura é essa:
     HomeScreen(List<Product) -> passa os dados para HomeScreenUiState, sua classe Cobaia
        -> São recebidos em HomeScreen(objeto: HomeScreenUiState)
     */







    //CODIGOS ANTIGOS PARA REVISAR SE PRECISO:
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

}