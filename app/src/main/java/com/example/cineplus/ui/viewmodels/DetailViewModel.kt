package com.example.cineplus.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cineplus.model.Movie
import com.example.cineplus.model.MovieDetails
import com.example.cineplus.model.TVShow
import com.example.cineplus.model.TVShowDetails
import com.example.cineplus.network.RetrofitInstance
import com.example.cineplus.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _movieDetails = MutableStateFlow<MovieDetails?>(null)
    val movieDetails: StateFlow<MovieDetails?> = _movieDetails.asStateFlow()

    private val _tvShowDetails = MutableStateFlow<TVShowDetails?>(null)
    val tvShowDetails: StateFlow<TVShowDetails?> = _tvShowDetails.asStateFlow()

    private val _movieCredits = MutableStateFlow<CreditsResponse?>(null)
    val movieCredits: StateFlow<CreditsResponse?> = _movieCredits.asStateFlow()

    private val _tvShowCredits = MutableStateFlow<CreditsResponse?>(null)
    val tvShowCredits: StateFlow<CreditsResponse?> = _tvShowCredits.asStateFlow()

    private val _movieVideos = MutableStateFlow<VideoResponse?>(null)
    val movieVideos: StateFlow<VideoResponse?> = _movieVideos.asStateFlow()

    private val _tvShowVideos = MutableStateFlow<VideoResponse?>(null)
    val tvShowVideos: StateFlow<VideoResponse?> = _tvShowVideos.asStateFlow()

    private val _similarMovies = MutableStateFlow<List<Movie>?>(null)
    val similarMovies: StateFlow<List<Movie>?> = _similarMovies.asStateFlow()

    private val _similarTVShows = MutableStateFlow<List<TVShow>?>(null)
    val similarTVShows: StateFlow<List<TVShow>?> = _similarTVShows.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun loadMovieDetails(movieId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val details = RetrofitInstance.api.getMovieDetails(movieId)
                _movieDetails.value = details
            } catch (e: Exception) {
                _error.value = "Failed to load movie details: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadTVShowDetails(tvShowId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val details = RetrofitInstance.api.getTVShowDetails(tvShowId)
                _tvShowDetails.value = details
            } catch (e: Exception) {
                _error.value = "Failed to load TV show details: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadMovieCredits(movieId: Int) {
        viewModelScope.launch {
            try {
                val credits = RetrofitInstance.api.getMovieCredits(movieId)
                _movieCredits.value = credits
            } catch (e: Exception) {
                // Handle error silently for credits
            }
        }
    }

    fun loadTVShowCredits(tvShowId: Int) {
        viewModelScope.launch {
            try {
                val credits = RetrofitInstance.api.getTVShowCredits(tvShowId)
                _tvShowCredits.value = credits
            } catch (e: Exception) {
                // Handle error silently for credits
            }
        }
    }

    fun loadMovieVideos(movieId: Int) {
        viewModelScope.launch {
            try {
                val videos = RetrofitInstance.api.getMovieVideos(movieId)
                _movieVideos.value = videos
            } catch (e: Exception) {
                // Handle error silently for videos
            }
        }
    }

    fun loadTVShowVideos(tvShowId: Int) {
        viewModelScope.launch {
            try {
                val videos = RetrofitInstance.api.getTVShowVideos(tvShowId)
                _tvShowVideos.value = videos
            } catch (e: Exception) {
                // Handle error silently for videos
            }
        }
    }

    fun loadSimilarMovies(movieId: Int) {
        viewModelScope.launch {
            try {
                val similar = RetrofitInstance.api.getSimilarMovies(movieId)
                _similarMovies.value = similar.results
            } catch (e: Exception) {
                // Handle error silently for similar content
            }
        }
    }

    fun loadSimilarTVShows(tvShowId: Int) {
        viewModelScope.launch {
            try {
                val similar = RetrofitInstance.api.getSimilarTVShows(tvShowId)
                _similarTVShows.value = similar.results
            } catch (e: Exception) {
                // Handle error silently for similar content
            }
        }
    }

    fun loadAllMovieData(movieId: Int) {
        loadMovieDetails(movieId)
        loadMovieCredits(movieId)
        loadMovieVideos(movieId)
        loadSimilarMovies(movieId)
    }

    fun loadAllTVShowData(tvShowId: Int) {
        loadTVShowDetails(tvShowId)
        loadTVShowCredits(tvShowId)
        loadTVShowVideos(tvShowId)
        loadSimilarTVShows(tvShowId)
    }

    fun clearData() {
        _movieDetails.value = null
        _tvShowDetails.value = null
        _movieCredits.value = null
        _tvShowCredits.value = null
        _movieVideos.value = null
        _tvShowVideos.value = null
        _similarMovies.value = null
        _similarTVShows.value = null
        _error.value = null
    }
}

