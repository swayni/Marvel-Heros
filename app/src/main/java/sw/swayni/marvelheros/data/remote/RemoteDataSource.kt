package sw.swayni.marvelheros.data.remote

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import sw.swayni.base.data.ResultData
import sw.swayni.marvelheros.BuildConfig
import sw.swayni.marvelheros.data.Api
import sw.swayni.marvelheros.data.model.CharacterDataWrapper
import sw.swayni.marvelheros.data.model.ComicDataWrapper
import sw.swayni.marvelheros.utils.md5
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val api: Api) : IRemoteDataSource {

    companion object{
        private const val HASH= "${1}${BuildConfig.BASE_API_PRIVATE_KEY}${BuildConfig.BASE_API_KEY}"
    }

    private val hash : String by lazy { HASH.md5() }

    override suspend fun getCharacters(
        offset: Int,
        limit: Int
    ): Flow<ResultData<CharacterDataWrapper>> = flow {
        try{
            val result = api.getCharacters("1", BuildConfig.BASE_API_KEY, offset, limit, hash)
            emit(ResultData.Success(result))
        }catch (e:Exception){
            emit(ResultData.Error(e))
        }
    }

    override suspend fun getComicsByCharacterId(
        characterId: Int,
        startYear: Int,
        limit: Int,
        orderBy: String
    ): Flow<ResultData<ComicDataWrapper>> = flow {
        try{
            val result = api.getComics(characterId, "1", BuildConfig.BASE_API_KEY, startYear, limit, orderBy, hash)
            emit(ResultData.Success(result))
        }catch (e:Exception){
            emit(ResultData.Error(e))
        }
    }

}