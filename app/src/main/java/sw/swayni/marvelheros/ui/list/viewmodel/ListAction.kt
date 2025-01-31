package sw.swayni.marvelheros.ui.list.viewmodel

import sw.swayni.base.mvvm.viewmodel.BaseAction
import sw.swayni.marvelheros.data.model.Character

sealed class ListAction : BaseAction {
    data object Loading : ListAction()
    data class Error (val error : String) : ListAction()
    data class SuccessCharacterList(val characterList : List<Character>) : ListAction()
}