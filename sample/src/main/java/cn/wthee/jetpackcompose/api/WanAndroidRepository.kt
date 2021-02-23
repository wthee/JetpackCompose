package cn.wthee.jetpackcompose.api

object WanAndroidRepository {

    val service = ServiceCreater.create(WanAndroidService::class.java)

    suspend fun getHomeArticleList(page: Int) = service.getHomeArticleList(page)
    suspend fun getBanner() = service.getBanner()
}