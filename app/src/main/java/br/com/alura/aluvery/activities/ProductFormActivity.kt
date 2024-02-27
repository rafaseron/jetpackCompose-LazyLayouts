package br.com.alura.aluvery.activities

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.material.Text
import br.com.alura.aluvery.ui.theme.AluveryTheme

class ProductFormActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent(){
            AluveryTheme {
                Surface {
                    Text(text = "Formulário de Produto")
                }
            }
        }
    }






}