package com.example.cineplus.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cineplus.databinding.FragmentTvShowsBinding
import com.example.cineplus.ui.adapters.TVShowAdapter
import com.example.cineplus.ui.viewmodels.TVShowsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TVShowsFragment : Fragment() {

    private var _binding: FragmentTvShowsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TVShowsViewModel by viewModels()

    private lateinit var popularTVShowsAdapter: TVShowAdapter
    private lateinit var topRatedTVShowsAdapter: TVShowAdapter
    private lateinit var onTheAirTVShowsAdapter: TVShowAdapter
    private lateinit var airingTodayTVShowsAdapter: TVShowAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvShowsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapters()
        setupObservers()
        setupSwipeRefresh()
    }

    private fun setupAdapters() {
        popularTVShowsAdapter = TVShowAdapter(emptyList()) { tvShow ->
            // Handle TV show click
        }

        topRatedTVShowsAdapter = TVShowAdapter(emptyList()) { tvShow ->
            // Handle TV show click
        }

        onTheAirTVShowsAdapter = TVShowAdapter(emptyList()) { tvShow ->
            // Handle TV show click
        }

        airingTodayTVShowsAdapter = TVShowAdapter(emptyList()) { tvShow ->
            // Handle TV show click
        }

        setupHorizontalRecyclerView(binding.rvPopularTvShows, popularTVShowsAdapter)
        setupHorizontalRecyclerView(binding.rvTopRatedTvShows, topRatedTVShowsAdapter)
        setupHorizontalRecyclerView(binding.rvOnTheAirTvShows, onTheAirTVShowsAdapter)
        setupHorizontalRecyclerView(binding.rvAiringTodayTvShows, airingTodayTVShowsAdapter)
    }

    private fun setupHorizontalRecyclerView(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<*>) {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            this.adapter = adapter
            setHasFixedSize(true)
        }
    }

    private fun setupObservers() {
        viewModel.popularTVShows.observe(viewLifecycleOwner) { tvShows ->
            popularTVShowsAdapter.updateData(tvShows)
        }

        viewModel.topRatedTVShows.observe(viewLifecycleOwner) { tvShows ->
            topRatedTVShowsAdapter.updateData(tvShows)
        }

        viewModel.onTheAirTVShows.observe(viewLifecycleOwner) { tvShows ->
            onTheAirTVShowsAdapter.updateData(tvShows)
        }

        viewModel.airingTodayTVShows.observe(viewLifecycleOwner) { tvShows ->
            airingTodayTVShowsAdapter.updateData(tvShows)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            binding.swipeRefresh.isRefreshing = isLoading
        }
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.loadTVShows()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
