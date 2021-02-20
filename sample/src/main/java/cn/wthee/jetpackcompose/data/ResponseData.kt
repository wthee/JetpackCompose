package cn.wthee.jetpackcompose.data

data class ResponseData<T>(
    val data: T,
    val errorCode: Int,
    val errorMsg: String
)
