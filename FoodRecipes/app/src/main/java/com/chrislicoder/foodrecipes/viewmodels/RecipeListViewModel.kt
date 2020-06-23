package com.chrislicoder.foodrecipes.viewmodels

import androidx.lifecycle.ViewModel
import com.chrislicoder.foodrecipes.repositories.RecipeRepository

class RecipeListViewModel : ViewModel() {
    private val mRecipeRepository: RecipeRepository by lazy {
        RecipeRepository.instance
    }

    val recipes = mRecipeRepository.recipes

    fun searchRecipes(query: String, pageNumber: Int) {
        mRecipeRepository.searchRecipesApi(query, pageNumber)
    }
}
