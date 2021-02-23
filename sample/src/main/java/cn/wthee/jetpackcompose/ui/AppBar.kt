package cn.wthee.jetpackcompose.ui

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import cn.wthee.jetpackcompose.R
import cn.wthee.jetpackcompose.ui.theme.Dimen
import cn.wthee.jetpackcompose.viewmodel.CommonViewModel

@Composable
fun AppBar(commonViewModel: CommonViewModel, navController: NavController, drawerState: BottomDrawerState) {
    val home = commonViewModel.home.observeAsState(true).value
    val title = commonViewModel.appBarTitle.observeAsState("").value

    BottomAppBar(Modifier.height(Dimen.appBarHeight)) {

        IconButton(
            onClick = {},
            modifier = Modifier.padding(start = Dimen.small, end = Dimen.small)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo",
                tint = MaterialTheme.colors.surface,
                modifier = Modifier.size(Dimen.iconSize)
            )
        }

        Text(
            text = title,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f, true)
        )

        IconButton(
            onClick = {
                if (home) {
                    drawerState.open {  }
                } else {
                    navController.navigateUp()
                    commonViewModel.home.value = true
                    commonViewModel.appBarTitle.value = ""
                }
            },
            modifier = Modifier.padding(start = Dimen.small, end = Dimen.small)
        ) {
            Icon(
                if (home) Icons.Filled.Menu else Icons.Filled.KeyboardArrowLeft,
                "menu",
                tint = MaterialTheme.colors.surface
            )
        }
    }
}