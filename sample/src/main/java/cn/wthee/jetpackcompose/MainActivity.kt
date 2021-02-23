package cn.wthee.jetpackcompose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import cn.wthee.jetpackcompose.navigation.Route
import cn.wthee.jetpackcompose.navigation.Route.ARTICLE_URL
import cn.wthee.jetpackcompose.ui.AppBar
import cn.wthee.jetpackcompose.ui.ArticleDetail
import cn.wthee.jetpackcompose.ui.ArticleList
import cn.wthee.jetpackcompose.ui.BottomDrawerContent
import cn.wthee.jetpackcompose.ui.theme.JetpackComposeTheme
import cn.wthee.jetpackcompose.ui.theme.shapes
import cn.wthee.jetpackcompose.viewmodel.CommonViewModel

private lateinit var commonViewModel: CommonViewModel

class MainActivity : AppCompatActivity() {
    @ExperimentalMaterialApi
    @ExperimentalComposeUiApi
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Home()
        }

    }
}

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun Home() {
    val navController = rememberNavController()
    val drawerState = rememberBottomDrawerState(BottomDrawerValue.Closed)
    commonViewModel = viewModel()
    val loading = commonViewModel.loading.observeAsState(initial = true).value
    JetpackComposeTheme() {
        BottomDrawerLayout(
            drawerState = drawerState,
            drawerShape = shapes.small,
            drawerContent = {
                BottomDrawerContent(commonViewModel, navController)
            }) {
            Column() {
                Surface(modifier = Modifier.weight(1f, true)) {
                    MainNavHost(navController)
                }
                AnimatedVisibility(visible = loading) {
                    LinearProgressIndicator(Modifier.fillMaxWidth())
                }
                //bottom app bar
                AppBar(commonViewModel, navController, drawerState)
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun MainNavHost(navController: NavHostController) {
    //配置导航
    NavHost(navController, Route.Home) {
        //首页
        composable(Route.Home) {
            ArticleList(commonViewModel, navController)
        }
        //文章详情页
        composable(
            Route.ViewArticle,
            arguments = listOf(navArgument(ARTICLE_URL) { type = NavType.StringType })
        ) {
            ArticleDetail(commonViewModel, it.arguments?.getString(ARTICLE_URL) ?: "")
        }

    }
}
