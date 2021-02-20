package cn.wthee.jetpackcompose.data

data class ArticleData(
    val curPage: Int,
    val datas: List<ArticleInfo>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)

data class ArticleInfo(
    val apkLink: String,
    val audit: Int,
    val author: String,
    val canEdit: Boolean,
    val chapterId: Int,
    val chapterName: String,
    val collect: Boolean,
    val courseId: Int,
    val desc: String,
    val descMd: String,
    val envelopePic: String,
    val fresh: Boolean,
    val host: String,
    val id: Int,
    val link: String,
    val niceDate: String,
    val niceShareDate: String,
    val origin: String,
    val prefix: String,
    val projectLink: String,
    val publishTime: Long,
    val realSuperChapterId: Int,
    val selfVisible: Int,
    val shareDate: Long,
    val shareUser: String,
    val superChapterId: Int,
    val superChapterName: String,
    val tags: List<Tag>,
    val title: String,
    val type: Int,
    val userId: Int,
    val visible: Int,
    val zan: Int
) {
    companion object {
        fun getDefault(): ArticleInfo {
            return ArticleInfo(
                "", 0, "", false, 0, "", true, 0, "", "", "",
                true, "", 0, "", "", "2020-12-21 11:11:11", "", "", "", 0, 0,
                0, 1L, "admin", 0, "", listOf(), "预览", 0, 0, 0, 0
            )

        }
    }

}

data class Tag(
    val name: String,
    val url: String
)