package cn.wthee.jetpackcompose.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import cn.wthee.jetpackcompose.R
import cn.wthee.jetpackcompose.data.ArticleInfo
import cn.wthee.jetpackcompose.navigation.toArticleDetail
import cn.wthee.jetpackcompose.ui.theme.Dimen
import cn.wthee.jetpackcompose.ui.theme.shapes
import cn.wthee.jetpackcompose.viewmodel.ArticleViewModel
import cn.wthee.jetpackcompose.viewmodel.CommonViewModel

private var articlePage = 0
private var data = arrayListOf<ArticleInfo>()

/**
 * 文章列表
 */
@Composable
fun ArticleList(commonViewModel: CommonViewModel, navController: NavController) {

    val viewModel: ArticleViewModel = viewModel()
    data.addAll(viewModel.articles.observeAsState(listOf()).value)
    commonViewModel.loading.value = false
    val state = rememberLazyListState()

    LazyColumn(
        Modifier.fillMaxWidth().fillMaxHeight(),
        state = state
    ) {

        itemsIndexed(data) { index, chat ->
            if (index == 0) {
                Banner(commonViewModel, navController)
            }
            ArticleListItem(commonViewModel, navController, chat)
        }
        // 滑动时提前加载一页
        if (state.firstVisibleItemIndex == (articlePage - 1) * 20 || articlePage < 2) {
            viewModel.loadArticle(++articlePage)
        }

    }
}

/**
 * 文章列表项
 */
@Composable
fun ArticleListItem(
    commonViewModel: CommonViewModel,
    navController: NavController,
    article: ArticleInfo
) {
    val typography = MaterialTheme.typography
    Card(
        shape = shapes.large,
        modifier = Modifier.padding(Dimen.medium)
            .shadow(Dimen.cardShadow)
            .clickable {
                toArticleDetail(commonViewModel, navController, article.link)
            }
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.background(Color.White)
        ) {

            //标题
            Text(
                article.title,
                style = typography.subtitle1,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(Dimen.small)
                    .fillMaxWidth()
            )

            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                //日期
                Text(
                    article.niceShareDate,
                    style = typography.caption,
                    modifier = Modifier.weight(1f)
                        .padding(start = Dimen.small)
                )

                //名字
                Text(
                    if (article.shareUser.isNotEmpty()) {
                        stringResource(id = R.string.shareUser) + article.shareUser
                    } else {
                        stringResource(id = R.string.author) + article.author
                    },
                    style = typography.body2,
                    overflow = TextOverflow.Ellipsis,
                )

                IconButton(
                    onClick = {
                        toArticleDetail(commonViewModel, navController, article.link)
                    }
                ) {
                    Icon(
                        Icons.Filled.KeyboardArrowRight,
                        contentDescription = null
                    )
                }
            }

        }
    }
}
