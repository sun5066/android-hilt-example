package github.sun5066.myapplication

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(private val mSavedStateHandle: SavedStateHandle): ViewModel() {

    private val _str = MutableLiveData("")
    val str: LiveData<String> get() = _str

    fun init(str: String) {
        Log.d("123", "str: $str")
        _str.postValue(str)
    }
}