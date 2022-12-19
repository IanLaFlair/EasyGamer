package com.ian.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ian.core.R
import com.ian.core.databinding.ItemListBinding
import com.ian.core.domain.model.GamesModel
import java.util.ArrayList

class GamesAdapter : RecyclerView.Adapter<GamesAdapter.ListViewHolder>() {

    private var listData = ArrayList<GamesModel>()
    var onItemClick: ((GamesModel) -> Unit)? = null

    fun setData(newListData: List<GamesModel>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

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
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false))


    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount() = listData.size
}