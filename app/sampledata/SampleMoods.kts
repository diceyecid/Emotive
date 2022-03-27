import java.util.*

// create a list of sample mood data
// this needs Mood to be edited to take in time upon initialization
private suspend fun loadSampleData()
{
    val db = AppDatabase.getDatabase( this ).appDao()
    var time : Calendar

    // 2021-3-23
    time = Calendar.getInstance()
    time.set( 2021, 2, 23 )
    db.insertMood( Mood( time, 1, "excited\n" + time.time.toString(), null ) )
    time = Calendar.getInstance()
    time.set( 2021, 2, 23 )
    db.insertMood( Mood( time, 3, "meh\n" + time.time.toString(), null ) )
    time = Calendar.getInstance()
    time.set( 2021, 2, 23 )
    db.insertMood( Mood( time, 5, "bad day\n" + time.time.toString(), null ) )

    // 2022-3-23
    time = Calendar.getInstance()
    time.set( 2022, 2, 23 )
    db.insertMood( Mood( time, 1, "excited\n" + time.time.toString(), null ) )
    time = Calendar.getInstance()
    time.set( 2022, 2, 23 )
    db.insertMood( Mood( time, 2, "happy\n" + time.time.toString(), null ) )
    time = Calendar.getInstance()
    time.set( 2022, 2, 23 )
    db.insertMood( Mood( time, 3, "meh\n" + time.time.toString(), null ) )

    // 2022-3-25
    time = Calendar.getInstance()
    time.set( 2022, 2, 25 )
    db.insertMood( Mood( time, 3, "meh\n" + time.time.toString(), null ) )
    time = Calendar.getInstance()
    time.set( 2022, 2, 25 )
    db.insertMood( Mood( time, 4, "sad\n" + time.time.toString(), null ) )
    time = Calendar.getInstance()
    time.set( 2022, 2, 25 )
    db.insertMood( Mood( time, 5, "cry\n" + time.time.toString(), null ) )

    // 2022-3-26
    time = Calendar.getInstance()
    time.set( 2022, 2, 26 )
    db.insertMood( Mood( time, 2, "joy\n" + time.time.toString(), null ) )
    time = Calendar.getInstance()
    time.set( 2022, 2, 26 )
    db.insertMood( Mood( time, 3, "meh\n" + time.time.toString(), null ) )
    time = Calendar.getInstance()
    time.set( 2022, 2, 26 )
    db.insertMood( Mood( time, 5, "cry\n" + time.time.toString(), null ) )
}