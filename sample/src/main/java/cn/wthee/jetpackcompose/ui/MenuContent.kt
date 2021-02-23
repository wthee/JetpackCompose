package cn.wthee.jetpackcompose.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
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
import cn.wthee.jetpackcompose.nav.Route
import cn.wthee.jetpackcompose.viewmodel.CommonViewModel

private const val SPAN_COUNT = 2
private val data = arrayListOf(
    MenuData(R.string.menu_login, R.drawable.logo, Route.Login)
)

@ExperimentalMaterialApi
@Composable
fun MenuContent(
    commonViewModel: CommonViewModel,
    navController: NavController,
    state: ModalBottomSheetState
) {

    LazyColumn(
        Modifier.fillMaxWidth().fillMaxHeight()
    ) {
        itemsIndexed(data) { index, _ ->
            val endIndex = if (index + SPAN_COUNT > data.size) data.size else index + SPAN_COUNT
            if (index % SPAN_COUNT == 0) {
                val rowData = data.subList(index, endIndex)
                Row(Modifier.fillMaxWidth(1f / SPAN_COUNT * rowData.size)) {
                    for (item in rowData) {
                        MenuItem(commonViewModel, navController, item, Modifier.weight(1f), state)
                    }
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun MenuItem(
    commonViewModel: CommonViewModel,
    navController: NavController,
    data: MenuData,
    modifier: Modifier,
    state: ModalBottomSheetState
) {
    val title = stringResource(id = data.titleId)
    Column(
        modifier.clickable {
            //TODO 页面导航
            state.hide()
            commonViewModel.home.value = false
            navController.navigate(data.route)
        },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painterResource(id = data.iconId), title)
        Text(
            text = title,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}