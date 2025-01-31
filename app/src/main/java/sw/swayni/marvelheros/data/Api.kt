package sw.swayni.marvelheros.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import sw.swayni.marvelheros.data.model.CharacterDataWrapper
import sw.swayni.marvelheros.data.model.ComicDataWrapper

interface Api {

    @GET(value = "characters")
    suspend fun getCharacters(
        @Query("ts") ts:String,
        @Query("apikey") apikey: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("hash") hash : String
    ): CharacterDataWrapper


    @GET(value = "characters/{character_id}/comics")
    suspend fun getComics(
        @Path("character_id") characterId: Int,
        @Query("ts") ts:String,
        @Query("apikey") apikey: String,
        @Query("startYear") startYear: Int,
        @Query("limit") limit: Int,
        @Query("orderBy") orderBy: String,
        @Query("hash") hash : String
    ): ComicDataWrapper
}