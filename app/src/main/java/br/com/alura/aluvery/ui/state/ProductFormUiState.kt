package br.com.alura.aluvery.ui.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

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
    var descricao by mutableStateOf("")


}