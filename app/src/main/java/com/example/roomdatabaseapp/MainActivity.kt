package com.example.roomdatabaseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdatabaseapp.database.UserDatabase
import com.example.roomdatabaseapp.database.getDatabase
import com.example.roomdatabaseapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: UserAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var dataSource: UserDatabase

    private var number = 1L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        dataSource = getDatabase(this)

        binding.lifecycleOwner = this

        adapter = UserAdapter()
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter = adapter

        binding.addButton.setOnClickListener {
            val firstName = binding.editFirstname.text.toString().trim()
            val lastName = binding.editLastname.text.toString().trim()
            if(firstName.isNotEmpty() && lastName.isNotEmpty()){
                CoroutineScope(Dispatchers.Main).launch {
                    insertUser(User(firstName, lastName))
                }
            }else{
                Toast.makeText(this, "Fields can't be empty", Toast.LENGTH_SHORT).show()
            }

        }

        binding.clearButton.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                dataSource.userDao.deleteAll()
            }
        }

        dataSource.userDao.getUsers().observe(this,{
            adapter.submitList(it)
        })
    }

    private suspend fun insertUser(user: User) {
        withContext(Dispatchers.IO){
            dataSource.userDao.insert(user)
        }
    }

}