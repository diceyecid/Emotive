package com.example.emotive

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// a recyclerview adapter for viewing Reward cards
class RewardRecyclerViewAdapter(private val rewardList: List<Reward> ): RecyclerView.Adapter<RewardRecyclerViewAdapter.RewardHolder>() {
    class RewardHolder(rewardView: View) : RecyclerView.ViewHolder(rewardView) {
        //val rewardIcon: ImageView = rewardView.findViewById(R.id.rewardIcon)
        val rewardText: TextView = rewardView.findViewById(R.id.rewardText)
        val imageButton: RelativeLayout = rewardView.findViewById(R.id.imageBtn)
        val rewardNumber: TextView = rewardView.findViewById(R.id.rewardNum)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RewardHolder {
        val rewardView =
            LayoutInflater.from(parent.context).inflate(R.layout.reward_card, parent, false)
        return RewardHolder(rewardView)
    }

    override fun onBindViewHolder(holder: RewardHolder, pos: Int) {
        //holder.rewardIcon.setImageResource(rewardList[pos].resource)
        holder.rewardText.text = rewardList[pos].text
        holder.rewardNumber.text = rewardList[pos].petal.toString()
        holder.imageButton.setOnClickListener { v -> claimReward(v, rewardList[pos]) }
    }

    override fun getItemCount() = rewardList.size

    // navigate to text entry to edit Reward
    private fun claimReward(view: View, Reward: Reward) {
        val intent = Intent(view.context, TextEntryActivity::class.java)
        intent.putExtra("Reward", Reward)
        view.context.startActivity(intent)
    }
}
