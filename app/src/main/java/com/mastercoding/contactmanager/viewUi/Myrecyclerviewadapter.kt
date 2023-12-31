package com.mastercoding.contactmanager.viewUi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mastercoding.contactmanager.R
import com.mastercoding.contactmanager.databinding.CardItemBinding
import com.mastercoding.contactmanager.room.User

class Myrecyclerviewadapter(private val usersList : List<User>,private val clicklistener : (User)->Unit) : RecyclerView.Adapter<MyViewholder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewholder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding:CardItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.card_item, parent, false)
        return MyViewholder(binding)
    }

    override fun onBindViewHolder(holder: MyViewholder, position: Int) {
        holder.bind(usersList[position], clicklistener)
    }

    override fun getItemCount(): Int {
        return usersList.size
    }
}

class MyViewholder(val binding : CardItemBinding):RecyclerView.ViewHolder(binding.root){
    fun bind(user: User, clicklistener: (User) -> Unit){
        binding.nameTextview.text = user.name
        binding.phnumtext.text = user.phnum

        binding.listitemlayout.setOnClickListener(){
            clicklistener(user)
        }
    }
}
