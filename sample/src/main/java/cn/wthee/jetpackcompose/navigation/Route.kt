package cn.wthee.jetpackcompose.navigation

object Route {
    const val Home = "home"
    const val ARTICLE_URL = "article_url"
    const val ARTICLE = "article"
    const val ViewArticle = "${ARTICLE}/{$ARTICLE_URL}"
}