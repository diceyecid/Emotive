package com.example.emotive


import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView


class ItemRecyclerViewAdapter(private val itemList: List<Item>,val context: Context): RecyclerView.Adapter<ItemRecyclerViewAdapter.ItemHolder>()  {
    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //val rewardIcon: ImageView = rewardView.findViewById(R.id.rewardIcon)
        val price: TextView = itemView.findViewById(R.id.price)
        val image: ImageView = itemView.findViewById(R.id.imageView)

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
        val uri = "@drawable/myresource" // where myresource (without the extension) is the file

        val drawableId: Int = context.getResources().getIdentifier(itemList[pos].path, "drawable", context.getPackageName())
        //val imageResource: Int = getResources().getIdentifier(uri, null, getPackageName())
        //val res: Drawable = getResources().getDrawable(imageResource)
        holder.image.setImageResource(drawableId)
        //holder.imageButton.text = 'x' + itemList[pos].petal.toString()
        //holder.imageButton.setOnClickListener { v -> claimReward(v, itemList[pos]) }
    }

    override fun getItemCount() = itemList.size

    
}