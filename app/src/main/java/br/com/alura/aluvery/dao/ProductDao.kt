package br.com.alura.aluvery.dao

import androidx.compose.runtime.mutableStateListOf
import br.com.alura.aluvery.model.Product
import br.com.alura.aluvery.sampledata.todosProdutos
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProductDao {
    companion object{
        private val mutableProdutos =  MutableStateFlow<List<Product>>(value = todosProdutos)
    }
    fun save(produto: Product){
        mutableProdutos.value = mutableProdutos.value + produto
    }
    fun listProducts(): StateFlow<List<Product>> {
        return mutableProdutos.asStateFlow()
    }
    //aqui convertemos a lista de 'Mutable'StateFlow para 'apenas' StateFlow
    //basicamente tipo um private set
}