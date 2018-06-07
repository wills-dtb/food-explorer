package com.ustwo.foodexplorer.view

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.DividerItemDecoration
import com.ustwo.foodexplorer.presenter.FoodItemsRecyclerAdapter
import com.ustwo.foodexplorer.R
import com.ustwo.foodexplorer.model.Product
import kotlinx.android.synthetic.main.activity_main.*
import com.bumptech.glide.load.engine.DiskCacheStrategy
import sun.tools.jconsole.Messages.SOURCE
import com.caverock.androidsvg.SVG
import android.graphics.drawable.PictureDrawable
import android.net.Uri
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.model.StreamEncoder
import java.io.InputStream
import android.R.transition
import android.widget.ImageView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade


class MainActivity : Activity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: FoodItemsRecyclerAdapter
    private var imageViewRes: ImageView
    private var imageViewNet: ImageView
    private RequestBuilder<PictureDrawablerequestBuilder
    LinearLayout ll

    private val foods: ArrayList<Product> =
        arrayListOf(
            // "Excellence Mint Intense Dark"
                Product(
                        "3046920028752"
                ),
            // "Coca Cola Zero"
                Product(
                        "5449000131805"
                ),
            // "Plantain Chips"
                Product(
                        "0832697000595"
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



        imageViewNet = findViewById(R.id.svg_image_view2) as ImageView

        requestBuilder = GlideApp.with(this)
                .`as`(PictureDrawable::class.java)
                .placeholder(R.drawable.image_loading)
                .error(R.drawable.image_error)
                .transition(withCrossFade())
                .listener(SvgSoftwareLayerSetter())

        val uri = Uri.parse("http://www.clker.com/cliparts/u/Z/2/b/a/6/android-toy-h.svg")
        requestBuilder.load(uri).into(imageViewNet)
    }
}
