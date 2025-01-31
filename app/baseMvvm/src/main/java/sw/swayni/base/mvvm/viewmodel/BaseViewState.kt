package sw.swayni.base.mvvm.viewmodel

import sw.swayni.base.mvvm.enums.UiState

interface BaseViewState {
    val errorCode : Int?
    val errorMessage: String?
    val uiState: UiState
}