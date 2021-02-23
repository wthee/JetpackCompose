package cn.wthee.jetpackcompose.ui

import android.net.http.SslError
import android.util.Log
import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import cn.wthee.jetpackcompose.viewmodel.CommonViewModel

/**
 * 文章详情
 */
@Composable
fun ArticleDetail(commonViewModel: CommonViewModel, url: String) {
    Log.e("debug", "$commonViewModel")
    commonViewModel.home.postValue(false)
    MaterialTheme() {
        Column(modifier = Modifier.fillMaxWidth()) {
            AndroidView(viewBlock = {
                WebView(it).apply {
                    //client
                    webViewClient = object : WebViewClient() {
                        override fun onPageFinished(view: WebView?, url: String?) {
                            commonViewModel.loading.value = false
                        }

                        override fun onReceivedSslError(
                            view: WebView?,
                            handler: SslErrorHandler?,
                            error: SslError?
                        ) {
                            handler?.proceed()
//                            super.onReceivedSslError(view, handler, error)
                        }
                    }
                    //设置
                    settings.apply {
                        javaScriptEnabled = true
                        allowFileAccess = true
                        allowContentAccess = true
                    }
                    //加载网页
                    loadUrl(url)
                }
            })
        }
    }

}