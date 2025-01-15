package com.furkanakyel.visaapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FoodDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_detail)

        val foodName = intent.getStringExtra(EXTRA_FOOD_NAME)
        val foodIngredients = intent.getStringExtra(EXTRA_FOOD_INGREDIENTS)
        val foodRecipe = intent.getStringExtra(EXTRA_FOOD_RECIPE)

        findViewById<TextView>(R.id.tvFoodName).text = foodName
        findViewById<TextView>(R.id.tvIngredients).text = foodIngredients
        findViewById<TextView>(R.id.tvRecipe).text = foodRecipe
    }

    companion object {
        private const val EXTRA_FOOD_NAME = "foodName"
        private const val EXTRA_FOOD_INGREDIENTS = "foodIngredients"
        private const val EXTRA_FOOD_RECIPE = "foodRecipe"

        fun newIntent(context: Context, food: Food): Intent {
            return Intent(context, FoodDetailActivity::class.java).apply {
                putExtra(EXTRA_FOOD_NAME, food.name)
                putExtra(EXTRA_FOOD_INGREDIENTS, food.ingredients)
                putExtra(EXTRA_FOOD_RECIPE, food.recipe)
            }
        }
    }
}
