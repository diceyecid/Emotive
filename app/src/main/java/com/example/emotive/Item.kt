package com.example.emotive

import java.io.Serializable

data class Item(var type: Int,
                var price: Int,
                var path: String,
                var uid: Int
                ) : Serializable
{
}

