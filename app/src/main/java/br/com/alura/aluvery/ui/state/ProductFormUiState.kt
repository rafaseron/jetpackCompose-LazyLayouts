package br.com.alura.aluvery.ui.state

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import br.com.alura.aluvery.model.Product
import java.math.BigDecimal

class ProductFormUiState(var onSaveClick: (Product) -> Unit = {},
    url: String, name: String, price: String, discription: String,
    erroNoPreco: Boolean) {

    //Para Armazenar os Estados do Composable Stateful e conseguir mandar esses Estados
    // atualizados pro Stateless - Estados modificados aqui no StateHolder - , precisamos armazenar
    // essas variaveis em uma Variavel com acesso ao método by mutableStateOf(variavelAntiga)

    //ou seja, se fossemos apenas propagar os Estados (sem fazer Alteracoes aqui dentro do StateHolder), pode
    //ser que apenas declarar as Variaveis no parametro da classe StateHolder já fosse o suficiente
    // (igual acontece com a 'var onSaveClick'

    var urlImagem by mutableStateOf(url)
    var nome by mutableStateOf(name)
    var preco by mutableStateOf(price)
    var descricao by mutableStateOf(discription)
    var priceError by mutableStateOf(erroNoPreco)



    fun urlIsBlank():Boolean{
        return urlImagem.isBlank()
    }

    fun newUrlText(newValue: String){
        urlImagem = newValue
        Log.d("ProductFormUiState", "novo url imagem: $urlImagem")
    }

    fun newNameText(newValue: String){
        nome = newValue
    }

    fun nameIsBlank(): Boolean{
        return nome.isBlank()
    }

    fun newPriceText(newValue: String){
        preco = newValue
    }

    fun priceIsBlank(): Boolean{
        return preco.isBlank()
    }
    fun isPriceError(newValue: Boolean){
        priceError = newValue
    }

    fun novaDescricao(newValue: String){
        descricao = newValue
    }



}