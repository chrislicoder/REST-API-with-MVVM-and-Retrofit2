package com.chrislicoder.foodrecipes.ui

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chrislicoder.foodrecipes.R
import com.chrislicoder.foodrecipes.models.Recipe
import com.chrislicoder.foodrecipes.viewmodels.RecipeViewModel

class RecipeActivity : BaseActivity() {
    private lateinit var mRecipeImage: AppCompatImageView
    private lateinit var mRecipeTitle: TextView
    private lateinit var mRecipeRank: TextView
    private lateinit var mRecipeIngrdeintsContainer: LinearLayout
    private lateinit var mScrollView: ScrollView
    private lateinit var mRecipeViewModel: RecipeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)
        mRecipeImage = findViewById(R.id.recipe_image)
        mRecipeTitle = findViewById(R.id.recipe_title)
        mRecipeRank = findViewById(R.id.recipe_social_score)
        mRecipeIngrdeintsContainer = findViewById(R.id.ingredients_container)
        mScrollView = findViewById(R.id.parent)

        mRecipeViewModel = ViewModelProvider(this).get(RecipeViewModel::class.java)
        showProgressBar(true)
        subscribeObservers()
        getIncomingIntent()
    }

    private fun getIncomingIntent() {
        if (intent.hasExtra(RECIPE_INTENT)) {
            intent.getParcelableExtra<Recipe>(
                RECIPE_INTENT
            )?.let {
                mRecipeViewModel.searchRecipeById(it.recipe_id!!)
            }
        }
    }

    private fun subscribeObservers() {
        mRecipeViewModel.recipes.observe(
            this,
            Observer { recipe ->
                recipe?.let {
                    if (recipe.recipe_id.equals(mRecipeViewModel.recipeId)) {
                        setRecipeProperties(it)
                        mRecipeViewModel.mDidRetrieveRecipe = true
                    }
                }
            }
        )

        mRecipeViewModel.isRecipeRequestTimeOut.observe(
            this,
            Observer {
                if (it && !mRecipeViewModel.mDidRetrieveRecipe) {
                    displayErrorScreen(getString(R.string.error_retrieve_message))
                }
            }
        )
    }

    private fun displayErrorScreen(errorMessage: String) {
        mRecipeTitle.text = getString(R.string.error_retrieve_message)
        mRecipeRank.text = ""
        TextView(this).apply {
            if (!TextUtils.isEmpty(errorMessage)) {
                text = errorMessage
            } else {
                text = getString(R.string.error_retrieve_message)
            }
            textSize = 15F
            layoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        }.also {
            mRecipeIngrdeintsContainer.addView(it)
        }

        Glide.with(this)
            .setDefaultRequestOptions(RequestOptions().placeholder(R.drawable.ic_launcher_background))
            .load(R.drawable.ic_launcher_background)
            .into(mRecipeImage)

        showParent()
        showProgressBar(false)
        mRecipeViewModel.mDidRetrieveRecipe = true
    }

    private fun setRecipeProperties(recipe: Recipe) {
        Glide.with(this)
            .setDefaultRequestOptions(RequestOptions().placeholder(R.drawable.ic_launcher_background))
            .load(recipe.image_url)
            .into(mRecipeImage)

        mRecipeTitle.text = recipe.title
        mRecipeRank.text = recipe.social_rank.toString()
        for (ingredients in recipe.ingredients!!) {
            TextView(this).apply {
                text = ingredients
                textSize = 15F
                layoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
            }.also {
                mRecipeIngrdeintsContainer.addView(it)
            }
        }

        showParent()
        showProgressBar(false)
    }

    private fun showParent() {
        mScrollView.visibility = View.VISIBLE
    }

    companion object {
        const val RECIPE_INTENT = "recipe"
    }
}
