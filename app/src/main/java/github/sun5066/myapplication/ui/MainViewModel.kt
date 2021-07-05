package github.sun5066.myapplication.ui

import android.app.Application
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.Transformations
import dagger.hilt.android.lifecycle.HiltViewModel
import github.sun5066.myapplication.R
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
    private val netDispatcher = newSingleThreadContext(name = "ServiceCall") // 스레드풀 디스패처
    private val factory = DocumentBuilderFactory.newInstance()

    private val _str = MutableLiveData("")
    private val _newCount = MutableLiveData(0)
    val str: LiveData<String> get() = _str
    val newCount: LiveData<String> get() = Transformations.map(_newCount) { "Found $it News" }


    fun init(str: String, navigator: BaseNavigator) {
        mNavigator = navigator
        _str.value = str

        asyncLoadNews()
    }

    val clickListener = View.OnClickListener { view ->
        when(view.id) {
            R.id.btn_show_keyboard -> mNavigator?.keyBoardSwitch(true)
            R.id.btn_hide_keyboard -> mNavigator?.keyBoardSwitch(false)
        }
    }

    private fun asyncLoadNews() = GlobalScope.launch(netDispatcher) {
        val headlines = fetchRssHeadlines()

        launch(Dispatchers.Main) { // 코루틴 스코프
            _newCount.value = headlines.size
        }
    }

    private fun fetchRssHeadlines(): List<String> {
        val builder = factory.newDocumentBuilder()
        val xml = builder.parse("https://www.npr.org/rss/rss.php?id=1001")
        val news = xml.getElementsByTagName("channel").item(0)
        return (0 until news.childNodes.length)
            .asSequence()
            .map { news.childNodes.item(it) }
            .filter { Node.ELEMENT_NODE == it.nodeType }
            .map { it as Element }
            .filter { "item" == it.tagName }
            .map {
                it.getElementsByTagName("title").item(0).textContent
            }
            .toList()
    }
}