package cn.wthee.jetpackcompose.navigation

import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import cn.wthee.jetpackcompose.data.ArticleInfo
import cn.wthee.jetpackcompose.viewmodel.CommonViewModel

object Route {
    const val Home = "home"
    const val ARTICLE_URL = "article_url"
    const val ARTICLE = "article"
    const val ViewArticle = "${ARTICLE}/{$ARTICLE_URL}"
}


fun toArticleDetail(
    commonViewModel: CommonViewModel,
    navController: NavController,
    url: String
) {
    //浏览文章
    navController.navigate("${Route.ARTICLE}/$url")
    commonViewModel.appBarTitle.value = "文章详情"
}