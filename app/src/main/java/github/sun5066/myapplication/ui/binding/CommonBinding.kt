package github.sun5066.myapplication.ui.binding

import android.view.View
import androidx.databinding.BindingAdapter

object CommonBinding {

    @BindingAdapter("clickListener")
    @JvmStatic
    fun setClickListener(view: View, clickListener: View.OnClickListener?) {
        clickListener?.let { view.setOnClickListener(clickListener) }
    }
}