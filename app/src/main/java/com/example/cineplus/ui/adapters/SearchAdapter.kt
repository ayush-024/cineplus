package com.example.cineplus.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cineplus.R
import com.example.cineplus.databinding.ItemSearchBinding
import com.example.cineplus.model.Movie
import com.example.cineplus.utils.Constants

class SearchAdapter(
    private var items: List<Movie>,
    private val onItemClick: (Movie) -> Unit
) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    inner class SearchViewHolder(private val binding: ItemSearchBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movie) {
            binding.apply {
                Glide.with(itemView)
                    .load(Constants.IMAGE_BASE_URL + item.posterPath)
                    .placeholder(R.drawable.placeholder_poster)
                    .error(R.drawable.placeholder_poster)
                    .into(ivPoster)

                tvTitle.text = item.title ?: item.originalTitle
                tvOverview.text = item.overview
                tvRating.text = item.voteAverage.toString()

                itemView.setOnClickListener {
                    onItemClick(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun updateData(newItems: List<Movie>) {
        items = newItems
        notifyDataSetChanged()
    }
}
