package com.example.roomdatabaseapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(

    @PrimaryKey(autoGenerate = true)
    var userId: Long = 0L,

    val firstName: String,

    val lastName: String,

)
