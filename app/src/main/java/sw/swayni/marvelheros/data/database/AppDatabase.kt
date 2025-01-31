package sw.swayni.marvelheros.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import sw.swayni.marvelheros.data.model.CharacterModel

@Database(entities =
[CharacterModel::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase(){

    abstract fun characterDao():CharacterDao
}