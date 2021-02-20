package cn.wthee.jetpackcompose.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import cn.wthee.jetpackcompose.R
import cn.wthee.jetpackcompose.data.ArticleInfo
import cn.wthee.jetpackcompose.ui.theme.Dimen
import cn.wthee.jetpackcompose.ui.theme.shapes
import cn.wthee.jetpackcompose.viewmodel.ArticleViewModel

@Composable
fun ArticleListItem(article: ArticleInfo) {
    MaterialTheme {
        val typography = MaterialTheme.typography
        Card(
            shape = shapes.large,
            modifier = Modifier.padding(Dimen.medium)
                .shadow(Dimen.cardShadow)
                .clickable {
                    //TODO 浏览文章
                    article.link
                }
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.background(Color.White)
            ) {

                //标题
                Text(
                    article.title,
                    style = typography.h6,
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
                        onClick = { /* doSomething() */ }
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
}

@Composable
fun ArticleList(viewModel: ArticleViewModel = viewModel()) {
    val data = viewModel.articles.observeAsState().value ?: listOf()
    LazyColumn(
        Modifier.fillMaxWidth(),
    ) {
        itemsIndexed(data) { index, chat ->
            ArticleListItem(chat)
        }
    }
}


@Preview
@Composable
fun PreviewArticle() {
    ArticleListItem(ArticleInfo.getDefault())
}