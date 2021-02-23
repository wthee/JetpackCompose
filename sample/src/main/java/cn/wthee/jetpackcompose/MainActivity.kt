package cn.wthee.jetpackcompose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import cn.wthee.jetpackcompose.navigation.Route
import cn.wthee.jetpackcompose.navigation.Route.ARTICLE_URL
import cn.wthee.jetpackcompose.ui.ArticleDetail
import cn.wthee.jetpackcompose.ui.ArticleList
import cn.wthee.jetpackcompose.ui.AppBar
import cn.wthee.jetpackcompose.ui.Banner
import cn.wthee.jetpackcompose.ui.theme.Dimen
import cn.wthee.jetpackcompose.ui.theme.JetpackComposeTheme
import cn.wthee.jetpackcompose.ui.theme.shapes
import cn.wthee.jetpackcompose.viewmodel.ArticleViewModel
import cn.wthee.jetpackcompose.viewmodel.CommonViewModel

private lateinit var commonViewModel: CommonViewModel

class MainActivity : AppCompatActivity() {
    @ExperimentalComposeUiApi
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Home()
        }

    }
}

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun Home() {
    val navController = rememberNavController()
    commonViewModel = viewModel()
    JetpackComposeTheme() {
        Column() {
            Surface(modifier = Modifier.weight(1f, true)) {
                MainNavHost(navController)
            }
            Surface() {
                //bottom app bar
                AppBar(commonViewModel, navController)
            }
        }
    }
}

@Composable
fun MainNavHost(navController: NavHostController) {
    //配置导航
    NavHost(navController, Route.Home) {
        //首页
        composable(Route.Home) {

            Column() {
                Banner(commonViewModel, navController)
                ArticleList(commonViewModel, navController)
            }
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
