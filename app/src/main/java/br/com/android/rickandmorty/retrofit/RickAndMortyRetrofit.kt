package br.com.android.rickandmorty.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

data class Origin (
    val name: String = "",
    val url: String = ""
)

data class Location (
    val name: String = "",
    val url: String = ""
)

data class Character (
    val id: Int = 0,
    val name: String = "Default Name",
    val status: String = "",
    val species: String = "",
    val type: String = "",
    val gender: String = "",
    val origin: Origin = Origin(),
    val location: Location = Location(),
    val image: String = "https://github.com/Alex5ander.png",
    val episode: List<String> = listOf(),
    val url: String = "",
    val created: String = "",
)

data class Info (
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?,
)

data class CharactersResponse (
    val results: List<Character>,
    val info: Info
)

interface RickAndMortyService {
    @GET("character")
    suspend fun getCharacters(@Query("page") page:Int = 0, @Query("name") name: String = ""): CharactersResponse
    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): Character
}

class RickAndMortyRetrofit {
    companion object {
        private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service: RickAndMortyService = retrofit.create(RickAndMortyService::class.java)
    }
}