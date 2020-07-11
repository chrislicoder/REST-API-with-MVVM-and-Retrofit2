package com.chrislicoder.foodrecipes.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.chrislicoder.foodrecipes.models.Recipe
import com.chrislicoder.foodrecipes.repositories.RecipeRepository

class RecipeViewModel : ViewModel() {
    private val mRecipeRepository: RecipeRepository by lazy {
        RecipeRepository.instance
    }

    lateinit var recipeId: String;

    fun getRecipe(): LiveData<Recipe> {
        return mRecipeRepository.recipe
    }

    fun searchRecipeById(recipeId: String) {
        this.recipeId = recipeId
        mRecipeRepository.searchRecipeById(recipeId)
    }
}
