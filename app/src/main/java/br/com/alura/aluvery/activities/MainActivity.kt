package br.com.alura.aluvery.activities

import android.content.Intent
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
                } ?: "menu"

                Log.e("Acesso", "Acesso -> $acesso")

                mutableStateOf(acesso)
            }



            fun routeFlow(navItem: NavItem){
                if (navItem.label == "Menu"){
                    navController.navigate(route = "Menu")
                }else{
                    if (navItem.label == "Adicionar"){
                        navController.navigate(route = "Adicionar")
                    }
                }
            }

            App(onFABclick = {
                navController.navigate(route = "add")
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
                    productViewModel.navLogic = { navController.navigate(route = "Menu") }
                    //adicionando a rota do navLogic ANTES de MANDAR este ViewModel para a referente tela

                    NavHost(navController = navController, startDestination = "Menu", builder = {
                        composable("Menu") { HomeScreen(viewModel) }
                        composable("Adicionar") { ProductFormScreen(viewModel = productViewModel) }
                    })



                           },
                itemClick = {
                    navItem ->
                    itemSelecionado = navItem.label
                    routeFlow(navItem)
                }, selecionado = itemSelecionado)
        }
    }
}
@Composable
fun App(onFABclick: () -> Unit = {}, conteudo: @Composable () -> Unit = {}, itemClick:(NavItem) -> Unit = {}, selecionado: String = "") {

    val navList = listOf<NavItem>(
        NavItem(image = painterResource(id = R.drawable.baseline_restaurant_menu_24), label = "Menu"),
        NavItem(image = painterResource(id = R.drawable.baseline_library_add_24), label = "Adicionar"),
        NavItem(image = painterResource(id = R.drawable.baseline_search_24), label = "Search")
    )

    AluveryTheme {
        Surface {
            Scaffold(floatingActionButton = {
                FloatingActionButton(onClick = onFABclick ) {
                    Icon(painter = painterResource(id = R.drawable.baseline_add_24), contentDescription = "add")
                }
            }, bottomBar = { BottomAppBar(navList = navList, itemClick = itemClick, selecionado = selecionado) }) {paddingValues ->
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