package com.example.emotive


data class Item(var type: Int,
                var price: Int,
                var path: String,
                var uid: Int
                )
{
    var typeName : String = when( type )
    {
        0 -> "Decoration"
        1 -> "Avatar"
        2 -> "Background"
        else -> ""
    }
}

