package com.chrislicoder.foodrecipes.repositories

import androidx.lifecycle.LiveData
import com.chrislicoder.foodrecipes.models.Recipe
import com.chrislicoder.foodrecipes.requests.RecipeApiClient

class RecipeRepository private constructor() {
    private val recipeClient by lazy { RecipeApiClient.instance }
    lateinit var mQuery: String
    var mPageNumber: Int = 0

    val recipes: LiveData<List<Recipe>?>
        get() = recipeClient.recipes

    fun searchRecipesApi(query: String, pageNumber: Int) {
        var number = pageNumber
        if (number == 0) {
            number = 1
        }
        mQuery = query
        mPageNumber = pageNumber
        recipeClient.searchRecipesApi(query, number)
    }

    fun searchNewPage() {
        searchRecipesApi(mQuery, mPageNumber + 1)
    }

    fun cancelRequest() {
        recipeClient.cancelRequest()
    }

    companion object {
        val instance: RecipeRepository by lazy {
            RecipeRepository()
        }
    }
}
