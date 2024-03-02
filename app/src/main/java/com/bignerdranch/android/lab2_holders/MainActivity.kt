package com.bignerdranch.android.lab2_holders

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.os.PersistableBundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bignerdranch.android.lab2_holders.api.RickAndMortyApiService
import com.bignerdranch.android.lab2_holders.data.Results
import com.bignerdranch.android.lab2_holders.ui.CharacterAdapter
import com.bignerdranch.android.lab2_holders.viewmodel.RickAndMortyViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var charactersData: Results
    private lateinit var adapter: CharacterAdapter
    lateinit var characterViewModel: RickAndMortyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.r_view)
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        characterViewModel = ViewModelProvider(this).get(RickAndMortyViewModel::class.java)

        CoroutineScope(Dispatchers.Default).launch {
            characterViewModel.retrofitAPI()
        }

        characterViewModel.charactersData.observe(this, Observer {
            charactersData = it
            adapter = CharacterAdapter(this, it)
            recyclerView.adapter = adapter
        })
    }
}