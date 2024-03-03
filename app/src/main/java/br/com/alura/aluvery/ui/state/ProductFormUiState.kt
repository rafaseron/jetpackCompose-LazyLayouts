package br.com.alura.aluvery.ui.state

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.math.BigDecimal

class ProductFormUiState {

    var urlImagem by mutableStateOf("")
    private set

    fun urlIsBlank():Boolean{
        return urlImagem.isBlank()
    }

    fun newUrlText(newValue: String){
        urlImagem = newValue
    }


    var nome by  mutableStateOf("")
        private set
    fun newNameText(newValue: String){
        nome = newValue
    }
    fun nameIsBlank(): Boolean{
        return nome.isBlank()
    }
    var preco by mutableStateOf("")
        private set

    fun newPriceText(newValue: String){
        preco = newValue
    }
    fun priceIsBlank(): Boolean{
        return preco.isBlank()
    }
    var priceError by mutableStateOf(false)
    fun isPriceError(newValue: Boolean){
        priceError = newValue
    }


    var descricao by mutableStateOf("")
        private set
    fun novaDescricao(newValue: String){
        descricao = newValue
    }


}