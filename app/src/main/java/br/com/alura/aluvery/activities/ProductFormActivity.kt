package br.com.alura.aluvery.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.alura.aluvery.model.Product
import br.com.alura.aluvery.sampledata.addedProducts
import br.com.alura.aluvery.ui.theme.AluveryTheme
import coil.compose.AsyncImage
import java.lang.NumberFormatException
import java.math.BigDecimal
import br.com.alura.aluvery.R
import br.com.alura.aluvery.dao.ProductDao
import br.com.alura.aluvery.sampledata.todosProdutos
import br.com.alura.aluvery.ui.viewmodels.ProductFormViewModel
import br.com.alura.aluvery.ui.viewmodels.ProductFormUiState

class ProductFormActivity: ComponentActivity() {

    private val dao = ProductDao()
    //val stateHolder by mutableStateOf(ProductFormUiState())
    //DESSA FORMA ACIMA, MANTEMOS O ESTADO DO STATE HOLDER DENTRO DA ACTIVITY
    //ISSO PROTEGE O ESTADO EM RECOMPOSICOES E RECRIACOES DE TELA
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent(){
            AluveryTheme {
                Surface {
                    ProductFormScreen(viewModel = ProductFormViewModel() , onClick = {p ->
                        dao.save(p)
                        finish()
                    })
                }
            }
        }
    }
}

//STATEFUL COMPOSABLE
@Composable
fun ProductFormScreen(onClick: (Product) -> Unit = {}, viewModel: ProductFormViewModel) {
    val onSaveClick = onClick

    /*val urlImagem by rememberSaveable { mutableStateOf("") }
    val nome by rememberSaveable { mutableStateOf("") }
    val preco by rememberSaveable { mutableStateOf("") }
    val descricao by rememberSaveable { mutableStateOf("") }
    val priceError by rememberSaveable { mutableStateOf(false) }

    val state = remember (urlImagem, nome, preco, descricao, priceError){
        ProductFormUiState(
            onSaveClick = onSaveClick,
            url = urlImagem,
            name = nome,
            price = preco,
            discription = descricao,
            erroNoPreco = priceError)
    }*/

    val state by viewModel.uiState.collectAsState()

    ProductFormScreen(stateHolder = state, viewModel = viewModel, onClick = onSaveClick)
}

//STATELESS COMPOSABLE
@Composable
fun ProductFormScreen(stateHolder: ProductFormUiState, viewModel: ProductFormViewModel, onClick: (Product) -> Unit = {}) {
    val onSaveClick = onClick

    /*
    Para não utilizar Stateful -> Stateless, seu Composable deveria receber apenas:
        -> fun ProductFormScreen(viewModel: ProductFormViewModel, onClick: (Product) -> Unit = {})

    Dentro do Composable você faria essas atribuicoes:
    -> val onSaveClick = onClick
    -> val uiState by viewModel.uiState.collectAsState()

    e assim utilizar uiState como seu StateHolder e viewModel: ProductFormViewModel

     */

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)) {

        Text(text = "Criando o Produto", fontWeight = FontWeight(400),
            fontSize = 28.sp,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp))

        if (stateHolder.urlIsBlank()){} else{
            AsyncImage(model = stateHolder.urlImagem, contentDescription = null, modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(start = 16.dp, end = 16.dp), contentScale = ContentScale.Fit,
                error = painterResource(id = R.drawable.baseline_image_search_24),
                placeholder = painterResource(id = R.drawable.placeholder))
        }

        OutlinedTextField(value = stateHolder.urlImagem, onValueChange = {newValue ->
            viewModel.newUrlText(newValue) },
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth(1f)
                .height(54.dp),
            placeholder = { Text(text = "Url da imagem")},
            label = { Text(text = "Url da imagem") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Uri,
                imeAction = ImeAction.Next)
        )

        OutlinedTextField(value = stateHolder.nome, onValueChange = {newValue ->
            viewModel.newNameText(newValue) },
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

        OutlinedTextField(value = stateHolder.preco, onValueChange = {newValue ->
            val convertedValue = try {
                BigDecimal(newValue.replace(",", "."))
            } catch (e: NumberFormatException){
                BigDecimal.ZERO
            viewModel.isPriceError(true)
            }
            viewModel.newPriceText(convertedValue.toString()) },
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth(1f)
                .height(54.dp),
            placeholder = { Text(text = "Preço")},
            label = { Text(text = "Preço") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Next), isError = stateHolder.priceError )
        if (stateHolder.priceError){
            Text(text = "Preço deve ser em decimal", color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 16.dp))
        }else{}

        OutlinedTextField(value = stateHolder.descricao, onValueChange = {newValue ->
            viewModel.novaDescricao(newValue) },
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
                BigDecimal(stateHolder.preco)
            }catch (e: NumberFormatException){
                BigDecimal.ZERO
            }

            if (stateHolder.nameIsBlank() || stateHolder.priceIsBlank()){}
            else{
                val addProduct = Product(name = stateHolder.nome,
                    price = convertedPrice,
                    image = stateHolder.urlImagem,
                    description = stateHolder.descricao)
                //de acordo com mudancas no codigo do app pela Alura -> fará por DAO
                //addedProducts.add(addProduct)
                //todosProdutos.add(addProduct)
                viewModel.onSaveClick(addProduct)
                Log.e("ProductFormActivity", "Adicionado agora -> $addProduct")
                Log.e("ProductFormActivity", "addedProducts -> $addedProducts")
                Log.e("ProductFormActivity", "todosProdutos -> $todosProdutos")
                Log.e("ProductFormActivity", "DaoList -> ${ProductDao().listProducts()}")}
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
            //ProductFormScreen(onClick = {})
        }
    }
}