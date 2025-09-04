package com.example.cineplus.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cineplus.model.TVShow
import com.example.cineplus.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TVShowsViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _popularTVShows = MutableStateFlow<List<TVShow>>(emptyList())
    val popularTVShows: StateFlow<List<TVShow>> = _popularTVShows.asStateFlow()

    private val _topRatedTVShows = MutableStateFlow<List<TVShow>>(emptyList())
    val topRatedTVShows: StateFlow<List<TVShow>> = _topRatedTVShows.asStateFlow()

    private val _onTheAirTVShows = MutableStateFlow<List<TVShow>>(emptyList())
    val onTheAirTVShows: StateFlow<List<TVShow>> = _onTheAirTVShows.asStateFlow()

    private val _airingTodayTVShows = MutableStateFlow<List<TVShow>>(emptyList())
    val airingTodayTVShows: StateFlow<List<TVShow>> = _airingTodayTVShows.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadTVShows()
    }

    fun loadTVShows() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val popularTVShowsResponse = repository.getPopularTVShows()
                _popularTVShows.value = popularTVShowsResponse.results

                val topRatedTVShowsResponse = repository.getTopRatedTVShows()
                _topRatedTVShows.value = topRatedTVShowsResponse.results

                val onTheAirTVShowsResponse = repository.getOnTheAirTVShows()
                _onTheAirTVShows.value = onTheAirTVShowsResponse.results

                val airingTodayTVShowsResponse = repository.getAiringTodayTVShows()
                _airingTodayTVShows.value = airingTodayTVShowsResponse.results

            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }
}
