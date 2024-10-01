package ag.android.movierandomizer

import ag.android.movierandomizer.data.MovieResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// API Interface defines the HTTP requests (GET, POST, etc)
interface MovieApiService {
    @GET("3/genre/movie/list")
    fun getGenres(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US"
    ): Call<MovieResponse>
}

// Create Retrofit instance
object RetrofitInstance {

    private const val BASE_URL = "https://api.themoviedb.org/"

    // Create and configure Retrofit
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()) // Converter to handle JSON data
        .build()

    val api: MovieApiService = retrofit.create(MovieApiService::class.java)
}