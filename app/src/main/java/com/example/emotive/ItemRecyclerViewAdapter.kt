package com.example.emotive


import android.content.Context
import android.content.Intent
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
        val lock: ImageView = itemView.findViewById(R.id.imageView2)


        //val imageButton: Button = itemView.findViewById(R.id.imageBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val rewardView =
            LayoutInflater.from(parent.context).inflate(R.layout.shop_card, parent, false)
        return ItemHolder(rewardView)
    }

    override fun onBindViewHolder(holder: ItemHolder, pos: Int) {
        //holder.rewardIcon.setImageResource(itemList[pos].resource)
        var user = UserData.getInstance(context)
        var unlocked : Int = 0
        if(user.unlockedItems.size!=0){
            for( item in 0 until user.unlockedItems.size){
                if(itemList[pos].uid==user.unlockedItems[item].uid){
                    unlocked=1
                }
            }
        }
        if(unlocked==1){
            holder.lock.setImageResource(R.drawable.empty_icon)
            holder.price.text = ""
        }
        else{
            holder.lock.setImageResource(R.drawable.lock_icon)
            holder.price.text = itemList[pos].price.toString()
        }
        val uri = "@drawable/myresource" // where myresource (without the extension) is the file

        val drawableId: Int = context.getResources().getIdentifier(itemList[pos].path, "drawable", context.getPackageName())
        //val imageResource: Int = getResources().getIdentifier(uri, null, getPackageName())
        //val res: Drawable = getResources().getDrawable(imageResource)
        holder.image.setImageResource(drawableId)
        //holder.imageButton.text = 'x' + itemList[pos].petal.toString()
        holder.image.setOnClickListener {
                v -> claimReward(v, itemList[pos], unlocked)
        }
    }

    override fun getItemCount() = itemList.size

    private fun claimReward(view: View, item: Item, lock: Int) {

        var user = UserData.getInstance(context)
        if (lock==0){
            user.removePetals(item.price)
            user.unlockItem(item)
        }
        if(item.type==0){
            user.decoration=item
            //user.(item.price)
        }
        else if(item.type==1){
            user.avatar=item
        }
        else if(item.type==2){
            user.background=item
        }

        val intent = Intent( view.context, MainActivity::class.java )
        //intent.putExtra( "item", item )
        view.context.startActivity( intent )
    }
}