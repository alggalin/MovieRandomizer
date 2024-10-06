package ag.android.movierandomizer

import ag.android.movierandomizer.data.GenreResponse

const val apiKey = BuildConfig.API_KEY

class MoviesRepository(private val apiService: MovieApiService) {

    suspend fun getGenres(): GenreResponse {
        return apiService.getGenres(apiKey)
    }
}