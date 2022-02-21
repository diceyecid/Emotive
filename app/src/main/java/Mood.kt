import android.media.Image
import com.example.emotive.R
import java.io.Serializable
import java.util.*

// this class is an abstract representation of mood data
data class Mood( val value: Int, var text: String?, var imageList: ArrayList<Image>? ): Serializable
{
    /********** alternative constructors **********/

    constructor( value: Int, text: String ): this( value, text, null )
    constructor( value: Int ): this( value, null, null )


    /********** additional attributes **********/

    // timestamp when initialized
    val time: Calendar = Calendar.getInstance()

    // the drawable resource index
    val resource: Int = when( value )
    {
        1 -> R.drawable.tier01
        2 -> R.drawable.tier02
        3 -> R.drawable.tier03
        4 -> R.drawable.tier04
        5 -> R.drawable.tier05
        else -> -1
    }
}
