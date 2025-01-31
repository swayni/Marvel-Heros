package sw.swayni.marvelheros.data.repository

import kotlinx.coroutines.flow.Flow
import sw.swayni.base.data.ResultData
import sw.swayni.marvelheros.data.model.CharacterDataWrapper
import sw.swayni.marvelheros.data.model.CharacterModel
import sw.swayni.marvelheros.data.model.ComicDataWrapper

interface IRepository {
    suspend fun getCharacters(offset : Int, limit : Int): Flow<ResultData<CharacterDataWrapper>>
    suspend fun getComicsByCharacterId(characterId : Int, startYear : Int, limit:Int, orderBy:String): Flow<ResultData<ComicDataWrapper>>


    suspend fun insertCharacter(character: CharacterModel): Flow<ResultData<Boolean>>
    suspend fun getCharacter(id:Int): Flow<ResultData<CharacterModel>>
    suspend fun getPopularCharacterList() : Flow<ResultData<List<CharacterModel>>>
    suspend fun deleteCharacter(character: CharacterModel): Flow<ResultData<Boolean>>
}