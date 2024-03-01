package br.com.alura.aluvery.dao

import androidx.compose.runtime.mutableStateListOf
import br.com.alura.aluvery.model.Product
import br.com.alura.aluvery.sampledata.todosProdutos

class ProductDao {
    companion object{
        private val mutableProdutos =  mutableStateListOf<Product>(*todosProdutos.toTypedArray())
    }
    fun save(produto: Product){
        mutableProdutos.add(produto)
    }
    fun listProducts() = mutableProdutos.toList()
}