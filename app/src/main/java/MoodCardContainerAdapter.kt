import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.emotive.R

// a recyclerview adapter for viewing mood cards
class MoodCardContainerAdapter( private val moodList: ArrayList<MoodCard> ): RecyclerView.Adapter<MoodCardContainerAdapter.MoodCardHolder>()
{
    class MoodCardHolder( moodView: View): RecyclerView.ViewHolder( moodView )
    {
        val moodIcon: ImageView = moodView.findViewById( R.id.moodIcon )
        val moodText: TextView = moodView.findViewById( R.id.moodText )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int ): MoodCardHolder
    {
        val moodView = LayoutInflater.from( parent.context ).inflate( R.layout.mood_card, parent, false )
        return MoodCardHolder( moodView )
    }

    override fun onBindViewHolder( holder: MoodCardHolder, pos: Int )
    {
        holder.moodIcon.setImageResource( getMoodImage( moodList[pos].mood ) )
        holder.moodText.text = moodList[pos].moodText
    }

    override fun getItemCount() = moodList.size

    private fun getMoodImage( mood: Int ): Int
    {
        when( mood )
        {
            1 -> return R.drawable.tier01
            2 -> return R.drawable.tier02
            3 -> return R.drawable.tier03
            4 -> return R.drawable.tier04
            5 -> return R.drawable.tier05
        }

        return 0
    }
}