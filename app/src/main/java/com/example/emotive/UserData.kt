package com.example.emotive

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*
import kotlin.collections.ArrayList

// constants for shared preferences key name
private const val APP_NAME : String = "emotive"
private const val BASKET : String = "basket"
private const val BACKGROUND : String = "background"
private const val AVATAR : String = "avatar"
private const val DECORATION : String = "decoration"
private const val UNLOCKED_ITEMS : String = "unlockItems"
private const val LAST_INCENTIVE : String = "last incentive"
private const val UNCOLLECTED : String = "uncollected"

// Call UserData.getInstance( context ) to get an instance
class UserData private constructor( app : Context )
{
    private val gson : Gson = Gson()
    private val sharedPref : SharedPreferences = app.getSharedPreferences( APP_NAME, Context.MODE_PRIVATE )
    private val itemListType = object : TypeToken<ArrayList<Item>>(){}.type


    /********** user data **********/


    // fetch data from shared preferences upon initialization
    private var basket : Int = sharedPref.getInt( BASKET, 50 )
        set( amount )
        {
            field = amount
            liveBasket.postValue( field )
            with( sharedPref.edit() )
            {
                putInt( BASKET, field )
                apply()
            }
        }
    var background : Item? = gson.fromJson( sharedPref.getString( BACKGROUND, null ), Item::class.java )
        set( item )
        {
            field = item
            writeToSharePref( BACKGROUND, field )
        }
    var avatar : Item? = gson.fromJson( sharedPref.getString( AVATAR, null ), Item::class.java )
        set( item )
        {
            field = item
            writeToSharePref( AVATAR, field )
        }
    var decoration : Item? = gson.fromJson( sharedPref.getString( DECORATION, null ), Item::class.java )
        set( item )
        {
            field = item
            writeToSharePref( DECORATION, field )
        }
    var unlockedItems : ArrayList<Item> =
        gson.fromJson( sharedPref.getString( UNLOCKED_ITEMS, gson.toJson( ArrayList<Item>() ) ), itemListType )
        private set( items )
        {
            field = items
            writeToSharePref( UNLOCKED_ITEMS, field )
        }
    var lastIntensive : Long = sharedPref.getLong( LAST_INCENTIVE, 0 )
        set( time )
        {
            field = time
            with( sharedPref.edit() )
            {
                putLong( LAST_INCENTIVE, time )
                apply()
            }
        }
    var uncollected : Int = sharedPref.getInt( UNCOLLECTED, 0 )
        set( amount )
        {
            field = amount
            with( sharedPref.edit() )
            {
                putInt( UNCOLLECTED, field )
                apply()
            }
        }


    /********** live data **********/


    var liveBasket : MutableLiveData<Int> = MutableLiveData( basket )


    /********** setters **********/


    // write object to shared preferences
    private fun writeToSharePref( key : String, data : Any? )
    {
//        Log.d( "write sp", "writing " + key )
        val json = gson.toJson( data )
        with( sharedPref.edit() )
        {
            putString( key, json )
            apply()
        }
    }

    // only increment and decrement is allowed for basket
    fun addPetals( amount : Int ) { basket += amount }
    fun removePetals( amount : Int ) { basket -= amount }

    fun setPetals( amount : Int ) { basket = amount }

    // only append is allowed for unlocked items
    fun unlockItem( item : Item )
    {
        unlockedItems.add( item )
        writeToSharePref( UNLOCKED_ITEMS, unlockedItems )
    }

    // admin control: lock all items
    fun lockAllItems()
    {
        unlockedItems.clear()
        writeToSharePref( UNLOCKED_ITEMS, unlockedItems )
    }

    // get last instance in calendar
    fun getLastInstanceInCal() : Calendar
    {
        val cal = Calendar.getInstance()
        cal.timeInMillis = lastIntensive
        return cal
    }


    /********** get instance **********/


    // singleton pattern to ensure only a single UserData exists
    companion object
    {
        private var instance : UserData? = null

        fun getInstance( context : Context ) : UserData
        {
            if( instance == null )
            {
                synchronized( UserData::class )
                {
                    instance = UserData( context.applicationContext )
                }
            }

            return instance!!
        }
    }
}