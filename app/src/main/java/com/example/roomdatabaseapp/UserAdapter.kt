package com.example.roomdatabaseapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabaseapp.databinding.UserItemBinding

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    var list = ArrayList<User>()

    fun submitList(list: ArrayList<User>){
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    class UserViewHolder private constructor(val binding: UserItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: User){
            binding.user = item
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup): UserViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = UserItemBinding.inflate(layoutInflater, parent, false)
                return UserViewHolder(binding)
            }
        }
    }
}