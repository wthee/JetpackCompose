package cn.wthee.jetpackcompose.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.wthee.jetpackcompose.api.WanAndroidRepository
import cn.wthee.jetpackcompose.data.ArticleData
import cn.wthee.jetpackcompose.data.ArticleInfo
import cn.wthee.jetpackcompose.data.BannerData
import kotlinx.coroutines.launch

/**
 * 文章 ViewModel
 */
class ArticleViewModel : ViewModel() {

    val banners = MutableLiveData<List<BannerData>>()
    val articles = MutableLiveData<List<ArticleInfo>>()

    fun getBanners() {
        viewModelScope.launch {
            val response = WanAndroidRepository.getBanner()
            if (response.errorCode == 0) {
                banners.postValue(response.data)
            }
        }
    }

    fun loadArticle(page: Int) {
        viewModelScope.launch {
            val response = WanAndroidRepository.getHomeArticleList(page)
            if (response.errorCode == 0) {
                articles.postValue(response.data.datas)
            }
        }
    }

}