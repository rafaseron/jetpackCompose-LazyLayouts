package br.com.alura.aluvery.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import br.com.alura.aluvery.ui.screens.HomeScreen
import br.com.alura.aluvery.ui.theme.AluveryTheme
import br.com.alura.aluvery.R
import br.com.alura.aluvery.dao.ProductDao
import br.com.alura.aluvery.model.Product
import br.com.alura.aluvery.sampledata.sampleCandies
import br.com.alura.aluvery.sampledata.sampleDrinks
import br.com.alura.aluvery.sampledata.sampleProducts
import br.com.alura.aluvery.ui.state.HomeScreenUiState

class MainActivity : ComponentActivity() {

    val state by mutableStateOf(HomeScreenUiState())
    //MELHOR ESCOPO PARA COLOCAR O MUTABLESTATE DE UM STATEHOLDER !!!
    /*
    Colocando aqui em cima, ele consegue lembrar o Estado quando vai para outra tela
    e retorna na mesma tela depois.
    Além de tornar a implementacao 1000x mais facil, já que nao precisa trazer nada
    de 'texto' e etc para dentro da MainActivity para passar para
    um parametro de uma funcao remember
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(onFABclick = {
                startActivity(Intent(this, ProductFormActivity::class.java)) },
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
                    HomeScreen(stateHolder = state)})
        }
    }
}
@Composable
fun App(onFABclick: () -> Unit = {}, conteudo: @Composable () -> Unit = {}) {
    AluveryTheme {
        Surface {
            Scaffold(floatingActionButton = {
                FloatingActionButton(onClick = onFABclick ) {
                    Icon(painter = painterResource(id = R.drawable.baseline_add_24), contentDescription = "add")
                }
            }) {paddingValues ->
                Box(modifier = Modifier.padding(paddingValues)){
                    conteudo()
                }

            }
        }
    }
}