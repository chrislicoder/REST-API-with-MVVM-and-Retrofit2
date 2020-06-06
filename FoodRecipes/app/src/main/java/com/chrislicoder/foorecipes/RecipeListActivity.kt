package com.chrislicoder.foorecipes

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.chrislicoder.foorecipes.util.Testing
import com.chrislicoder.foorecipes.viewmodels.RecipeListViewModel
import kotlinx.android.synthetic.main.activity_recipe_list.*

private const val TAG = "RecipeListActivity"

class RecipeListActivity : BaseActivity() {
    private lateinit var mRecipeListViewModel: RecipeListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)

        mRecipeListViewModel = ViewModelProvider(this).get(RecipeListViewModel::class.java)

        subscribeObservers()

        test.setOnClickListener {
            testRetrofit()
        }
    }

    private fun subscribeObservers() {
        mRecipeListViewModel.recipes
            .observe(this, Observer { recipeList ->
                recipeList?.let {
                    Testing.printRecipes("network test", recipeList)
                }

            })
    }

    private fun testRetrofit() {
        mRecipeListViewModel.searchRecipes("chicken", 1)
    }
}
