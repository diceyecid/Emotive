package com.example.emotive

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// constants for shared preferences key name
private const val APP_NAME : String = "emotive"
private const val BASKET : String = "basket"
private const val BACKGROUND : String = "background"
private const val AVATAR : String = "avatar"
private const val DECORATION : String = "decoration"
private const val UNLOCKED_ITEMS : String = "unlockItems"

// Call UserData.getInstance( context ) to get an instance
class UserData private constructor( app : Context )
{
    private val gson : Gson = Gson()
    private val sharedPref : SharedPreferences = app.getSharedPreferences( APP_NAME, Context.MODE_PRIVATE )
    private val itemListType = object : TypeToken<ArrayList<Item>>(){}.type


    /********** user data **********/


    // fetch data from shared preferences upon initialization
    var basket : Int = sharedPref.getInt( BASKET, 0 )
        private set( amount )
        {
            field = amount
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


    /********** setters **********/


    // write object to shared preferences
    fun writeToSharePref( key : String, data : Any? )
    {
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

    // only append is allowed for unlocked items
    fun UnlockedItem( item : Item ) { unlockedItems.add( item ) }


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