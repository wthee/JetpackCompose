package cn.wthee.jetpackcompose.viewmodel

import androidx.compose.ui.res.stringResource
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.wthee.jetpackcompose.R

class CommonViewModel : ViewModel() {

    val loading = MutableLiveData(true)
    val home = MutableLiveData(true)
    val appBarTitle = MutableLiveData("WanAndroid")

}