package com.example.emotive

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_text_entry.*
import java.net.URI

// a recyclerview adapter for viewing mood cards
class MoodRecyclerViewAdapter(private val moodList: List<Mood> ): RecyclerView.Adapter<MoodRecyclerViewAdapter.MoodHolder>()
{
    class MoodHolder(moodView: View ): RecyclerView.ViewHolder( moodView )
    {
        val moodIcon: ImageView = moodView.findViewById( R.id.moodIcon )
        val moodText: TextView = moodView.findViewById( R.id.moodText )
        val editButton: ImageButton = moodView.findViewById( R.id.editButton )
        val moodImage: ImageView = moodView.findViewById( R.id.inputPhoto)
    }

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int ): MoodHolder
    {
        val moodView = LayoutInflater.from( parent.context ).inflate( R.layout.mood_card, parent, false )
        return MoodHolder( moodView )
    }

    override fun onBindViewHolder(holder: MoodHolder, pos: Int )
    {
        holder.moodIcon.setImageResource( moodList[pos].resource )
        holder.moodText.text = moodList[pos].text
        if( !moodList[pos].uri.isNullOrBlank()){
            holder.moodImage.setImageURI(moodList[pos].uri!!.toUri())
            }
        else{
            holder.moodImage.setImageResource(R.drawable.empty_icon)
        }
        holder.editButton.setOnClickListener{ v -> editMood( v, moodList[pos] ) }
    }

    override fun getItemCount() = moodList.size

    // navigate to text entry to edit mood
    private fun editMood( view: View, mood: Mood)
    {
        val intent = Intent( view.context, TextEntryActivity::class.java )
        intent.putExtra( "mood", mood )
        view.context.startActivity( intent )
    }
    /*
    private fun URIsetter( pos : Int ): Uri
    {
        val stringUri : Uri

        // if mood has photo and all of the elements in moods does not have photo
        return Uri
    }

     */
}