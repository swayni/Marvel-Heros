package sw.swayni.marvelheros.ui.main_activity.viewmodel

import sw.swayni.base.mvvm.enums.UiState
import sw.swayni.base.mvvm.viewmodel.BaseViewState

data class MainViewState(
    override val errorCode: Int? = null,
    override val errorMessage: String? = null,
    override val uiState: UiState = UiState.IDLE
) : BaseViewState
