package com.chrislicoder.foorecipes.requests

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chrislicoder.foorecipes.models.Recipe

class RecipeApiClient private constructor() {
    private val mRecipes: MutableLiveData<List<Recipe>> = MutableLiveData()
    val recipes: LiveData<List<Recipe>>
        get() = mRecipes

    companion object {
        val instance: RecipeApiClient by lazy {
            RecipeApiClient()
        }
    }
}
