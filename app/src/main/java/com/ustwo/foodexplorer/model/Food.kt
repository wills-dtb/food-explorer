package com.ustwo.foodexplorer.model

import org.json.JSONObject
import java.net.URL

data class Food(
        val name: String,
        val quantity: String,
        val barcode: String,
        val ingredientsURL: String,
        val imageURL: String
)

class Product(code: String) {
    var food: Food? = null
    private val code: String

    init {
        this.code = code
    }

    fun fetchData() {
        // Mock Server URL
        val url = URL("https://f7953da6-9465-4252-8f20-813ed3619842.mock.pstmn.io/$code")

        // Production URL
//        val url = URL("https://world.openfoodfacts.org/api/v0/product/$code.json")
        val json = url.readText()
        val jsonObj = JSONObject(json)
        val productObj = jsonObj.getJSONObject("product")

    food = Food(
                productObj.getString("product_name"),
                productObj.getString("quantity"),
                code,
                productObj.getString("image_ingredients_url"),
                productObj.getString("image_url")
        )
    }
}