package br.com.alura.aluvery.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.alura.aluvery.ui.screens.HomeScreen
import br.com.alura.aluvery.ui.theme.AluveryTheme
import br.com.alura.aluvery.R
import br.com.alura.aluvery.navigation.Destination
import br.com.alura.aluvery.ui.components.BottomAppBar
import br.com.alura.aluvery.ui.components.NavItem
import br.com.alura.aluvery.ui.viewmodels.HomeScreenViewModel
import br.com.alura.aluvery.ui.viewmodels.ProductFormViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val navController = rememberNavController()

            val currentBackStack by navController.currentBackStackEntryAsState()
            val currentDestination = currentBackStack?.destination

            var itemSelecionado by remember(currentDestination) {

                val acesso = currentDestination?.let {
                    p ->
                    p.route.toString()
                } ?: Destination().menu.route

                Log.e("Acesso", "Acesso -> $acesso")

                mutableStateOf(acesso)
            }

            fun showBottomBar(): Boolean{
                currentDestination?.let {
                    p ->
                    when (p.route.toString()){
                        "Menu" -> return true
                        else -> return false
                    }
                } ?: return false
            }

            fun showFAB(): Boolean{
                currentDestination?.let {
                        p ->
                    when (p.route.toString()){
                        "Menu" -> return true
                        else -> return false
                    }
                } ?: return false
            }

            fun showTopBar(): Boolean{
                currentDestination?.let {
                        p ->
                    when (p.route.toString()){
                        "Menu" -> return true
                        else -> return false
                    }
                } ?: return false
            }



            fun routeFlow(navItem: NavItem){
                navController.navigate(route = navItem.label){
                    launchSingleTop = true
                    popUpTo(route = navItem.label)
                }
            }

            App(showBottomBar = showBottomBar(), showFAB = showFAB()/*, showTopBar = showTopBar()*/, onFABclick = {
                navController.navigate(route = Destination().adicionar.route)
                /*startActivity(Intent(this, ProductFormActivity::class.java))*/
                // Esse exemplo de refatoração para usar o Navigation no lugar de startActivity é para mostrar que o FAB pode:
                // 1. abrir nova Activity
                // 2. abrir uma rota de Navegação

                //Caso tu fosse implementar por Navegação, daria para Elevar uma funcao para ser executada pós click do Botao de Salvar do ProductFormScreen
                // faria então da Navegação de Volta para por exemplo a rota "menu"
                //Então no caso, tu tem que passar essa rota diretamente pro ViewModel

                //Exemplo:
                /*
                    val viewModel: HomeScreenViewModel by viewModels()
                   viewModel.navLogic = { navController.navigate(route = "menu") }  <-- MUDAMOS o valor desta funcao que é recebida por parametros
                   // ANTES de enviar o mesmo viewModel pro Composable da tela correspondente

                    NavHost(navController, startDestination){
                      composable("menu") { HomeScreen(viewModel) }
                    }

                 */

                // LEMBRANDO QUE AGORA O VIEWMODEL DEVE RECEBR NO SEU PARAMETRO: (var navLogic: () -> Unit = {} )
                // e ai é só ele executar navLogic() quando desejado

                             },
                conteudo = {

                    val viewModel: HomeScreenViewModel by viewModels()

                    val productViewModel: ProductFormViewModel by viewModels()
                    productViewModel.navLogic = { navController.navigate(route = Destination().menu.route) }
                    //adicionando a rota do navLogic ANTES de MANDAR este ViewModel para a referente tela

                    NavHost(navController = navController, startDestination = Destination().menu.route, builder = {
                        composable(Destination().menu.route) { HomeScreen(viewModel) }
                        composable(Destination().adicionar.route) { ProductFormScreen(viewModel = productViewModel) }
                    })



                           },
                itemClick = {
                    navItem ->
                    itemSelecionado = navItem.label
                    routeFlow(navItem)
                    //daria para passar a rota direto com: navControler.navigate(route = navItem.label)
                }, selecionado = itemSelecionado)
        }
    }
}
@Composable
fun App(onFABclick: () -> Unit = {}, conteudo: @Composable () -> Unit = {}, itemClick:(NavItem) -> Unit = {}, selecionado: String = "", showBottomBar: Boolean = false,
        showFAB: Boolean = false) {

    val navList = listOf(
        NavItem(image = painterResource(id = R.drawable.baseline_restaurant_menu_24), label = Destination().menu.route),
        NavItem(image = painterResource(id = R.drawable.baseline_library_add_24), label = Destination().adicionar.route),
        NavItem(image = painterResource(id = R.drawable.baseline_search_24), label = "Search")
    )

    AluveryTheme {
        Surface {
            Scaffold(floatingActionButton = {
                if (showFAB){
                    FloatingActionButton(onClick = onFABclick ) {
                        Icon(painter = painterResource(id = R.drawable.baseline_add_24), contentDescription = "add")
                    }
                }
            }, bottomBar = { if(showBottomBar) {
                BottomAppBar(navList = navList, itemClick = itemClick, selecionado = selecionado)
                    }
                }
            ) {paddingValues ->
                Box(modifier = Modifier.padding(paddingValues)){
                    conteudo()
                    //esse conteudo() refere-se aoo 'restante' do conteudo que estaria aqui inteiro do Corpo do Composable
                    /*
                    EXPLICACAO: foi feito isso para que se ele Eleve o conteudo do Corpo do Composable para que possa ser adicionado posteriormente
                    mais conteudo a este Composable. Neste caso, sua reutilizacao de adicionar novos elementos ao conteudo ja existente
                    deste Composable 'App()' é feito na MainActivity

                                                                                                                                */
}

}
}
}
}