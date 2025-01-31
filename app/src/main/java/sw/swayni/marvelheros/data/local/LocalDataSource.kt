package sw.swayni.marvelheros.data.local

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import sw.swayni.base.data.ResultData
import sw.swayni.marvelheros.data.database.AppDatabase
import sw.swayni.marvelheros.data.model.CharacterModel
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val appDatabase: AppDatabase) : ILocalDataSource {

    override suspend fun insertCharacter(character: CharacterModel): Flow<ResultData<Boolean>> = flow {
        try{
            val result = appDatabase.characterDao().add(character)
            emit(ResultData.Success(true))
        }catch (e:Exception){
            emit(ResultData.Error(e))
        }
    }

    override suspend fun getCharacter(id: Int): Flow<ResultData<CharacterModel>> = flow {
        try{
            val result = appDatabase.characterDao().getCharacter(id)
            emit(ResultData.Success(result))
        }catch (e:Exception){
            emit(ResultData.Error(e))
        }
    }

    override suspend fun getPopularCharacterList(): Flow<ResultData<List<CharacterModel>>> = flow {
        try{
            val result = appDatabase.characterDao().getPopularCharacters()
            emit(ResultData.Success(result))
        }catch (e:Exception){
            emit(ResultData.Error(e))
        }
    }

    override suspend fun deleteCharacter(character: CharacterModel): Flow<ResultData<Boolean>> = flow {
        try{
            val result = appDatabase.characterDao().delete(character)
            emit(ResultData.Success(true))
        }catch (e:Exception){
            emit(ResultData.Error(e))
        }
    }
}