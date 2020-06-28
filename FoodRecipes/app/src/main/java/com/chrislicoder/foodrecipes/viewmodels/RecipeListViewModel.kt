package com.chrislicoder.foodrecipes.viewmodels

import androidx.lifecycle.ViewModel
import com.chrislicoder.foodrecipes.repositories.RecipeRepository

class RecipeListViewModel : ViewModel() {
   var isViewingRecipes =false
    private val mRecipeRepository: RecipeRepository by lazy {
        RecipeRepository.instance
    }

    val recipes = mRecipeRepository.recipes

    fun searchRecipes(query: String, pageNumber: Int) {
        isViewingRecipes = true
        mRecipeRepository.searchRecipesApi(query, pageNumber)
    }

    fun onBackPressed(): Boolean {
        return if (isViewingRecipes) {
            isViewingRecipes = false
            false
        } else true
    }
}
