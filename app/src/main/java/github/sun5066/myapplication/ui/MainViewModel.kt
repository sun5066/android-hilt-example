package github.sun5066.myapplication.ui

import android.app.Application
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import github.sun5066.myapplication.R
import github.sun5066.myapplication.ui.base.BaseNavigator
import github.sun5066.myapplication.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel(application) {

    private var mNavigator: BaseNavigator? = null

    private val _str = MutableLiveData("")
    val str: LiveData<String> get() = _str

    fun init(str: String, navigator: BaseNavigator) {
        mNavigator = navigator
        _str.value = str
    }

    val clickListener = View.OnClickListener { view ->
        when(view.id) {
            R.id.btn_show_keyboard -> mNavigator?.keyBoardSwitch(true)
            R.id.btn_hide_keyboard -> mNavigator?.keyBoardSwitch(false)
        }
    }
}