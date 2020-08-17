package com.chrislicoder.foodrecipes.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.chrislicoder.foodrecipes.models.Recipe
import com.chrislicoder.foodrecipes.repositories.RecipeRepository

class RecipeViewModel : ViewModel() {
    private val mRecipeRepository: RecipeRepository by lazy {
        RecipeRepository.instance
    }

    lateinit var recipeId: String

    var mDidRetrieveRecipe: Boolean = false

    val recipes = mRecipeRepository.recipe

    val isRecipeRequestTimeOut = mRecipeRepository.isRecipeRequestTimeOut

    fun searchRecipeById(recipeId: String) {
        this.recipeId = recipeId
        mRecipeRepository.searchRecipeById(recipeId)
    }
}
