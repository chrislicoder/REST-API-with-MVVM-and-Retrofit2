package com.chrislicoder.foorecipes.repositories

import androidx.lifecycle.LiveData
import com.chrislicoder.foorecipes.models.Recipe
import com.chrislicoder.foorecipes.requests.RecipeApiClient

class RecipeRepository private constructor() {
    private val recipeClient by lazy { RecipeApiClient.instance }

    val recipes: LiveData<List<Recipe>?>
        get() = recipeClient.recipes

    fun searchRecipesApi(query: String, pageNumber: Int) {
        var number = pageNumber
        if (number == 0) {
            number = 1
        }
        recipeClient.searchRecipesApi(query, number)
    }

    companion object {
        val instance: RecipeRepository by lazy {
            RecipeRepository()
        }
    }
}
