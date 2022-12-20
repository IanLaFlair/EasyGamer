package com.ian.core.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ian.core.R
import com.ian.core.databinding.ItemListBinding
import com.ian.core.domain.model.GamesModel
import java.util.ArrayList

class GamesAdapter : ListAdapter<GamesModel, GamesAdapter.ListViewHolder>(TaskDiffCallBack()) {

    var onItemClick: ((GamesModel) -> Unit)? = null

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListBinding.bind(itemView)
        fun bind(data: GamesModel) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.image)
                    .into(imgCover)
                txtTitle.text = data.name
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(getItem(bindingAdapterPosition))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false))


    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class TaskDiffCallBack : DiffUtil.ItemCallback<GamesModel>() {
        override fun areItemsTheSame(oldItem: GamesModel, newItem: GamesModel): Boolean {
            return oldItem.gamesId == newItem.gamesId;
        }

        override fun areContentsTheSame(oldItem: GamesModel, newItem: GamesModel): Boolean {
            return oldItem == newItem
        }
    }
}

