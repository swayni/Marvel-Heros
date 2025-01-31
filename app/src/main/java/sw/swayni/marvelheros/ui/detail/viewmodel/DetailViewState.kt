package sw.swayni.marvelheros.ui.detail.viewmodel

import sw.swayni.base.mvvm.enums.UiState
import sw.swayni.base.mvvm.viewmodel.BaseViewState
import sw.swayni.marvelheros.data.model.ComicDataWrapper

data class DetailViewState(
    val comicDataContainer : ComicDataWrapper?= null,
    val isFavorite : Boolean ?= null,
    override val errorCode: Int? = null,
    override val errorMessage: String? = null,
    override val uiState: UiState = UiState.IDLE
) : BaseViewState
