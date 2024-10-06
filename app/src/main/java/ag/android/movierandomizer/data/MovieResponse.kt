package ag.android.movierandomizer.data


data class MovieResponse (
    val results: List<Movie>
)

// Data returned from each individual movie
data class Movie (
    val id: Int,
    val original_language: String,
    val overview: String, // movie description
    val title: String,
    val popularity: Double,
    val poster_path: String, // url used for images https://image.tmdb.org/t/p/w500/${poster_path}
    val genre_ids: List<Int>,
    val release_date: String,
    val vote_average: Double,
    val vote_count: Int
)

data class GenreResponse (
    val genres: List<Genre>
)

// Data returned from a genre
data class Genre(
    val id: Int,
    val name: String
)