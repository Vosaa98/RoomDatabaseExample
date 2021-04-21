package com.example.roomdatabaseapp.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomdatabaseapp.User

@Dao
interface UserDao {

    @Insert
    fun insert(user: User)

    @Query("SELECT * FROM User ORDER BY userId DESC")
    fun getUsers(): LiveData<List<User>>
}

@Database(entities = [User::class], version = 1)
abstract class UserDatabase: RoomDatabase() {
    abstract val userDao: UserDao
}

private lateinit var INSTANCE: UserDatabase

fun getDatabase(contest: Context): UserDatabase{
    synchronized(UserDatabase::class.java){
        if(!::INSTANCE.isInitialized){
            INSTANCE = Room.databaseBuilder(contest.applicationContext,
                UserDatabase::class.java,
                "users").build()
        }
    }
    return INSTANCE
}