package github.sun5066.myapplication.extention

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast

fun Activity.showLongToast(msg: String) {
    showToast(this, msg, Toast.LENGTH_LONG)
}

fun Activity.showShortToast(msg: String) {
    showToast(this, msg, Toast.LENGTH_SHORT)
}

private fun showToast(context: Context, msg: String, length: Int) {
    Toast.makeText(context, msg, length).show()
}