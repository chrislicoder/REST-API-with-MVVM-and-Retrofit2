package com.chrislicoder.foorecipes.viewmodels

import androidx.lifecycle.ViewModel
import com.chrislicoder.foorecipes.repositories.RecipeRepository

class RecipeListViewModel : ViewModel() {
    private val mRecipeRepository: RecipeRepository by lazy {
        RecipeRepository.instance
    }

    val recipes = mRecipeRepository.recipes

    fun searchRecipes(query: String, pageNumber: Int) {
        mRecipeRepository.searchRecipesApi(query, pageNumber)
    }
}