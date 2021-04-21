package com.example.roomdatabaseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdatabaseapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: UserAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        val newList = ArrayList<User>()
        newList.add(User(1L, "Savo", "Lekovic"))
        newList.add(User(2L, "Nikola", "Petrovic"))
        newList.add(User(3L, "Bata", "Familija"))


        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter = adapter
        adapter.submitList(newList)

    }
}