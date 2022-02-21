import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.emotive.R
import com.example.emotive.TextEntryActivity

// a recyclerview adapter for viewing mood cards
class MoodRecyclerViewAdapter(private val moodList: ArrayList<Mood> ): RecyclerView.Adapter<MoodRecyclerViewAdapter.MoodHolder>()
{
    class MoodHolder(moodView: View ): RecyclerView.ViewHolder( moodView )
    {
        val moodIcon: ImageView = moodView.findViewById( R.id.moodIcon )
        val moodText: TextView = moodView.findViewById( R.id.moodText )
        val editButton: ImageButton = moodView.findViewById( R.id.editButton )
    }

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int ): MoodHolder
    {
        val moodView = LayoutInflater.from( parent.context ).inflate( R.layout.mood_card, parent, false )
        return MoodHolder( moodView )
    }

    override fun onBindViewHolder( holder: MoodHolder, pos: Int )
    {
        holder.moodIcon.setImageResource( moodList[pos].resource )
        holder.moodText.text = moodList[pos].text
        holder.editButton.setOnClickListener{ v -> editMood( v, moodList[pos] ) }
    }

    override fun getItemCount() = moodList.size

    // navigate to text entry to edit mood
    private fun editMood( view: View, mood: Mood )
    {
        val intent = Intent( view.context, TextEntryActivity::class.java )
        intent.putExtra( "mood", mood )
        view.context.startActivity( intent )
    }
}