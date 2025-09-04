package com.example.cineplus.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cineplus.databinding.FragmentMoviesBinding
import com.example.cineplus.ui.adapters.MovieAdapter
import com.example.cineplus.ui.viewmodels.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MoviesViewModel by viewModels()

    private lateinit var popularMoviesAdapter: MovieAdapter
    private lateinit var topRatedMoviesAdapter: MovieAdapter
    private lateinit var nowPlayingMoviesAdapter: MovieAdapter
    private lateinit var upcomingMoviesAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapters()
        setupObservers()
        setupSwipeRefresh()
    }

    private fun setupAdapters() {
        popularMoviesAdapter = MovieAdapter(emptyList()) { movie ->
            // Handle movie click
        }

        topRatedMoviesAdapter = MovieAdapter(emptyList()) { movie ->
            // Handle movie click
        }

        nowPlayingMoviesAdapter = MovieAdapter(emptyList()) { movie ->
            // Handle movie click
        }

        upcomingMoviesAdapter = MovieAdapter(emptyList()) { movie ->
            // Handle movie click
        }

        setupHorizontalRecyclerView(binding.rvPopularMovies, popularMoviesAdapter)
        setupHorizontalRecyclerView(binding.rvTopRatedMovies, topRatedMoviesAdapter)
        setupHorizontalRecyclerView(binding.rvNowPlayingMovies, nowPlayingMoviesAdapter)
        setupHorizontalRecyclerView(binding.rvUpcomingMovies, upcomingMoviesAdapter)
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

        viewModel.topRatedMovies.observe(viewLifecycleOwner) { movies ->
            topRatedMoviesAdapter.updateData(movies)
        }

        viewModel.nowPlayingMovies.observe(viewLifecycleOwner) { movies ->
            nowPlayingMoviesAdapter.updateData(movies)
        }

        viewModel.upcomingMovies.observe(viewLifecycleOwner) { movies ->
            upcomingMoviesAdapter.updateData(movies)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            binding.swipeRefresh.isRefreshing = isLoading
        }
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.loadMovies()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}