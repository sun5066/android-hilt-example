package github.sun5066.myapplication.ui.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {

    /**
     * 1. CompositeDisposable 관리 공용코드 추가
     * 2. fragments 관리 추가
     *
     * @since 21.06.16
     * */

    protected val mDisposable: CompositeDisposable by lazy { CompositeDisposable() }


}