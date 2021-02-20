package cn.wthee.jetpackcompose.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.wthee.jetpackcompose.data.ArticleData

class ArticleViewModel: ViewModel() {

    val articles = MutableLiveData<List<ArticleData>>()

    fun loadArticle(){
        val mockData = arrayListOf<ArticleData>()
        for(i in 0 ..20){
            mockData.add(ArticleData("标题 $i", "摘要.....$i"))
        }
        articles.value = mockData
    }





}