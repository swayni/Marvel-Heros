package sw.swayni.marvelheros.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import sw.swayni.marvelheros.data.model.CharacterModel

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(character : CharacterModel)

    @Query("SELECT * FROM CharacterModel")
    suspend fun getPopularCharacters(): List<CharacterModel>

    @Query("SELECT * FROM CharacterModel WHERE CharacterModel.id == :id")
    suspend fun getCharacter(id: Int): CharacterModel

    @Delete
    suspend fun delete(character: CharacterModel)
}