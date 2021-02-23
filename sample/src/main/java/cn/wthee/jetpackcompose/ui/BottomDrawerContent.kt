package cn.wthee.jetpackcompose.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import cn.wthee.jetpackcompose.R
import cn.wthee.jetpackcompose.data.MenuData
import cn.wthee.jetpackcompose.navigation.Route
import cn.wthee.jetpackcompose.viewmodel.CommonViewModel

@Composable
fun BottomDrawerContent(commonViewModel: CommonViewModel, navController: NavController) {
    val spanCount = 2
    val data = arrayListOf<MenuData>()
    data.add(MenuData(R.string.app_name, R.drawable.logo, Route.Home))
    data.add(MenuData(R.string.app_name, R.drawable.logo, Route.Home))
    data.add(MenuData(R.string.app_name, R.drawable.logo, Route.Home))

    LazyColumn(
        Modifier.fillMaxWidth().fillMaxHeight()
    ) {
        itemsIndexed(data) { index, _ ->
            val endIndex = if (index + spanCount > data.size) data.size else index + spanCount
            if (index % spanCount == 0) {
                val rowData = data.subList(index, endIndex)
                Row(Modifier.fillMaxWidth(1f / spanCount * rowData.size)) {
                    for (item in rowData) {
                        MenuItem(commonViewModel, navController, item, Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
fun MenuItem(
    commonViewModel: CommonViewModel,
    navController: NavController,
    data: MenuData,
    modifier: Modifier
) {
    Column(
        modifier.clickable {
            //TODO 页面导航
            navController.navigate(data.route)
        },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painterResource(id = data.iconId), stringResource(id = data.titleId))
        Text(
            text = stringResource(id = data.titleId),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}