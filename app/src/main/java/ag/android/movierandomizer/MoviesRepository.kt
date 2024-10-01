package ag.android.movierandomizer

import ag.android.movierandomizer.data.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val apiKey = BuildConfig.API_KEY

class MoviesRepository(private val api: MovieApiService) {

    fun getGenres(callback: (MovieResponse) -> Unit) {
        api.getGenres(apiKey).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                callback(response.body()!!)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                callback(null!!)
            }
        })
    }
}