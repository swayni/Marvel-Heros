package sw.swayni.marvelheros.data.local

import kotlinx.coroutines.flow.Flow
import sw.swayni.base.data.ResultData
import sw.swayni.marvelheros.data.model.CharacterModel

interface ILocalDataSource {
    suspend fun insertCharacter(character: CharacterModel): Flow<ResultData<Boolean>>
    suspend fun getCharacter(id:Int): Flow<ResultData<CharacterModel>>
    suspend fun getPopularCharacterList() : Flow<ResultData<List<CharacterModel>>>
    suspend fun deleteCharacter(character: CharacterModel): Flow<ResultData<Boolean>>
}