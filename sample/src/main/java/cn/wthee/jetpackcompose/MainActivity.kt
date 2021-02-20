package cn.wthee.jetpackcompose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.Dimension
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cn.wthee.jetpackcompose.ui.ArticleList
import cn.wthee.jetpackcompose.ui.theme.Dimen
import cn.wthee.jetpackcompose.ui.theme.JetpackComposeTheme
import cn.wthee.jetpackcompose.ui.theme.shapes
import cn.wthee.jetpackcompose.viewmodel.ArticleViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                Column() {
                    IntroCard()
                    ArticleList()
                }
            }
        }

        val viewModel: ArticleViewModel by viewModels()
        viewModel.loadArticle()
    }
}

@Composable
fun IntroCard() {
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


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeTheme {
        Column() {
            IntroCard()
            ArticleList()
        }
    }
}