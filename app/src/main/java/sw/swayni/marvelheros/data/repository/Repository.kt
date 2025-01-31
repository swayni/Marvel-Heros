package sw.swayni.marvelheros.data.repository

import kotlinx.coroutines.flow.Flow
import sw.swayni.base.data.ResultData
import sw.swayni.marvelheros.data.local.ILocalDataSource
import sw.swayni.marvelheros.data.model.CharacterDataWrapper
import sw.swayni.marvelheros.data.model.CharacterModel
import sw.swayni.marvelheros.data.model.ComicDataWrapper
import sw.swayni.marvelheros.data.remote.IRemoteDataSource
import javax.inject.Inject

class Repository @Inject constructor(private val localDataSource: ILocalDataSource, private val remoteDataSource: IRemoteDataSource) : IRepository{

    override suspend fun getCharacters(
        offset: Int,
        limit: Int
    ): Flow<ResultData<CharacterDataWrapper>> = remoteDataSource.getCharacters(offset, limit)

    override suspend fun getComicsByCharacterId(
        characterId: Int,
        startYear: Int,
        limit: Int,
        orderBy: String
    ): Flow<ResultData<ComicDataWrapper>> = remoteDataSource.getComicsByCharacterId(characterId, startYear, limit, orderBy)

    override suspend fun insertCharacter(character: CharacterModel): Flow<ResultData<Boolean>> = localDataSource.insertCharacter(character)

    override suspend fun getCharacter(id: Int): Flow<ResultData<CharacterModel>> = localDataSource.getCharacter(id)

    override suspend fun getPopularCharacterList(): Flow<ResultData<List<CharacterModel>>> = localDataSource.getPopularCharacterList()

    override suspend fun deleteCharacter(character: CharacterModel): Flow<ResultData<Boolean>> = localDataSource.deleteCharacter(character)
}