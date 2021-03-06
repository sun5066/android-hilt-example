package github.sun5066.myapplication.ui

import android.app.Application
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import github.sun5066.myapplication.R
import github.sun5066.myapplication.model.ArticleData
import github.sun5066.myapplication.model.FeedData
import github.sun5066.myapplication.ui.base.BaseNavigator
import github.sun5066.myapplication.ui.base.BaseViewModel
import kotlinx.coroutines.*
import org.w3c.dom.Element
import org.w3c.dom.Node
import javax.inject.Inject
import javax.xml.parsers.DocumentBuilderFactory

@HiltViewModel
@ObsoleteCoroutinesApi
class MainViewModel @Inject constructor(
    application: Application,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel(application) {

    private var mNavigator: BaseNavigator? = null
    private val mIoDispatcher = newFixedThreadPoolContext(2, "IO") // 스레드풀 디스패처
    private val factory = DocumentBuilderFactory.newInstance()
    private val feeds = listOf(
        FeedData("npr", "https://www.npr.org/rss/rss.php?id=1001"),
        FeedData("cnn", "http://rss.cnn.com/rss/cnn_topstories.rss"),
        FeedData("fox", "http://feeds.foxnews.com/foxnews/politics?format=xml"),
        FeedData("inv", "htt:throwable")
    )

    private val _failed = MutableLiveData("")
    private val _news = MutableLiveData("")
    val failed: LiveData<String> get() = _failed
    val news: LiveData<String> get() = _news


    fun init(str: String, navigator: BaseNavigator) {
        mNavigator = navigator

        asyncLoadNews()
    }

    val clickListener = View.OnClickListener { view ->
        when(view.id) {
            R.id.btn_show_keyboard -> mNavigator?.keyBoardSwitch(true)
            R.id.btn_hide_keyboard -> mNavigator?.keyBoardSwitch(false)
        }
    }

    @ExperimentalCoroutinesApi
    private fun asyncLoadNews() = GlobalScope.launch {
        val requests = mutableListOf<Deferred<Sequence<ArticleData>>>()
        feeds.mapTo(requests) { asyncFetchArticles(it, mIoDispatcher) }

        requests.forEach {
            it.join()
        }
        val articles = requests
            .filter { !it.isCancelled }
            .flatMap { it.getCompleted() }

        val failed = requests
            .filter { it.isCancelled }

        launch(Dispatchers.Main) { // 코루틴 스코프
            _news.value = "Found ${articles.size} News in ${requests.size} feeds"

            failed.size.takeIf { it > 0 }.apply {
                _failed.value = "failed: ${failed.size}"
            }
        }
    }

    private fun asyncFetchArticles(feed: FeedData, dispatcher: CoroutineDispatcher) = GlobalScope.async(dispatcher) {
            val builder = factory.newDocumentBuilder()
            val xml = builder.parse(feed.url)
            val news = xml.getElementsByTagName("channel").item(0)
            (0 until news.childNodes.length)
                .asSequence()
                .map { news.childNodes.item(it) }
                .filter { Node.ELEMENT_NODE == it.nodeType }
                .map { it as Element }
                .filter { "item" == it.tagName }
                .map {
                    val title = it.getElementsByTagName("title")
                        .item(0)
                        .textContent

                    val summary = it.getElementsByTagName("description")
                        .item(0)
                        .textContent

                    ArticleData(feed.name, title, summary)
                }
        }
}