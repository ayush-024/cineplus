package com.example.cineplus.network

import com.example.cineplus.model.MovieResponse
import com.example.cineplus.model.TVShowResponse
import com.example.cineplus.ui.viewmodels.CreditsResponse
import com.example.cineplus.ui.viewmodels.MovieDetails
import com.example.cineplus.ui.viewmodels.TVShowDetails
import com.example.cineplus.ui.viewmodels.VideoResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBService {
    // Existing methods...
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int = 1
    ): MovieResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("page") page: Int = 1
    ): MovieResponse

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("page") page: Int = 1
    ): MovieResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("page") page: Int = 1
    ): MovieResponse

    @GET("trending/movie/day")
    suspend fun getTrendingMovies(
        @Query("page") page: Int = 1
    ): MovieResponse

    @GET("tv/popular")
    suspend fun getPopularTVShows(
        @Query("page") page: Int = 1
    ): TVShowResponse

    @GET("tv/top_rated")
    suspend fun getTopRatedTVShows(
        @Query("page") page: Int = 1
    ): TVShowResponse

    @GET("tv/on_the_air")
    suspend fun getOnTheAirTVShows(
        @Query("page") page: Int = 1
    ): TVShowResponse

    @GET("tv/airing_today")
    suspend fun getAiringTodayTVShows(
        @Query("page") page: Int = 1
    ): TVShowResponse

    @GET("trending/tv/day")
    suspend fun getTrendingTVShows(
        @Query("page") page: Int = 1
    ): TVShowResponse

    @GET("search/multi")
    suspend fun searchMulti(
        @Query("query") query: String,
        @Query("page") page: Int = 1
    ): MovieResponse

    // New methods for detailed views
    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int
    ): MovieDetails

    @GET("tv/{tv_id}")
    suspend fun getTVShowDetails(
        @Path("tv_id") tvShowId: Int
    ): TVShowDetails

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") movieId: Int
    ): CreditsResponse

    @GET("tv/{tv_id}/credits")
    suspend fun getTVShowCredits(
        @Path("tv_id") tvShowId: Int
    ): CreditsResponse

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideos(
        @Path("movie_id") movieId: Int
    ): VideoResponse

    @GET("tv/{tv_id}/videos")
    suspend fun getTVShowVideos(
        @Path("tv_id") tvShowId: Int
    ): VideoResponse

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovies(
        @Path("movie_id") movieId: Int,
        @Query("page") page: Int = 1
    ): MovieResponse

    @GET("tv/{tv_id}/similar")
    suspend fun getSimilarTVShows(
        @Path("tv_id") tvShowId: Int,
        @Query("page") page: Int = 1
    ): TVShowResponse

    @GET("movie/{movie_id}/recommendations")
    suspend fun getMovieRecommendations(
        @Path("movie_id") movieId: Int,
        @Query("page") page: Int = 1
    ): MovieResponse

    @GET("tv/{tv_id}/recommendations")
    suspend fun getTVShowRecommendations(
        @Path("tv_id") tvShowId: Int,
        @Query("page") page: Int = 1
    ): TVShowResponse
}
