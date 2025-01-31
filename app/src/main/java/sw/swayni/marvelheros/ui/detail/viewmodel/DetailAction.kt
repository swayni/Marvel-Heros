package sw.swayni.marvelheros.ui.detail.viewmodel

import sw.swayni.base.mvvm.viewmodel.BaseAction
import sw.swayni.marvelheros.data.model.ComicDataWrapper

sealed class DetailAction : BaseAction{

    data class GetComics(val comicDataContainer : ComicDataWrapper) : DetailAction()
    data class AddFavorite(val isAddFavorite: Boolean) : DetailAction()
    data class GetFavorite(val isGetFavorite: Boolean) : DetailAction()
    data class DeleteFavorite(val isDeleteFavorite: Boolean) : DetailAction()
    data object Loading : DetailAction()
    data class Error(val errorMessage: String) : DetailAction()

}