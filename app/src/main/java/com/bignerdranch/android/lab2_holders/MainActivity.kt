package com.bignerdranch.android.lab2_holders

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bignerdranch.android.lab2_holders.api.RickAndMortyApiService
import com.bignerdranch.android.lab2_holders.ui.CharacterAdapter
import com.bignerdranch.android.lab2_holders.viewmodel.RickAndMortyViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CharacterAdapter
    lateinit var characterViewModel: RickAndMortyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.r_view)
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        characterViewModel = ViewModelProvider(this).get(RickAndMortyViewModel::class.java)

        characterViewModel.retrofitAPI(this)

        recyclerView.adapter = characterViewModel.adapter.value

        characterViewModel.recyclerView.observe(this, Observer {
            recyclerView = findViewById(R.id.r_view)
            recyclerView = it as RecyclerView
        })

        characterViewModel.adapter.observe(this, Observer {
            adapter = it
            recyclerView.adapter = adapter
        })
    }
}