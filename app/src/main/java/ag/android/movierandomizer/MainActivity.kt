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
        setContent {
            MovieRandomizerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    val apiKey = BuildConfig.API_KEY

    lateinit var movieViewModel: MovieViewModel

    val apiService = RetrofitInstance.api

    val movieRepository = MoviesRepository(apiService)

    movieViewModel.fetchGenres(apiKey)

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MovieRandomizerTheme {
        Greeting("Android")
    }
}