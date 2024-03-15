package br.com.alura.aluvery.ui.components

import androidx.compose.foundation.Image
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.alura.aluvery.R

@Composable
fun BottomAppBar(menuClick: () -> Unit = {}, addClick: () -> Unit = {}, selected: Boolean = false) {

    val menuIcone = painterResource(id = R.drawable.baseline_restaurant_menu_24)
    val addIcone = painterResource(id = R.drawable.baseline_library_add_24)

    val menuIcon = remember { menuIcone }
    val addIcon = remember { addIcone }

    NavigationBar{
        NavigationBarItem(icon = {
                Image(
                painter = menuIcon,
                contentDescription = "Menu",
            )
        }, label = {
            Text("Home")
        }, onClick = menuClick, selected = selected)

        NavigationBarItem(selected = selected, onClick = addClick, icon = {
            Image(painter = addIcon, contentDescription = null)
        })


    }
}

@Preview
@Composable
private fun BottomAppBarPre() {
    BottomAppBar()
}