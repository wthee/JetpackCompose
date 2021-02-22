package cn.wthee.jetpackcompose.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import cn.wthee.jetpackcompose.ui.theme.Dimen
import cn.wthee.jetpackcompose.viewmodel.CommonViewModel

@Composable
fun BottomActionBar(commonViewModel: CommonViewModel, navController: NavController) {
    val home = commonViewModel.home.observeAsState(true).value

    ConstraintLayout(
        modifier = Modifier
            .background(MaterialTheme.colors.primary)
            .fillMaxWidth()
    ) {
        val (title, menu) = createRefs()

        //标题
        Text(
            text = "test",
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.surface,
            modifier = Modifier.constrainAs(title) {
                start.linkTo(parent.start,margin = Dimen.medium)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            })

        //功能键
        IconButton(
            modifier = Modifier.constrainAs(menu) {
                end.linkTo(parent.end)
            },
            onClick = {
                if (home) {
                    Log.e("debug", "home")
                } else {
                    navController.navigateUp()
                    commonViewModel.home.value = true
                }
            }) {
            Icon(
                if (home) Icons.Filled.Menu else Icons.Filled.KeyboardArrowLeft,
                "menu",
                tint = MaterialTheme.colors.surface
            )
        }
    }
    Row(
        modifier = Modifier.fillMaxWidth()
            .background(MaterialTheme.colors.primary)
    ) {

    }
}