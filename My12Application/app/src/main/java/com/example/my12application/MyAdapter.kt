package com.example.my12application

import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.example.my12application.databinding.ItemRecyclerviewBinding


class MyViewHolder(val binding: ItemRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root)

class MyAdapter(val datas: MutableList<String>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var context: Context

    override fun getItemCount(): Int {
        return datas?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return   MyViewHolder(
            ItemRecyclerviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ))
    }




    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MyViewHolder).binding
        // item_reclerview.xml의 id itemData의 내용
        binding.itemData.text = datas!![position]

         var sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
         val txColor:String? = sharedPreferences.getString("tx_color", "")
         binding.itemData.setTextColor(Color.parseColor(txColor))

    }
}
