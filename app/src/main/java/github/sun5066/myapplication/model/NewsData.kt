package github.sun5066.myapplication.model

data class FeedData(
    val name: String, // New 사 이름
    val url: String
)

data class ArticleData(
    val feed: String,
    val title: String,
    val summary: String
)
