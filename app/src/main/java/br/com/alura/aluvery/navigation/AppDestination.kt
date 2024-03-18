package br.com.alura.aluvery.navigation

sealed class AppDestination(val route: String){
    object Menu: AppDestination(route = "Menu")
    object Adicionar: AppDestination(route = "Adicionar")
}