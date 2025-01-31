package sw.swayni.marvelheros.data.remote

import kotlinx.coroutines.flow.Flow
import sw.swayni.base.data.ResultData
import sw.swayni.marvelheros.data.model.CharacterDataWrapper
import sw.swayni.marvelheros.data.model.ComicDataWrapper

interface IRemoteDataSource {
    suspend fun getCharacters(offset : Int, limit : Int): Flow<ResultData<CharacterDataWrapper>>
    suspend fun getComicsByCharacterId(characterId : Int, startYear : Int, limit:Int, orderBy:String): Flow<ResultData<ComicDataWrapper>>
}