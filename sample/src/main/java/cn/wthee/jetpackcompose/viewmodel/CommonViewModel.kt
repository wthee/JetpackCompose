package cn.wthee.jetpackcompose.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CommonViewModel : ViewModel() {

    val loading = MutableLiveData(true)
    val home = MutableLiveData(true)

}