package com.ustwo.foodexplorer.presenter

import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.*
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.ustwo.foodexplorer.R
import com.ustwo.foodexplorer.model.Product
import kotlinx.android.synthetic.main.food_item_row.view.*
//import sun.security.krb5.internal.KDCOptions.with



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
            view.productName.text = "Loading"

            async {
                product.fetchData()

                async(UI) {
                    Picasso.get().isLoggingEnabled = true
                    Picasso.get().load(product.food?.imageURL).into(view.itemImage)
//                    Picasso.get().load(product.food?.grade).into(view.itemImage2)


//                    val image = findViewById(R.id.ivimage) as ImageView
//
//                    SvgLoader.pluck()
//                            .with(this)
//                            .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
//                            .load("http://www.clker.com/cliparts/u/Z/2/b/a/6/android-toy-h.svg", image)

                    view.productName.text = product.food?.name
                    view.productWeight.text = product.food?.quantity
                }
            }
        }
    }
}
