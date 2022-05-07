package com.example.recyclerviewwithlivedata.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recyclerviewwithlivedata.RecyclerList
import com.example.recyclerviewwithlivedata.network.RetroInstance
import com.example.recyclerviewwithlivedata.network.RetroService
import retrofit2.Call
import retrofit2.Response


class RecyclerActivityViewModel : ViewModel(){

    lateinit var recyclerListData:MutableLiveData<RecyclerList>

    init {
        recyclerListData = MutableLiveData()
    }

    fun getRecyclerListDataObserver():MutableLiveData<RecyclerList> {
        return recyclerListData
    }

    fun makeApiCall() {
        val retroInstance = RetroInstance.getRetrofitInstance().create(RetroService::class.java)
        val call = retroInstance.getDataFromAPI("newyork")
        call.enqueue(object : retrofit2.Callback<RecyclerList>{
            override fun onResponse(call: Call<RecyclerList>, response: Response<RecyclerList>) {
                if(response.isSuccessful) {
                    recyclerListData.postValue(response.body())
                }else {
                    recyclerListData.postValue(null)
                }
            }
            override fun onFailure(call: Call<RecyclerList>, t: Throwable) {
                recyclerListData.postValue(null)
            }
        })
    }
}