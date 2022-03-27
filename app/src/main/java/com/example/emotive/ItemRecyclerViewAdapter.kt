package com.example.emotive


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

class ItemRecyclerViewAdapter(private val itemList: List<Item> ): RecyclerView.Adapter<ItemRecyclerViewAdapter.ItemHolder>()  {
    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //val rewardIcon: ImageView = rewardView.findViewById(R.id.rewardIcon)
        val price: TextView = itemView.findViewById(R.id.price)

        //val imageButton: Button = itemView.findViewById(R.id.imageBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val rewardView =
            LayoutInflater.from(parent.context).inflate(R.layout.shop_card, parent, false)
        return ItemHolder(rewardView)
    }

    override fun onBindViewHolder(holder: ItemHolder, pos: Int) {
        //holder.rewardIcon.setImageResource(itemList[pos].resource)
        holder.price.text = itemList[pos].price.toString()
        //holder.imageButton.text = 'x' + itemList[pos].petal.toString()
        //holder.imageButton.setOnClickListener { v -> claimReward(v, itemList[pos]) }
    }

    override fun getItemCount() = itemList.size

    
}