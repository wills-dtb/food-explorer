package com.ustwo.foodexplorer

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: FoodItemsRecyclerAdapter

    private val foods: ArrayList<Product> =
        arrayListOf(
            // "Excellence Mint Intense Dark"
            Product(
                "3046920028752"
            ),
            // "Coca Cola Zero"
            Product(
                "5449000131805"
            )
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager

        adapter = FoodItemsRecyclerAdapter(foods)
        recyclerView.adapter = adapter

        val decoration = DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decoration)
    }
}
