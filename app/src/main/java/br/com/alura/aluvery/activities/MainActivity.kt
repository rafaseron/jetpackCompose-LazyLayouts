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
import androidx.compose.ui.tooling.preview.Preview
import br.com.alura.aluvery.sampledata.sampleSections
import br.com.alura.aluvery.ui.screens.HomeScreen
import br.com.alura.aluvery.ui.theme.AluveryTheme
import br.com.alura.aluvery.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(onFABclick = {
                startActivity(Intent(this, ProductFormActivity::class.java))
            })
        }
    }
}
@Composable
fun App(onFABclick: () -> Unit = {}) {
    AluveryTheme {
        Surface {
            Scaffold(floatingActionButton = {
                FloatingActionButton(onClick = onFABclick ) {
                    Icon(painter = painterResource(id = R.drawable.baseline_add_24), contentDescription = "add")
                }
            }) {paddingValues ->
                Box(modifier = Modifier.padding(paddingValues)){
                    HomeScreen(sections = sampleSections)
                }

            }
            //SearchScreen(selections = sampleSections)
        }
    }
}