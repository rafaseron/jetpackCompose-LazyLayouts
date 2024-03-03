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

    private val dao = ProductDao()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(onFABclick = {
                startActivity(Intent(this, ProductFormActivity::class.java)) },
                conteudo = {

                    //CASO HAJA RECOMPOSICAO NESSA TELA, DEVE-SE MANDAR O STATE ASSIM:
                    /*
                    state = remember { HomeScreenUiState }
                     */
                    HomeScreen(stateHolder = HomeScreenUiState())})
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