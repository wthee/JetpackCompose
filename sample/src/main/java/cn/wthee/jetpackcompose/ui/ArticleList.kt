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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cn.wthee.jetpackcompose.data.ArticleData
import cn.wthee.jetpackcompose.ui.theme.Dimen
import cn.wthee.jetpackcompose.ui.theme.shapes
import cn.wthee.jetpackcompose.viewmodel.ArticleViewModel

@Composable
fun ArticleListItem(article: ArticleData) {
    MaterialTheme {
        val typography = MaterialTheme.typography
        Card(
            shape = shapes.large,
            modifier = Modifier.padding(Dimen.medium)
                .shadow(Dimen.cardShadow)
                .clickable {

                }
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.background(Color.White)
            ) {
                Text(
                    article.title,
                    style = typography.h5,
                    modifier = Modifier.padding(Dimen.small)
                        .fillMaxWidth()
                )

                Text(
                    article.summary,
                    style = typography.h6,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(Dimen.small)
                )

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
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
        Modifier.fillMaxWidth()
    ) {
        itemsIndexed(data) { index, chat ->
            ArticleListItem(chat)
        }
    }
}


@Preview
@Composable
fun PreviewArticleList() {
    ArticleList()
}