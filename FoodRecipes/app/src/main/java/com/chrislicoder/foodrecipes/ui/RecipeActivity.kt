package com.chrislicoder.foodrecipes.ui

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import com.chrislicoder.foodrecipes.R
import com.chrislicoder.foodrecipes.models.Recipe

private const val RECIPE_INTENT = "recipe"

class RecipeActivity : BaseActivity() {
    private lateinit var mRecipeImage: AppCompatImageView
    private lateinit var mRecipeTitle: TextView
    private lateinit var mRecipeRank: TextView
    private lateinit var mRecipeIngrdeintsContainer: LinearLayout
    private lateinit var mScrollView: ScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)
        mRecipeImage = findViewById(R.id.recipe_image)
        mRecipeTitle = findViewById(R.id.recipe_list)
        mRecipeRank = findViewById(R.id.recipe_social_score)
        mRecipeIngrdeintsContainer = findViewById(R.id.ingredients_container)
        mScrollView = findViewById(R.id.parent)

        getIncomingIntent()
    }

    private fun getIncomingIntent() {
        if (intent.hasExtra(RECIPE_INTENT)) {
            val recipe = intent.getParcelableExtra<Recipe>(
                RECIPE_INTENT
            )
        }
    }
}
