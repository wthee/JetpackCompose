package cn.wthee.jetpackcompose.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class MenuData(
    @StringRes val titleId: Int,
    @DrawableRes val iconId: Int,
    val route: String

)
