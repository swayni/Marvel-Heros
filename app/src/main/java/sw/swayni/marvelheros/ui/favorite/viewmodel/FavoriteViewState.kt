package sw.swayni.marvelheros.ui.favorite.viewmodel

import sw.swayni.base.mvvm.enums.UiState
import sw.swayni.base.mvvm.viewmodel.BaseViewState
import sw.swayni.marvelheros.data.model.CharacterModel

data class FavoriteViewState(
    val characterList: List<CharacterModel>? = null,
    override val errorCode: Int? = null,
    override val errorMessage: String? = null,
    override val uiState: UiState = UiState.IDLE
) : BaseViewState