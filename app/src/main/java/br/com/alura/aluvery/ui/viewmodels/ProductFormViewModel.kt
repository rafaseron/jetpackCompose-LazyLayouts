package br.com.alura.aluvery.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import br.com.alura.aluvery.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/* o StateHolder obrigatoriamente deve ser uma data class para que voce tenha acesso a .copy quando
    for atualizar a instancia de _uiState
    // como no exemplo: _uiState.value = _uiState.value.copy()
 */
data class ProductFormUiState(val onSaveClick: (Product) -> Unit = {},
                         val urlImagem: String = "", val nome: String = "", val preco: String = "", val descricao: String = "",
                         val priceError: Boolean = false) {

    fun urlIsBlank():Boolean{
        return urlImagem.isBlank()
    }

    fun nameIsBlank(): Boolean{
        return nome.isBlank()
    }

    fun priceIsBlank(): Boolean{
        return preco.isBlank()
    }

}



class ProductFormViewModel(): ViewModel(){

    private val _uiState: MutableStateFlow<ProductFormUiState> = MutableStateFlow(ProductFormUiState())
    val uiState = _uiState.asStateFlow()

    fun newUrlText(newValue: String){
        _uiState.value = _uiState.value.copy(urlImagem = newValue)
    }

    fun newNameText(newValue: String){
        _uiState.value = _uiState.value.copy(nome = newValue)
    }

    fun newPriceText(newValue: String){
        _uiState.value = _uiState.value.copy(preco = newValue)
    }

    fun isPriceError(newValue: Boolean){
        _uiState.value = _uiState.value.copy(priceError = newValue)
    }

    fun novaDescricao(newValue: String){
        _uiState.value = _uiState.value.copy(descricao = newValue)
    }

}