package sw.swayni.marvelheros.ui.detail.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import sw.swayni.base.data.ResultData
import sw.swayni.base.di.quality.IoDispatcher
import sw.swayni.base.mvvm.enums.UiState
import sw.swayni.base.mvvm.viewmodel.BaseViewModel
import sw.swayni.marvelheros.data.model.CharacterModel
import sw.swayni.marvelheros.data.repository.IRepository
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: IRepository, @IoDispatcher private val dispatcher: CoroutineDispatcher) : BaseViewModel<DetailViewState, DetailAction>(DetailViewState()) {


    private var startYear : Int = 2005
    private val limit : Int = 10
    private val orderBy : String = "onsaleDate"

    fun getComics(characterId: Int){
        viewModelScope.launch(dispatcher) {
            sendAction(DetailAction.Loading)
            repository.getComicsByCharacterId(characterId, startYear, limit, orderBy).collect{
                when(it){
                    is ResultData.Error -> sendAction(DetailAction.Error(it.exception.message.toString()))
                    is ResultData.Success -> sendAction(DetailAction.GetComics(it.data))
                }
            }
        }
    }

    fun addFavorite(character: CharacterModel){
        viewModelScope.launch(dispatcher) {
            sendAction(DetailAction.Loading)
            repository.insertCharacter(character).collect{
                when(it){
                    is ResultData.Error -> sendAction(DetailAction.Error(it.exception.message.toString()))
                    is ResultData.Success ->{
                        if (it.data) {
                            sendAction(DetailAction.AddFavorite(true))
                        }
                        else{
                            sendAction(DetailAction.AddFavorite(false))
                        }
                    }
                }
            }
        }
    }

    fun getFavorite(characterId: Int){
        viewModelScope.launch(dispatcher) {
            sendAction(DetailAction.Loading)
            repository.getCharacter(characterId).collect {
                when (it) {
                    is ResultData.Error -> sendAction(DetailAction.Error(it.exception.message.toString()))
                    is ResultData.Success -> {
                        if (it.data.id == characterId){
                            sendAction(DetailAction.GetFavorite(true))
                        }
                        else {
                            sendAction(DetailAction.GetFavorite(false))
                        }
                    }
                }
            }
        }
    }

    fun deleteFavorite(character: CharacterModel){
        viewModelScope.launch(dispatcher) {
            sendAction(DetailAction.Loading)
            repository.deleteCharacter(character).collect{
                when(it){
                    is ResultData.Error -> sendAction(DetailAction.Error(it.exception.message.toString()))
                    is ResultData.Success -> {
                        if (it.data) {
                            sendAction(DetailAction.DeleteFavorite(false))
                        }
                        else{
                            sendAction(DetailAction.DeleteFavorite(true))
                        }
                    }
                }
            }
        }
    }

    override fun cleanState(): DetailViewState = state.copy(uiState = UiState.IDLE, errorCode = null, errorMessage = null)

    override fun onReduceState(viewAction: DetailAction): DetailViewState = when(viewAction){
        is DetailAction.AddFavorite ->  state.copy(uiState = UiState.SUCCESS, comicDataContainer =null, isFavorite = viewAction.isAddFavorite, errorCode = null, errorMessage = null)
        is DetailAction.DeleteFavorite ->  state.copy(uiState = UiState.SUCCESS, comicDataContainer =null, isFavorite = viewAction.isDeleteFavorite, errorCode = null, errorMessage = null)
        is DetailAction.Error ->  state.copy(uiState = UiState.ERROR, comicDataContainer =null, isFavorite = null, errorCode = null, errorMessage = viewAction.errorMessage)
        is DetailAction.GetComics ->  state.copy(uiState = UiState.SUCCESS, comicDataContainer = viewAction.comicDataContainer, isFavorite = null, errorCode = null, errorMessage = null)
        is DetailAction.GetFavorite ->  state.copy(uiState = UiState.SUCCESS, comicDataContainer = null, isFavorite = viewAction.isGetFavorite, errorCode = null, errorMessage = null)
        DetailAction.Loading -> state.copy(uiState = UiState.IDLE, comicDataContainer = null, isFavorite = null, errorCode = null, errorMessage = null)
    }
}