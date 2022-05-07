package com.example.recyclerviewwithlivedata.network

import retrofit2.Call
import com.example.recyclerviewwithlivedata.RecyclerList
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {

    //api.github.com/search/repositories?q=newyork
    @GET("repositories")
    fun getDataFromAPI(@Query("q") query: String):Call<RecyclerList>
}