package br.com.alura.aluvery.ui.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import br.com.alura.aluvery.dao.ProductDao
import br.com.alura.aluvery.model.Product
import br.com.alura.aluvery.ui.components.CardProductItem
import br.com.alura.aluvery.ui.components.ProductsSection

class HomeScreenUiState() {

    private val dao = ProductDao()

    var texto by mutableStateOf("")
    private set
    fun alterarTexto(novoValor: String){
        texto = novoValor
    }

    fun getSearchedProducts(): List<Product>{
        return dao.listProducts().filter { p ->
            p.name.contains(texto, ignoreCase = true) ||
                    p.description?.contains(texto, ignoreCase = true) ?: false
        }
    }

    fun textoIsBlank(): Boolean{
        if (texto.isBlank()){
            return true
        } else {
            return false
        }
    }

    //VAMOS TER QUE PREPARAR FUNCOES PARA TRABALHAR COM A ESSA LOGICA:
    /*
    if (texto.isBlank()){
                for (section in sections){
                    item {
                        ProductsSection(title = section.key, products = section.value)
                    }
                }
            }else{
                for (product in searchedProducts){
                    item { CardProductItem(product = product) }
                }
            }    */

}