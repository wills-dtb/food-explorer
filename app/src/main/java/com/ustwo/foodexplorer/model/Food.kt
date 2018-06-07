package com.ustwo.foodexplorer.model

import org.json.JSONObject
import java.net.URL

data class Food(
        val name: String,
        val quantity: String,
        val barcode: String,
//        val grade: String,
        val imageURL: String
)

class Product(code: String) {
    var food: Food? = null
    private val code: String

    init {
        this.code = code
    }

    fun fetchData() {
        val url = URL("https://world.openfoodfacts.org/api/v0/product/$code.json")
        val json = url.readText()
        val jsonObj = JSONObject(json)
        val productObj = jsonObj.getJSONObject("product")
//        val nutritionGrade = productObj.getString("nutrition_grade")

//        val url2 = URL("https://static.openfoodfacts.org/images/misc/nutriscore-$nutritionGrade.svg")
//        val nutriscore = url2.readText()

    food = Food(
                productObj.getString("product_name"),
                productObj.getString("quantity"),
                code,
//            "https://static.openfoodfacts.org/images/misc/nutriscore-$nutritionGrade.svg",
                productObj.getString("image_url")
        )
    }
//    fun fetchSVGData() {
//        val url = URL("https://static.openfoodfacts.org/images/misc/nutriscore-$nutritionGrade.svg")
//        val json = urlGrade.readText()
//        val jsonObj = JSONObject(json)
//
//        food = Food(
//                productObj.getString("product_name"),
//                productObj.getString("quantity"),
//                code,
//                nutritionGrade,
//                productObj.getString("image_url")
//        )
//    }
}