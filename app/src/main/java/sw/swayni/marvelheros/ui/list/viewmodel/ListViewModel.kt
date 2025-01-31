package sw.swayni.marvelheros.ui.list.viewmodel

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
class ListViewModel @Inject constructor(private val repository: IRepository, @IoDispatcher private val dispatcher: CoroutineDispatcher) : BaseViewModel<ListViewState, ListAction>(ListViewState()) {

    private var offset : Int = 0
    private var limit : Int = 30


    init {
        getCharacters(limit)
    }

    private fun getCharacters(limit : Int, isLoadMore : Boolean = false){
        if (!isLoadMore){
            this.offset = 0
        }
        viewModelScope.launch(dispatcher) {
            sendAction(ListAction.Loading)
            repository.getCharacters(offset, limit).collect{
                when(it){
                    is ResultData.Error -> sendAction(ListAction.Error(it.exception.message.toString()))
                    is ResultData.Success -> sendAction(ListAction.SuccessCharacterList(it.data.data.results))
                }
            }
        }
    }

    fun loadMoreCharacters(){
        offset++
        getCharacters(limit, isLoadMore = true)
    }


    override fun cleanState(): ListViewState = state.copy(uiState = UiState.IDLE, errorCode = null, errorMessage = null)

    override fun onReduceState(viewAction: ListAction): ListViewState = when(viewAction){
        is ListAction.Loading -> state.copy(uiState = UiState.LOADING, errorCode = null, errorMessage = null, offset = offset)
        is ListAction.Error -> state.copy(uiState = UiState.ERROR, errorCode = null, errorMessage = viewAction.error, offset = offset)
        is ListAction.SuccessCharacterList -> state.copy(uiState = UiState.SUCCESS, characterList = viewAction.characterList, offset = offset)
    }
}