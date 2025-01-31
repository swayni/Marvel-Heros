package sw.swayni.marvelheros.ui.favorite.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import sw.swayni.base.data.ResultData
import sw.swayni.base.di.quality.IoDispatcher
import sw.swayni.base.mvvm.enums.UiState
import sw.swayni.base.mvvm.viewmodel.BaseViewModel
import sw.swayni.marvelheros.data.repository.IRepository
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: IRepository, @IoDispatcher private val dispatcher: CoroutineDispatcher) : BaseViewModel<FavoriteViewState, FavoriteAction>(FavoriteViewState()) {

    fun getFavoriteList(){
        viewModelScope.launch(dispatcher) {
            sendAction(FavoriteAction.Loading)
            repository.getPopularCharacterList().collect{
                when(it){
                    is ResultData.Error -> sendAction(FavoriteAction.Error(it.exception.message.toString()))
                    is ResultData.Success -> sendAction(FavoriteAction.Success(it.data))
                }
            }
        }
    }

    override fun cleanState(): FavoriteViewState = state.copy(uiState = UiState.IDLE, errorCode = null, errorMessage = null)

    override fun onReduceState(viewAction: FavoriteAction): FavoriteViewState = when(viewAction){
        is FavoriteAction.Error -> state.copy(uiState = UiState.ERROR, characterList = null, errorCode = null, errorMessage = viewAction.error)
        FavoriteAction.Loading -> state.copy(uiState = UiState.LOADING, characterList = null, errorCode = null, errorMessage = null)
        is FavoriteAction.Success -> state.copy(uiState = UiState.SUCCESS, characterList = viewAction.characterList, errorCode = null, errorMessage = null)
    }
}