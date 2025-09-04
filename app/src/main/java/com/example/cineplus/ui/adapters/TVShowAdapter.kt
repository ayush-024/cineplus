package com.example.cineplus.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cineplus.R
import com.example.cineplus.databinding.ItemTvShowBinding
import com.example.cineplus.model.TVShow
import com.example.cineplus.utils.Constants

class TVShowAdapter(
    private var tvShows: List<TVShow>,
    private val onItemClick: (TVShow) -> Unit
) : RecyclerView.Adapter<TVShowAdapter.TVShowViewHolder>() {

    inner class TVShowViewHolder(private val binding: ItemTvShowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TVShow) {
            binding.apply {
                Glide.with(itemView)
                    .load(Constants.IMAGE_BASE_URL + tvShow.posterPath)
                    .placeholder(R.drawable.placeholder_poster)
                    .error(R.drawable.placeholder_poster)
                    .into(ivPoster)

                tvTitle.text = tvShow.name
                tvRating.text = tvShow.voteAverage.toString()

                itemView.setOnClickListener {
                    onItemClick(tvShow)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVShowViewHolder {
        val binding = ItemTvShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TVShowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TVShowViewHolder, position: Int) {
        holder.bind(tvShows[position])
    }

    override fun getItemCount(): Int = tvShows.size

    fun updateData(newTVShows: List<TVShow>) {
        tvShows = newTVShows
        notifyDataSetChanged()
    }
}
