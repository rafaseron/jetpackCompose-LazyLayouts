package br.com.alura.aluvery.activities

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.alura.aluvery.model.Product
import br.com.alura.aluvery.sampledata.addedProducts
import br.com.alura.aluvery.ui.theme.AluveryTheme
import coil.compose.AsyncImage
import java.lang.NumberFormatException
import java.math.BigDecimal
import br.com.alura.aluvery.R

class ProductFormActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent(){
            AluveryTheme {
                Surface {
                    ProductFormScreen()
                }
            }
        }
    }
}

@Composable
fun ProductFormScreen() {
    var urlImagem by remember { mutableStateOf("") }
    var nome by remember { mutableStateOf("") }
    var preco by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }
    var priceError by remember { mutableStateOf(false) }

    //TESTES DE DADOS
    /*
    urlImagem = "https://img.freepik.com/fotos-gratis/hamburguer-delicioso-isolado-no-fundo-branco_125540-3368.jpg"
    nome = "João"
    preco = "22,99"
    descricao = LoremIpsum(300).values.first()*/

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)) {

        Text(text = "Criando o Produto", fontWeight = FontWeight(400),
            fontSize = 28.sp,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp))

        if (urlImagem.isBlank()){} else{
            AsyncImage(model = urlImagem, contentDescription = null, modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(start = 16.dp, end = 16.dp), contentScale = ContentScale.Fit,
                error = painterResource(id = R.drawable.baseline_image_search_24),
                placeholder = painterResource(id = R.drawable.placeholder))
        }

        OutlinedTextField(value = urlImagem, onValueChange = {newValue ->
            urlImagem = newValue },
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth(1f)
                .height(54.dp),
            placeholder = { Text(text = "Url da imagem")},
            label = { Text(text = "Url da imagem") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Uri,
                imeAction = ImeAction.Next)
        )

        OutlinedTextField(value = nome, onValueChange = {newValue ->
            nome = newValue },
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth(1f)
                .height(54.dp),
            placeholder = { Text(text = "Nome")},
            label = { Text(text = "Nome") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
                capitalization = KeyboardCapitalization.Words)
        )

        OutlinedTextField(value = preco, onValueChange = {newValue ->
            val convertedValue = try {
                BigDecimal(newValue.replace(",", "."))
            }catch (e: NumberFormatException){BigDecimal.ZERO
            priceError = true}
            preco = convertedValue.toString() },
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth(1f)
                .height(54.dp),
            placeholder = { Text(text = "Preço")},
            label = { Text(text = "Preço") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Next), isError = priceError )
        if (priceError == true){
            Text(text = "Preço deve ser em decimal", color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 16.dp))
        }

        OutlinedTextField(value = descricao, onValueChange = {newValue ->
            descricao = newValue },
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth(1f)
                .heightIn(min = 100.dp),
            placeholder = { Text(text = "Descrição")},
            maxLines = Int.MAX_VALUE,
            label = { Text(text = "Descrição") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Default,
                capitalization = KeyboardCapitalization.Sentences)
        )

        Button(onClick = {
            //garantir que o price fique em BigDecimal
            val convertedPrice = try {
                BigDecimal(preco)
            }catch (e: NumberFormatException){
                BigDecimal.ZERO
            }

            if (nome.isBlank() || preco.isBlank()){}
            else{
                         val addProduct = Product(name = nome,
                             price = convertedPrice,
                             image = urlImagem,
                             description = descricao)
                         addedProducts.add(addProduct)
                Log.e("ProductFormActivity", "Adicionado agora -> $addProduct")
                Log.e("ProductFormActivity", "Todos -> $addedProducts") }
                         },
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                )
                .height(36.dp)
                .fillMaxWidth(1f)) {
            Text(text = "Salvar")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ProductFormScreenPreview() {
    AluveryTheme {
        Surface {
            ProductFormScreen()
        }
    }
}