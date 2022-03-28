package com.example.emotive

import android.app.Application
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView

// a recyclerview adapter for viewing Reward cards
class RewardRecyclerViewAdapter(
    private val rewardList: List<Reward>,
    private val viewModel : RewardViewModel
    ): RecyclerView.Adapter<RewardRecyclerViewAdapter.RewardHolder>()
{

    class RewardHolder(rewardView: View) : RecyclerView.ViewHolder(rewardView) {
        //val rewardIcon: ImageView = rewardView.findViewById(R.id.rewardIcon)
        val rewardText: TextView = rewardView.findViewById(R.id.rewardText)
        val imageButton: Button = rewardView.findViewById(R.id.imageBtn)
        val userData = UserData.getInstance( rewardView.context )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RewardHolder {
        val rewardView =
            LayoutInflater.from(parent.context).inflate(R.layout.reward_card, parent, false)
        return RewardHolder(rewardView)
    }

    override fun onBindViewHolder(holder: RewardHolder, pos: Int) {
        //holder.rewardIcon.setImageResource(rewardList[pos].resource)
        holder.rewardText.text = rewardList[pos].text
        holder.imageButton.text = 'x' + rewardList[pos].petal.toString()
        holder.imageButton.setOnClickListener {
            holder.userData.addPetals( rewardList[pos].petal )
            viewModel.deleteReward( rewardList[pos] )
        }
    }

    override fun getItemCount() = rewardList.size
}
