package br.com.alura.aluvery.navigation

data class RouteShape (val route: String)

class Destination{
    val menu = RouteShape(route = "Menu")
    val adicionar = RouteShape(route = "Adicionar")
    val detailedProduct = RouteShape(route = "Detailed")
}

/*
sealed class AppDestination(val route: String){
    object Menu: AppDestination(route = "Menu")
    object Adicionar: AppDestination(route = "Adicionar")
}
// Como eu prefiro, estou utilizando a implementacao RouteShape + Destination. Mas fica aqui a AppDestination de sugestao
 */