package sw.swayni.marvelheros.ui.main_activity.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import sw.swayni.base.mvvm.enums.UiState
import sw.swayni.base.mvvm.viewmodel.BaseViewModel

//@HiltViewModel
class MainViewModel constructor() : BaseViewModel<MainViewState, MainAction>(MainViewState()) {
    override fun cleanState(): MainViewState = state.copy(uiState = UiState.IDLE, errorCode = null, errorMessage = null)

    override fun onReduceState(viewAction: MainAction): MainViewState = when(viewAction){
        else ->{
            state.copy(uiState = UiState.IDLE, errorCode = null, errorMessage = null)
        }
    }

}