package br.com.alura.aluvery.dao

import br.com.alura.aluvery.sampledata.todosProdutos

class ProductDao {
    companion object{
        private val mutableProdutos = todosProdutos.toMutableList()
    }
    fun listProducts() = mutableProdutos.toList()
}