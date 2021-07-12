package github.sun5066.myapplication.ui

import android.util.Log
import android.view.View
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import github.sun5066.myapplication.R
import github.sun5066.myapplication.databinding.ActivityMainBinding
import github.sun5066.myapplication.extention.hideKeyboard
import github.sun5066.myapplication.extention.showKeyboard
import github.sun5066.myapplication.ui.base.BaseActivity
import github.sun5066.myapplication.ui.base.BaseNavigator

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(), BaseNavigator {

    private val mViewModel: MainViewModel by viewModels()

    override fun getLayoutResId() = R.layout.activity_main

    override fun initDataBinding() {
        mBinding.viewModel = mViewModel
        mBinding.lifecycleOwner = this
    }

    override fun initView() {
        mViewModel.init("hello ViewModel", this)
    }

    override fun keyBoardSwitch(isShow: Boolean) {
        with(mBinding.btnHideKeyboard) {
            if (isShow) showKeyboard()
            else hideKeyboard()
        }
    }

}