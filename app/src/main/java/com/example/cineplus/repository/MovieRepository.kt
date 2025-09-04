package com.example.cineplus.repository

import com.example.cineplus.model.Movie
import com.example.cineplus.model.MovieResponse
import com.example.cineplus.model.TVShow
import com.example.cineplus.model.TVShowResponse
import com.example.cineplus.network.RetrofitInstance
import com.example.cineplus.ui.viewmodels.CreditsResponse
import com.example.cineplus.ui.viewmodels.MovieDetails
import com.example.cineplus.ui.viewmodels.TVShowDetails
import com.example.cineplus.ui.viewmodels.VideoResponse
import javax.inject.Inject

class MovieRepository @Inject constructor() {
    // Existing methods...
    suspend fun getPopularMovies(page: Int = 1) = RetrofitInstance.api.getPopularMovies(page)
    suspend fun getTopRatedMovies(page: Int = 1) = RetrofitInstance.api.getTopRatedMovies(page)
    suspend fun getNowPlayingMovies(page: Int = 1) = RetrofitInstance.api.getNowPlayingMovies(page)
    suspend fun getUpcomingMovies(page: Int = 1) = RetrofitInstance.api.getUpcomingMovies(page)
    suspend fun getTrendingMovies(page: Int = 1) = RetrofitInstance.api.getTrendingMovies(page)
    
    suspend fun getPopularTVShows(page: Int = 1) = RetrofitInstance.api.getPopularTVShows(page)
    suspend fun getTopRatedTVShows(page: Int = 1) = RetrofitInstance.api.getTopRatedTVShows(page)
    suspend fun getOnTheAirTVShows(page: Int = 1) = RetrofitInstance.api.getOnTheAirTVShows(page)
    suspend fun getAiringTodayTVShows(page: Int = 1) = RetrofitInstance.api.getAiringTodayTVShows(page)
    suspend fun getTrendingTVShows(page: Int = 1) = RetrofitInstance.api.getTrendingTVShows(page)
    
    suspend fun searchMulti(query: String, page: Int = 1) = RetrofitInstance.api.searchMulti(query, page)

    // New methods for detailed views
    suspend fun getMovieDetails(movieId: Int) = RetrofitInstance.api.getMovieDetails(movieId)
    suspend fun getTVShowDetails(tvShowId: Int) = RetrofitInstance.api.getTVShowDetails(tvShowId)
    suspend fun getMovieCredits(movieId: Int) = RetrofitInstance.api.getMovieCredits(movieId)
    suspend fun getTVShowCredits(tvShowId: Int) = RetrofitInstance.api.getTVShowCredits(tvShowId)
    suspend fun getMovieVideos(movieId: Int) = RetrofitInstance.api.getMovieVideos(movieId)
    suspend fun getTVShowVideos(tvShowId: Int) = RetrofitInstance.api.getTVShowVideos(tvShowId)
    suspend fun getSimilarMovies(movieId: Int, page: Int = 1) = RetrofitInstance.api.getSimilarMovies(movieId, page)
    suspend fun getSimilarTVShows(tvShowId: Int, page: Int = 1) = RetrofitInstance.api.getSimilarTVShows(tvShowId, page)
    suspend fun getMovieRecommendations(movieId: Int, page: Int = 1) = RetrofitInstance.api.getMovieRecommendations(movieId, page)
    suspend fun getTVShowRecommendations(tvShowId: Int, page: Int = 1) = RetrofitInstance.api.getTVShowRecommendations(tvShowId, page)
}
