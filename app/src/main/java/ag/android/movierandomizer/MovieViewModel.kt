package ag.android.movierandomizer

import ag.android.movierandomizer.data.GenreResponse
import ag.android.movierandomizer.data.MovieResponse
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MovieViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    private val _genreResponse = MutableLiveData<GenreResponse>()
    val genreResponse: LiveData<GenreResponse> = _genreResponse

    fun getGenres() {
        viewModelScope.launch {
            try {
                val response = moviesRepository.getGenres()
                _genreResponse.value = response
            } catch (e: Exception) {
                Log.e("API ERROR", "Failed to getch genres: ${e.message}")
            }
        }
    }
}

class MovieViewModelFactory(private val moviesRepository: MoviesRepository) :
    ViewModelProvider.Factory {

        override fun <T: ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
                return MovieViewModel(moviesRepository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
}