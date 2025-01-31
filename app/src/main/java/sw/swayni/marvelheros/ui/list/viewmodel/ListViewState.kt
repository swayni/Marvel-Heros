package sw.swayni.marvelheros.ui.list.viewmodel

import sw.swayni.base.mvvm.enums.UiState
import sw.swayni.base.mvvm.viewmodel.BaseViewState
import sw.swayni.marvelheros.data.model.Character


data class ListViewState(
    val offset : Int = 0,
    val characterList : List<Character>? = null,
    override val errorCode: Int? = null,
    override val errorMessage: String? = null,
    override val uiState: UiState = UiState.IDLE
): BaseViewState
