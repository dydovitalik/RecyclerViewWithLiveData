package com.example.recyclerviewwithlivedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.example.recyclerviewwithlivedata.viewmodel.RecyclerActivityViewModel
import kotlinx.android.synthetic.main.activity_recycler_view.*

class RecyclerViewActivity : AppCompatActivity() {

    lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        initRecyclerView()
        createData()
    }

       private fun initRecyclerView() {
           recyclerView.apply {
               layoutManager = LinearLayoutManager(this@RecyclerViewActivity)
               recyclerViewAdapter = RecyclerViewAdapter()
               adapter = recyclerViewAdapter

               val decoration = DividerItemDecoration(applicationContext, VERTICAL)
               addItemDecoration(decoration)
           }
       }

       fun createData() {

           val viewModel = ViewModelProviders.of(this).get(RecyclerActivityViewModel::class.java)
           viewModel.getRecyclerListDataObserver().observe(this, Observer<RecyclerList> {

               if (it != null){
                   recyclerViewAdapter.setListData(it.items!!)
                   recyclerViewAdapter.notifyDataSetChanged()
               } else {
                   Toast.makeText(this@RecyclerViewActivity, "Error in getting data from api.", Toast.LENGTH_LONG).show()
               }
           })
           viewModel.makeApiCall()
       }
}