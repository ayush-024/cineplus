package com.example.cineplus.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cineplus.databinding.FragmentHomeBinding
import com.example.cineplus.ui.adapters.MovieAdapter
import com.example.cineplus.ui.adapters.TVShowAdapter
import com.example.cineplus.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()

    private lateinit var popularMoviesAdapter: MovieAdapter
    private lateinit var trendingMoviesAdapter: MovieAdapter
    private lateinit var topRatedMoviesAdapter: MovieAdapter
    private lateinit var nowPlayingMoviesAdapter: MovieAdapter
    private lateinit var popularTVShowsAdapter: TVShowAdapter
    private lateinit var trendingTVShowsAdapter: TVShowAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapters()
        setupObservers()
    }

    private fun setupAdapters() {
        // Movies
        popularMoviesAdapter = MovieAdapter(emptyList()) { movie ->
            // Handle movie click
        }

        trendingMoviesAdapter = MovieAdapter(emptyList()) { movie ->
            // Handle movie click
        }

        topRatedMoviesAdapter = MovieAdapter(emptyList()) { movie ->
            // Handle movie click
        }

        nowPlayingMoviesAdapter = MovieAdapter(emptyList()) { movie ->
            // Handle movie click
        }

        // TV Shows
        popularTVShowsAdapter = TVShowAdapter(emptyList()) { tvShow ->
            // Handle TV show click
        }

        trendingTVShowsAdapter = TVShowAdapter(emptyList()) { tvShow ->
            // Handle TV show click
        }

        setupHorizontalRecyclerView(binding.rvPopularMovies, popularMoviesAdapter)
        setupHorizontalRecyclerView(binding.rvTrendingMovies, trendingMoviesAdapter)
        setupHorizontalRecyclerView(binding.rvTopRatedMovies, topRatedMoviesAdapter)
        setupHorizontalRecyclerView(binding.rvNowPlayingMovies, nowPlayingMoviesAdapter)
        setupHorizontalRecyclerView(binding.rvPopularTvShows, popularTVShowsAdapter)
        setupHorizontalRecyclerView(binding.rvTrendingTvShows, trendingTVShowsAdapter)
    }

    private fun setupHorizontalRecyclerView(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<*>) {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            this.adapter = adapter
            setHasFixedSize(true)
        }
    }

    private fun setupObservers() {
        viewModel.popularMovies.observe(viewLifecycleOwner) { movies ->
            popularMoviesAdapter.updateData(movies)
        }

        viewModel.trendingMovies.observe(viewLifecycleOwner) { movies ->
            trendingMoviesAdapter.updateData(movies)
        }

        viewModel.topRatedMovies.observe(viewLifecycleOwner) { movies ->
            topRatedMoviesAdapter.updateData(movies)
        }

        viewModel.nowPlayingMovies.observe(viewLifecycleOwner) { movies ->
            nowPlayingMoviesAdapter.updateData(movies)
        }

        viewModel.popularTVShows.observe(viewLifecycleOwner) { tvShows ->
            popularTVShowsAdapter.updateData(tvShows)
        }

        viewModel.trendingTVShows.observe(viewLifecycleOwner) { tvShows ->
            trendingTVShowsAdapter.updateData(tvShows)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
