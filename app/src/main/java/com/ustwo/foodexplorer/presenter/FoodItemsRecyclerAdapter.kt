package com.ustwo.foodexplorer.presenter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.ustwo.foodexplorer.R
import com.ustwo.foodexplorer.model.Product
import kotlinx.android.synthetic.main.food_item_row.view.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async


class FoodItemsRecyclerAdapter(private val foods: ArrayList<Product>) :
    RecyclerView.Adapter<FoodItemsRecyclerAdapter.FoodItemHolder>() {

    override fun getItemCount() = foods.size


    override fun onBindViewHolder(holder: FoodItemHolder, position: Int) {
        val itemFood = foods[position]
        holder.bindFood(itemFood)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodItemHolder {
        val inflatedView = parent.inflate(R.layout.food_item_row, false)
        return FoodItemHolder(inflatedView)
    }

    // ---
    inner class FoodItemHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view: View = v
        private var product: Product? = null

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            Log.d("RecyclerView", "CLICK!")
        }

        fun bindFood(product: Product) {
            this.product = product
            view.productName.text = "Loading"

            async {
                product.fetchData()

                async(UI) {
                    Picasso.get().isLoggingEnabled = true
                    Picasso.get().load(product.food?.imageURL).into(view.itemImage)
                    Picasso.get().load(product.food?.ingredientsURL).into(view.itemImage2)

                    view.productName.text = product.food?.name
                    view.productWeight.text = product.food?.quantity
                }
            }
        }
    }
}

