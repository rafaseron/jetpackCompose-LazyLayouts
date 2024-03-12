package br.com.alura.aluvery.ui.viewmodels

import androidx.lifecycle.ViewModel
import br.com.alura.aluvery.dao.ProductDao
import br.com.alura.aluvery.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.lang.NumberFormatException
import java.math.BigDecimal

/* o StateHolder obrigatoriamente deve ser uma data class para que voce tenha acesso a .copy quando
    for atualizar a instancia de _uiState
    // como no exemplo: _uiState.value = _uiState.value.copy()
 */
data class ProductFormUiState(val urlImagem: String = "", val nome: String = "",
          val preco: String = "", val descricao: String = "", val priceError: Boolean = false)



class ProductFormViewModel: ViewModel(){

    private val _uiState: MutableStateFlow<ProductFormUiState> = MutableStateFlow(ProductFormUiState())
    val uiState = _uiState.asStateFlow()

    val finishedEvent = MutableStateFlow(false)
    private fun onSaveClick(product: Product){
        ProductDao().save(product)
        finishedEvent.value = true
    }

    fun productConverter(){
        if (uiState.value.nome.isNotBlank() || uiState.value.preco.isNotBlank()){
            val addProduct = Product(name = uiState.value.nome,
                price = uiState.value.preco.toBigDecimal(),
                image = uiState.value.urlImagem,
                description = uiState.value.descricao)

            onSaveClick(addProduct)
        }
    }


    fun newUrlText(newValue: String){
        _uiState.value = _uiState.value.copy(urlImagem = newValue)
    }

    fun newNameText(newValue: String){
        _uiState.value = _uiState.value.copy(nome = newValue)
    }

    fun newPriceText(newValue: String) {
        val convertedValue = convertToBigDecimal(newValue)

        _uiState.value = _uiState.value.copy(preco = convertedValue.toString())
    }

    private fun convertToBigDecimal(newValue: String): BigDecimal {

        return try {
            BigDecimal(newValue.replace(",", "."))
        } catch (e: NumberFormatException){
            BigDecimal.ZERO
        }.also {
            isPriceError(it == BigDecimal.ZERO)
        }

    }

    private fun isPriceError(newValue: Boolean){
        _uiState.value = _uiState.value.copy(priceError = newValue)
    }

    fun novaDescricao(newValue: String){
        _uiState.value = _uiState.value.copy(descricao = newValue)
    }

}