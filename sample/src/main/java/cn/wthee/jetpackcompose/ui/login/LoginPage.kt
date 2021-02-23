package cn.wthee.jetpackcompose.ui.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController
import cn.wthee.jetpackcompose.R
import cn.wthee.jetpackcompose.nav.toHome
import cn.wthee.jetpackcompose.ui.theme.Dimen
import cn.wthee.jetpackcompose.ui.theme.shapes
import cn.wthee.jetpackcompose.viewmodel.CommonViewModel

/**
 * 登录页面
 */
@ExperimentalAnimationApi
@Composable
fun LoginPage(commonViewModel: CommonViewModel, navController: NavController) {
    val userName = rememberSaveable { mutableStateOf("") }
    val psw = rememberSaveable { mutableStateOf("") }
    val typography = MaterialTheme.typography

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painterResource(id = R.drawable.logo), null)

        Text("玩Android 开放API", style = typography.subtitle2)

        TextField(
            value = userName.value,
            label = { Text("用户名") },
            modifier = Modifier.fillMaxWidth().padding(Dimen.medium),
            backgroundColor = MaterialTheme.colors.surface,
            leadingIcon = { Icon(Icons.Filled.Person, null) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            shape = shapes.small,
            onValueChange = {
                userName.value = it
            })

        TextField(
            value = psw.value,
            label = { Text("密码") },
            modifier = Modifier.fillMaxWidth().padding(Dimen.medium),
            backgroundColor = MaterialTheme.colors.surface,
            leadingIcon = { Icon(Icons.Filled.Lock, null) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            shape = shapes.small,
            onValueChange = {
                psw.value = it
            })

        AnimatedVisibility(visible = userName.value.isNotEmpty() && psw.value.isNotEmpty()) {
            Button(onClick = {
                //TODO 登录校验，登录成功 appbar 显示为用户名
                toHome(commonViewModel, navController, "")
            }, modifier = Modifier.padding(top = Dimen.large)) {
                Text(stringResource(id = R.string.menu_login))
                Icon(Icons.Filled.KeyboardArrowRight, null)
            }
        }

    }
}