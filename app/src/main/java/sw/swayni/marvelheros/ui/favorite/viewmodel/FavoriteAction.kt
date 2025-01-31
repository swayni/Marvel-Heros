package sw.swayni.marvelheros.ui.favorite.viewmodel

import sw.swayni.base.mvvm.viewmodel.BaseAction
import sw.swayni.marvelheros.data.model.CharacterModel

sealed class FavoriteAction : BaseAction {
    data object Loading : FavoriteAction()
    data class Error(val error : String): FavoriteAction()
    data class Success(val characterList: List<CharacterModel>) : FavoriteAction()
}