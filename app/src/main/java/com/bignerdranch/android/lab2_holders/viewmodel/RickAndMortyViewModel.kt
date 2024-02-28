package com.bignerdranch.android.lab2_holders.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.lab2_holders.api.RickAndMortyApiService
import com.bignerdranch.android.lab2_holders.data.Results
import com.bignerdranch.android.lab2_holders.ui.CharacterAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class RickAndMortyViewModel : ViewModel() {
    lateinit var charactersData: Results
    var adapter = MutableLiveData<CharacterAdapter>()
    var recyclerView = MutableLiveData<RecyclerView?>()

    fun retrofitAPI(context: Context) {
        var characterData: Results

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val charactersRetrofit = RickAndMortyApiService.create().getCharacters()

                withContext(Dispatchers.Main) {
                    charactersData = charactersRetrofit

                    adapter.postValue(CharacterAdapter(context, charactersData))

                    val recycler = RecyclerView(context)
                    recycler.adapter = adapter.value
                    recyclerView.postValue(recycler)
                }
            } catch (ex: Exception) {
            }
        }
    }
}