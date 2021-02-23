package cn.wthee.jetpackcompose.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import cn.wthee.jetpackcompose.R
import cn.wthee.jetpackcompose.ui.theme.Dimen
import cn.wthee.jetpackcompose.viewmodel.CommonViewModel

@Composable
fun AppBar(commonViewModel: CommonViewModel, navController: NavController) {
    val appName = stringResource(id = R.string.app_name)
    val home = commonViewModel.home.observeAsState(true).value
    val title = commonViewModel.appBarTitle.observeAsState(appName).value

    BottomAppBar(Modifier.height(Dimen.appBarHeight)) {
        Text(title)

        Spacer(Modifier.weight(1f, true))

        IconButton(
            onClick = {
                if (home) {
                    //TODO 打开侧边栏
                } else {
                    navController.navigateUp()
                    commonViewModel.home.value = true
                    commonViewModel.appBarTitle.value = appName
                }
            }) {
            Icon(
                if (home) Icons.Filled.Menu else Icons.Filled.KeyboardArrowLeft,
                "menu",
                tint = MaterialTheme.colors.surface
            )
        }
    }
}