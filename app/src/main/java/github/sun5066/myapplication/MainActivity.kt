package github.sun5066.myapplication

import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import github.sun5066.myapplication.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val mViewModel: TodoViewModel by viewModels()

    override fun getLayoutResId() = R.layout.activity_main

    override fun initDataBinding() {
        mBinding.viewModel = mViewModel
        mBinding.lifecycleOwner = this
    }

    override fun initView() {
        mViewModel.init("hello ViewModel")
    }

}