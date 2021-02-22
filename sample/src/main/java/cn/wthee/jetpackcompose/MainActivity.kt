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
import cn.wthee.jetpackcompose.ui.BottomActionBar
import cn.wthee.jetpackcompose.ui.theme.Dimen
import cn.wthee.jetpackcompose.ui.theme.JetpackComposeTheme
import cn.wthee.jetpackcompose.ui.theme.shapes
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
        ConstraintLayout() {
            val (host, actionBar) = createRefs()
            Surface(modifier = Modifier.constrainAs(host) {
                top.linkTo(parent.top)
                bottom.linkTo(actionBar.top)
            }) {
                MainNavHost(navController)
            }
            Surface(modifier = Modifier.constrainAs(actionBar) {
                bottom.linkTo(parent.bottom)
            }) {
                //bar
                BottomActionBar(commonViewModel, navController)
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
                BannerCard()
                ArticleList(navController)
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

@Composable
fun BannerCard() {
    val image = painterResource(R.drawable.jetpack)
    MaterialTheme {
        Card(
            shape = shapes.large,
            modifier = Modifier.padding(Dimen.medium)
                .shadow(Dimen.cardShadow)
                .clickable {

                }
        ) {
            val imageModifier = Modifier
                .preferredHeight(180.dp)
                .fillMaxWidth()

            Image(
                painter = image,
                contentDescription = "图片",
                modifier = imageModifier,
                contentScale = ContentScale.Crop
            )
        }

    }
}
