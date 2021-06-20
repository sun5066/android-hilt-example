package github.sun5066.myapplication.extention

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun Activity.showKeyboard(view: View) {
    val inputManagerManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManagerManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
}

fun Activity.hideKeyboard(view: View) {
    currentFocus?.let {
        val inputManagerManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManagerManager.hideSoftInputFromWindow(it.windowToken, 0)
    }
}