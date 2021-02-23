package cn.wthee.jetpackcompose.nav

import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import cn.wthee.jetpackcompose.viewmodel.CommonViewModel

object Route {
    const val Home = "home"
    const val Login = "login"
    const val ARTICLE_URL = "article_url"
    const val ARTICLE = "article"
    const val ViewArticle = "${ARTICLE}/{$ARTICLE_URL}"
}


fun toArticleDetail(
    commonViewModel: CommonViewModel,
    navController: NavController,
    url: String
) {
    commonViewModel.loading.value = true
    //浏览文章
    navController.navigate("${Route.ARTICLE}/$url")
    commonViewModel.appBarTitle.value = "文章详情"
}

fun toHome(
    commonViewModel: CommonViewModel,
    navController: NavController,
    title: String =""
){
    commonViewModel.home.value = true
    commonViewModel.appBarTitle.value = title
    navController.navigateUp()
}