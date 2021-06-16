package github.sun5066.myapplication.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<VDB: ViewDataBinding> : AppCompatActivity() {
    lateinit var mBinding: VDB

    @LayoutRes
    abstract fun getLayoutResId(): Int
    abstract fun initDataBinding()
    abstract fun initView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, getLayoutResId())
        initDataBinding()
        initView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}