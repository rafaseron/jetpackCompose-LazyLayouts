package br.com.alura.aluvery.ui.components

import androidx.compose.foundation.Image
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview


data class NavItem(val image: Painter, val label: String)
//caso voce queira trabalhar com a Route e Label diferentes, eh so refatorar a val labal para -> val route.
// dai eh so criar a val label novamente e preencher o que faltar durante o codigo.


@Composable
fun BottomAppBar(navList: List<NavItem> = emptyList(), itemClick:(NavItem) -> Unit = {}, selecionado: String = "") {

    NavigationBar{

        for (item in navList){
            NavigationBarItem(selected = item.label == selecionado , onClick = { itemClick(item) }, icon = { Image(painter = item.image, contentDescription = item.label)},
                label = { Text(text = item.label)})
        }

    }
}

@Preview
@Composable
private fun BottomAppBarPre() {
    BottomAppBar()
}