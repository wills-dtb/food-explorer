package com.ustwo.foodexplorer

import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.*
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.food_item_row.view.*

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

    class FoodItemHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
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

            view.itemName.text = "Loading"

            async {
                product.fetchData()

                async(UI) {
                    Picasso.get().isLoggingEnabled = true
                    Picasso.get().load(product.food?.imageURL).into(view.itemImage)

                    view.itemName.text = product.food?.name
                    view.itemQuantity.text = product.food?.quantity
                }
            }
        }
    }
}
