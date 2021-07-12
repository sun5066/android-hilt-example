package github.sun5066.myapplication.ui.binding

import android.view.View
import androidx.databinding.BindingAdapter
import github.sun5066.myapplication.extention.hideKeyboard
import github.sun5066.myapplication.extention.showKeyboard

object CommonBinding {

    @BindingAdapter("clickListener")
    @JvmStatic
    fun setClickListener(view: View, clickListener: View.OnClickListener?) {
        clickListener?.let { view.setOnClickListener(clickListener) }
    }

    @BindingAdapter(value = ["keyboardListener", "isKeyboardShow"])
    @JvmStatic
    fun setKeyboardListener(view: View, isKeyboardShow: Boolean) {
        view.apply {
            if (isKeyboardShow) showKeyboard()
            else hideKeyboard()
        }
    }
}