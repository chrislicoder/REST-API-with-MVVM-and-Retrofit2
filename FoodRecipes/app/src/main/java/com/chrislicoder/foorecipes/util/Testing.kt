package com.chrislicoder.foorecipes.util

import android.util.Log
import com.chrislicoder.foorecipes.models.Recipe

object Testing {
    fun printRecipes(tag: String?, list: List<Recipe>) {
        for (recipe in list) {
            Log.d(
                tag,
                "printRecipes: ${recipe.recipe_id}, ${recipe.title}"
            )
        }
    }
}
