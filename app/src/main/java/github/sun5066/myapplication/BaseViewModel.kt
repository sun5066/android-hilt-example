package github.sun5066.myapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {

    /**
     * 1. CompositeDisposable 관리 공용코드 추가
     * 2. fragments 관리 추가
     *
     * @since 21.06.16
     * */

}