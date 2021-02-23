package cn.wthee.jetpackcompose.api

import cn.wthee.jetpackcompose.data.ArticleData
import cn.wthee.jetpackcompose.data.BannerData
import cn.wthee.jetpackcompose.data.ResponseData
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * https://www.wanandroid.com/
 */
interface WanAndroidService {

    /**
     * 根据 page 页码（从 0 开始），获取首页文章
     * https://www.wanandroid.com/article/list/0/json
     */
    @GET("/article/list/{page}/json")
    suspend fun getHomeArticleList(@Path("page") page: Int): ResponseData<ArticleData>

    /**
     * 获取 banner
     * https://www.wanandroid.com/banner/json
     */
    @GET("/banner/json")
    suspend fun getBanner(): ResponseData<List<BannerData>>
}