package br.com.alura.aluvery.ui.components

import androidx.compose.foundation.Image
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.alura.aluvery.R


data class NavItem(val image: Painter, val label: String)


@Composable
fun BottomAppBar(navList: List<NavItem> = emptyList(), itemClick:(NavItem) -> Unit = {}, selecionado: String = "") {

    NavigationBar{

        for (item in navList){
            val selectedItem = item.label
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