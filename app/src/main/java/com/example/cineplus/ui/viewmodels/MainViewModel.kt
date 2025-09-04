package com.example.cineplus.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cineplus.model.Movie
import com.example.cineplus.model.TVShow
import com.example.cineplus.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _popularMovies = MutableStateFlow<List<Movie>>(emptyList())
    val popularMovies: StateFlow<List<Movie>> = _popularMovies.asStateFlow()

    private val _trendingMovies = MutableStateFlow<List<Movie>>(emptyList())
    val trendingMovies: StateFlow<List<Movie>> = _trendingMovies.asStateFlow()

    private val _topRatedMovies = MutableStateFlow<List<Movie>>(emptyList())
    val topRatedMovies: StateFlow<List<Movie>> = _topRatedMovies.asStateFlow()

    private val _nowPlayingMovies = MutableStateFlow<List<Movie>>(emptyList())
    val nowPlayingMovies: StateFlow<List<Movie>> = _nowPlayingMovies.asStateFlow()

    private val _popularTVShows = MutableStateFlow<List<TVShow>>(emptyList())
    val popularTVShows: StateFlow<List<TVShow>> = _popularTVShows.asStateFlow()

    private val _trendingTVShows = MutableStateFlow<List<TVShow>>(emptyList())
    val trendingTVShows: StateFlow<List<TVShow>> = _trendingTVShows.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadAllData()
    }

    private fun loadAllData() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                // Load movies
                val popularMoviesResponse = repository.getPopularMovies()
                _popularMovies.value = popularMoviesResponse.results

                val trendingMoviesResponse = repository.getTrendingMovies()
                _trendingMovies.value = trendingMoviesResponse.results

                val topRatedMoviesResponse = repository.getTopRatedMovies()
                _topRatedMovies.value = topRatedMoviesResponse.results

                val nowPlayingMoviesResponse = repository.getNowPlayingMovies()
                _nowPlayingMovies.value = nowPlayingMoviesResponse.results

                // Load TV shows
                val popularTVShowsResponse = repository.getPopularTVShows()
                _popularTVShows.value = popularTVShowsResponse.results

                val trendingTVShowsResponse = repository.getTrendingTVShows()
                _trendingTVShows.value = trendingTVShowsResponse.results

            } catch (e: Exception) {
                // Handle error
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }
}
