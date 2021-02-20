package cn.wthee.jetpackcompose.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.wthee.jetpackcompose.api.WanAndroidRepository
import cn.wthee.jetpackcompose.data.ArticleData
import cn.wthee.jetpackcompose.data.ArticleInfo
import kotlinx.coroutines.launch

class ArticleViewModel: ViewModel() {

    val articles = MutableLiveData<List<ArticleInfo>>()

    fun loadArticle(page: Int){
        viewModelScope.launch {
            val response = WanAndroidRepository.getHomeArticleList(page)
            if(response.errorCode == 0){
                articles.postValue(response.data.datas)
            }
        }
    }

}