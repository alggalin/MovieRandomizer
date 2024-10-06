package ag.android.movierandomizer

import ag.android.movierandomizer.data.MovieResponse
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ag.android.movierandomizer.ui.theme.MovieRandomizerTheme
import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val apiService = RetrofitInstance.retrofitService
        val moviesRepository = MoviesRepository(apiService)
        // create instance of viewmodel factory
        val factory = MovieViewModelFactory(moviesRepository)

        val movieViewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

        setContent {
            MovieRandomizerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        movieViewModel = movieViewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(movieViewModel: MovieViewModel, modifier: Modifier = Modifier) {
    val genreResponse by movieViewModel.genreResponse.observeAsState()

    LaunchedEffect(Unit) {
        movieViewModel.getGenres()
    }

    Column(modifier) {
        genreResponse?.let { response ->
            Text("Genres: ${response.genres.joinToString { it.name }}")
        } ?: run {
            Text("Loading genres...")
        }
    }
}