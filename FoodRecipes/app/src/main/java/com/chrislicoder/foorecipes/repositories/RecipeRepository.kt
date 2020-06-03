package com.chrislicoder.foorecipes.repositories

import androidx.lifecycle.LiveData
import com.chrislicoder.foorecipes.models.Recipe
import com.chrislicoder.foorecipes.requests.RecipeApiClient

class RecipeRepository private constructor() {
    private val recipeClient = RecipeApiClient.instance

    val recipes: LiveData<List<Recipe>>
        get() = recipeClient.recipes

    companion object {
        val instance: RecipeRepository by lazy {
            RecipeRepository()
        }
    }
}
