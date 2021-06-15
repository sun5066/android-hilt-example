package github.sun5066.myapplication

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    application: Application,
    private val savedStateHandle: SavedStateHandle
) : AndroidViewModel(application) {

    private val _str = MutableLiveData("")
    val str: LiveData<String> get() = _str

    fun init(str: String) {
        _str.value = str
    }
}