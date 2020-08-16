package com.chrislicoder.foodrecipes.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.chrislicoder.foodrecipes.models.Recipe
import com.chrislicoder.foodrecipes.requests.RecipeApiClient


class RecipeRepository private constructor() {
    private val recipeClient by lazy { RecipeApiClient.instance }
    lateinit var mQuery: String
    var mPageNumber: Int = 0
    private val mIsQueryExhausted: MutableLiveData<Boolean> = MutableLiveData()
    private val mRecipes: MediatorLiveData<List<Recipe>> = MediatorLiveData()

    init {
        initMediators()
    }

    val recipes: LiveData<List<Recipe>?>
        get() = mRecipes

    val recipe: LiveData<Recipe>
        get() = recipeClient.recipe

    val isRecipeRequestTimeOut: LiveData<Boolean>
        get() = recipeClient.timeOut

    val isQueryExhausted: LiveData<Boolean>
        get() = mIsQueryExhausted

    fun searchRecipeById(recipeID: String) {
        recipeClient.searchRecipeByID(recipeID)
    }

    fun searchRecipesApi(query: String, pageNumber: Int) {
        var number = pageNumber
        if (number == 0) {
            number = 1
        }
        mQuery = query
        mPageNumber = pageNumber
        mIsQueryExhausted.value = false
        recipeClient.searchRecipesApi(query, number)
    }

    fun searchNewPage() {
        searchRecipesApi(mQuery, mPageNumber + 1)
    }

    fun cancelRequest() {
        recipeClient.cancelRequest()
    }

    private fun initMediators() {
        val recipeListApiSource = recipeClient.recipes
        mRecipes.addSource(recipeListApiSource) { recipes ->
            recipes?.let {
                mRecipes.value = it
                doneQuery(it)
            } ?: run {
                // search database cache
                doneQuery(null)
            }
        }
    }

    private fun doneQuery(list: List<Recipe>?) {
        mIsQueryExhausted.value = list == null || list.size < 30
    }

    companion object {
        val instance: RecipeRepository by lazy {
            RecipeRepository()
        }
    }
}
