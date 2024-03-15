package br.com.alura.aluvery.activities

import android.content.Intent
import android.os.Bundle
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.alura.aluvery.ui.screens.HomeScreen
import br.com.alura.aluvery.ui.theme.AluveryTheme
import br.com.alura.aluvery.R
import br.com.alura.aluvery.ui.components.BottomAppBar
import br.com.alura.aluvery.ui.viewmodels.HomeScreenViewModel
import br.com.alura.aluvery.ui.viewmodels.ProductFormViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val navController = rememberNavController()

            App(onFABclick = {
                navController.navigate(route = "add")
                /*startActivity(Intent(this, ProductFormActivity::class.java))*/
                // Esse exemplo de refatoração para usar o Navigation no lugar de startActivity é para mostrar que o FAB pode:
                // 1. abrir nova Activity
                // 2. abrir uma rota de Navegação

                //Caso tu fosse implementar por Navegação, daria para elevar o click do Botao de Salvar do ProductFormScreen para adicionar que depois de Salvar
                // faria então da Navegação de Volta para por exemplo a rota "menu"
                // então ficaria:
                /*
                    //logica de Save do botão
                    navContoller.navigate(route = "menu")
                 */

                // Lembrando que essa rota de navegacao poderia ser recebida pelo ViewModel referente a tela durante instancia do mesmo
                //Exemplo:
                /*
                    val navLogic = navContoller.navigate(route = "menu")

                    val viewModel: HomeScreenViewModel by viewModels()
                    NavHost(navController, startDestination){
                      composable("menu") { HomeScreen(viewModel(navLogic)) }
                    }

                 */

                //NA REAL ESSA SERIA A IDEIA, MAS É UM POUCO MAIS COMPLEXO QUE ISSO, JÁ QUE VIEWMODEL NAO RECEBE PARAMETROS DIRETAMENTE
                // POR EXEMPLO, daria para tentar passar o navLogic pro ProductFormUiState, para que o ViewModel pegue de lá

                             },
                conteudo = {

                    //CASO HAJA RECOMPOSICAO NESSA TELA, DEVE-SE MANDAR O STATE ASSIM:
                    /*
                    state = remember(//parametros a serem lembrados) { HomeScreenUiState() }
                    Atencao -> se utilizar o remember dessa forma, tem que levar tudo de 'texto' para essa Activity
                    e passar 'texto' para um dos parametros que serao levados em consideracao no remember

                    Também, deverá ter o Map/Lista aqui dentro dessa Activity e passar para o parametro de remember
                    (para computar tambem muddancas de lista -> acarretar em mudanca de UI)

                    ai sim, seria só chamar a HomeScreen abaixo dessa forma:
                    HomeScreen(stateHolder = state)

                    Caso o HomeScreenUiState solicitar parametros, eles sao enviados no remember acima, nos
                    parametros do construtor de HomeScreenUiState() que está dentro do remember(){ }
                     */
                    val viewModel: HomeScreenViewModel by viewModels()
                    //HomeScreen(viewModel)

                    val productViewModel: ProductFormViewModel by viewModels()

                    NavHost(navController = navController, startDestination = "menu", builder = {
                        composable("menu") { HomeScreen(viewModel) }
                        composable("add") { ProductFormScreen(viewModel = productViewModel) }
                    })



                           },
                menuClick = { navController.navigate("menu") },
                addClick = { navController.navigate("add") })
        }
    }
}
@Composable
fun App(onFABclick: () -> Unit = {}, conteudo: @Composable () -> Unit = {}, menuClick: () -> Unit = {}, addClick: () -> Unit = {}) {
    AluveryTheme {
        Surface {
            Scaffold(floatingActionButton = {
                FloatingActionButton(onClick = onFABclick ) {
                    Icon(painter = painterResource(id = R.drawable.baseline_add_24), contentDescription = "add")
                }
            }, bottomBar = { BottomAppBar(menuClick = menuClick, addClick = addClick) }) {paddingValues ->
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