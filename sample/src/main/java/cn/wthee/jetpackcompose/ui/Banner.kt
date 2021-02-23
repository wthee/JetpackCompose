package cn.wthee.jetpackcompose.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import cn.wthee.jetpackcompose.data.BannerData
import cn.wthee.jetpackcompose.navigation.toArticleDetail
import cn.wthee.jetpackcompose.viewmodel.ArticleViewModel
import cn.wthee.jetpackcompose.viewmodel.CommonViewModel
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun Banner(commonViewModel: CommonViewModel, navController: NavController) {
    val articleViewModel: ArticleViewModel = viewModel()
    articleViewModel.getBanners()
    val banners = articleViewModel.banners.observeAsState(listOf()).value
    LazyRow(
        Modifier.fillMaxWidth()
    ) {
        itemsIndexed(banners) { _, data ->
            BannerItem(commonViewModel, navController, data)
        }
    }
}

@Composable
fun BannerItem(commonViewModel: CommonViewModel, navController: NavController, data: BannerData) {
    Column(modifier = Modifier.clickable {
        toArticleDetail(commonViewModel, navController, data.url)
    }) {
        Text(text = data.title)
        //fixme 图片无法正常加载
        CoilImage(data = data.imagePath, modifier = Modifier.fillMaxWidth().height(200.dp)) {}
    }
}