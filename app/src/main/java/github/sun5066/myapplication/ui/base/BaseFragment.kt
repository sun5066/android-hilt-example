package github.sun5066.myapplication.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver

abstract class BaseFragment<VDB: ViewDataBinding, VM: BaseViewModel>: Fragment(), LifecycleObserver {

    lateinit var mBinding: VDB
    abstract val mViewModel: VM

    @LayoutRes
    abstract fun getLayoutResId(): Int
    abstract fun initDataBinding()
    abstract fun initView()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutResId(), container, false)
        initDataBinding()

        lifecycle.addObserver(this)
        return mBinding.root
    }

    override fun onResume() {
        super.onResume()

        initView()
    }
}