// Data classes for detailed responses
data class MovieDetails(
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String?,
    val backdrop_path: String?,
    val release_date: String?,
    val vote_average: Double,
    val vote_count: Int,
    val runtime: Int?,
    val genres: List<Genre>,
    val production_companies: List<ProductionCompany>,
    val production_countries: List<ProductionCountry>,
    val spoken_languages: List<SpokenLanguage>,
    val status: String,
    val tagline: String?,
    val budget: Long,
    val revenue: Long,
    val homepage: String?
)

data class TVShowDetails(
    val id: Int,
    val name: String,
    val overview: String,
    val poster_path: String?,
    val backdrop_path: String?,
    val first_air_date: String?,
    val vote_average: Double,
    val vote_count: Int,
    val episode_run_time: List<Int>?,
    val genres: List<Genre>,
    val production_companies: List<ProductionCompany>,
    val production_countries: List<ProductionCountry>,
    val spoken_languages: List<SpokenLanguage>,
    val status: String,
    val tagline: String?,
    val number_of_seasons: Int,
    val number_of_episodes: Int,
    val homepage: String?,
    val last_air_date: String?,
    val networks: List<Network>
)

data class CreditsResponse(
    val id: Int,
    val cast: List<Cast>,
    val crew: List<Crew>
)

data class VideoResponse(
    val id: Int,
    val results: List<Video>
)

data class Genre(
    val id: Int,
    val name: String
)

data class ProductionCompany(
    val id: Int,
    val name: String,
    val logo_path: String?,
    val origin_country: String
)

data class ProductionCountry(
    val iso_3166_1: String,
    val name: String
)

data class SpokenLanguage(
    val english_name: String,
    val iso_639_1: String,
    val name: String
)

data class Network(
    val id: Int,
    val name: String,
    val logo_path: String?,
    val origin_country: String
)

data class Cast(
    val id: Int,
    val name: String,
    val character: String,
    val profile_path: String?,
    val order: Int
)

data class Crew(
    val id: Int,
    val name: String,
    val job: String,
    val department: String,
    val profile_path: String?
)

