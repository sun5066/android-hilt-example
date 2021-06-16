package github.sun5066.myapplication.ui

import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import github.sun5066.myapplication.R
import github.sun5066.myapplication.databinding.ActivityMainBinding
import github.sun5066.myapplication.ui.base.BaseActivity

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val mViewModel: MainViewModel by viewModels()

    override fun getLayoutResId() = R.layout.activity_main

    override fun initDataBinding() {
        mBinding.viewModel = mViewModel
        mBinding.lifecycleOwner = this
    }

    override fun initView() {
        mViewModel.init("hello ViewModel")
    }

}