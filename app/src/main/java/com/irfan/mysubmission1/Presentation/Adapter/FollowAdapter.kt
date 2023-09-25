package com.irfan.mysubmission1.Presentation.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.irfan.mysubmission1.Presentation.ui.DetailActivity
import com.irfan.mysubmission1.data.response.FollowResponse
import com.irfan.mysubmission1.databinding.ItemListBinding

class FollowAdapter: ListAdapter<FollowResponse, FollowAdapter.FollowHolder>(DIFF_CALLBACK){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowHolder(binding)
    }

    class FollowHolder(private var binding: ItemListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(followers: FollowResponse){
            Glide.with(itemView)
                .load(followers.avatarUrl)
                .into(binding.itemImgFollower)
            binding.itemUsername.text = followers.login
        }
    }

    override fun onBindViewHolder(holder: FollowHolder, position: Int) {
        val listFollowers = getItem(position)

        holder.apply {
            bind(listFollowers)
            itemView.setOnClickListener {
                val intentToDetail = Intent(itemView.context, DetailActivity::class.java)
                intentToDetail.putExtra(DetailActivity.KEY_TAG, listFollowers.login)
                itemView.context.startActivity(intentToDetail)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FollowResponse>() {
            override fun areItemsTheSame(oldItem: FollowResponse, newItem: FollowResponse): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: FollowResponse, newItem: FollowResponse): Boolean {
                return oldItem == newItem
            }
        }
    }
}