data class Video(
    val id: String,
    val key: String,
    val name: String,
    val site: String,
    val type: String,
    val official: Boolean
)

    private val _movieDetails = MutableStateFlow<MovieDetails?>(null)
    val movieDetails: StateFlow<MovieDetails?> = _movieDetails.asStateFlow()

    private val _tvShowDetails = MutableStateFlow<TVShowDetails?>(null)
    val tvShowDetails: StateFlow<TVShowDetails?> = _tvShowDetails.asStateFlow()

    private val _movieCredits = MutableStateFlow<CreditsResponse?>(null)
    val movieCredits: StateFlow<CreditsResponse?> = _movieCredits.asStateFlow()

    private val _tvShowCredits = MutableStateFlow<CreditsResponse?>(null)
    val tvShowCredits: StateFlow<CreditsResponse?> = _tvShowCredits.asStateFlow()

    private val _movieVideos = MutableStateFlow<VideoResponse?>(null)
    val movieVideos: StateFlow<VideoResponse?> = _movieVideos.asStateFlow()

    private val _tvShowVideos = MutableStateFlow<VideoResponse?>(null)
    val tvShowVideos: StateFlow<VideoResponse?> = _tvShowVideos.asStateFlow()

    private val _similarMovies = MutableStateFlow<List<Movie>?>(null)
    val similarMovies: StateFlow<List<Movie>?> = _similarMovies.asStateFlow()

    private val _similarTVShows = MutableStateFlow<List<TVShow>?>(null)
    val similarTVShows: StateFlow<List<TVShow>?> = _similarTVShows.asStateFlow()

    private val _movieRecommendations = MutableStateFlow<List<Movie>?>(null)
    val movieRecommendations: StateFlow<List<Movie>?> = _movieRecommendations.asStateFlow()

    private val _tvShowRecommendations = MutableStateFlow<List<TVShow>?>(null)
    val tvShowRecommendations: StateFlow<List<TVShow>?> = _tvShowRecommendations.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun loadMovieDetails(movieId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val details = repository.getMovieDetails(movieId)
                _movieDetails.value = details
            } catch (e: Exception) {
                _error.value = "Failed to load movie details: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadTVShowDetails(tvShowId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val details = repository.getTVShowDetails(tvShowId)
                _tvShowDetails.value = details
            } catch (e: Exception) {
                _error.value = "Failed to load TV show details: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadMovieCredits(movieId: Int) {
        viewModelScope.launch {
            try {
                val credits = repository.getMovieCredits(movieId)
                _movieCredits.value = credits
            } catch (e: Exception) {
                // Handle error silently for credits
            }
        }
    }

    fun loadTVShowCredits(tvShowId: Int) {
        viewModelScope.launch {
            try {
                val credits = repository.getTVShowCredits(tvShowId)
                _tvShowCredits.value = credits
            } catch (e: Exception) {
                // Handle error silently for credits
            }
        }
    }

    fun loadMovieVideos(movieId: Int) {
        viewModelScope.launch {
            try {
                val videos = repository.getMovieVideos(movieId)
                _movieVideos.value = videos
            } catch (e: Exception) {
                // Handle error silently for videos
            }
        }
    }

    fun loadTVShowVideos(tvShowId: Int) {
        viewModelScope.launch {
            try {
                val videos = repository.getTVShowVideos(tvShowId)
                _tvShowVideos.value = videos
            } catch (e: Exception) {
                // Handle error silently for videos
            }
        }
    }

    fun loadSimilarMovies(movieId: Int) {
        viewModelScope.launch {
            try {
                val similar = repository.getSimilarMovies(movieId)
                _similarMovies.value = similar.results
            } catch (e: Exception) {
                // Handle error silently for similar content
            }
        }
    }

    fun loadSimilarTVShows(tvShowId: Int) {
        viewModelScope.launch {
            try {
                val similar = repository.getSimilarTVShows(tvShowId)
                _similarTVShows.value = similar.results
            } catch (e: Exception) {
                // Handle error silently for similar content
            }
        }
    }

    fun loadMovieRecommendations(movieId: Int) {
        viewModelScope.launch {
            try {
                val recommendations = repository.getMovieRecommendations(movieId)
                _movieRecommendations.value = recommendations.results
            } catch (e: Exception) {
                // Handle error silently for recommendations
            }
        }
    }

    fun loadTVShowRecommendations(tvShowId: Int) {
        viewModelScope.launch {
            try {
                val recommendations = repository.getTVShowRecommendations(tvShowId)
                _tvShowRecommendations.value = recommendations.results
            } catch (e: Exception) {
                // Handle error silently for recommendations
            }
        }
    }

    fun loadAllMovieData(movieId: Int) {
        loadMovieDetails(movieId)
        loadMovieCredits(movieId)
        loadMovieVideos(movieId)
        loadSimilarMovies(movieId)
        loadMovieRecommendations(movieId)
    }

    fun loadAllTVShowData(tvShowId: Int) {
        loadTVShowDetails(tvShowId)
        loadTVShowCredits(tvShowId)
        loadTVShowVideos(tvShowId)
        loadSimilarTVShows(tvShowId)
        loadTVShowRecommendations(tvShowId)
    }

    fun clearData() {
        _movieDetails.value = null
        _tvShowDetails.value = null
        _movieCredits.value = null
        _tvShowCredits.value = null
        _movieVideos.value = null
        _tvShowVideos.value = null
        _similarMovies.value = null
        _similarTVShows.value = null
        _movieRecommendations.value = null
        _tvShowRecommendations.value = null
        _error.value = null
    }
}

// Data classes for detailed responses (keep the same as above)