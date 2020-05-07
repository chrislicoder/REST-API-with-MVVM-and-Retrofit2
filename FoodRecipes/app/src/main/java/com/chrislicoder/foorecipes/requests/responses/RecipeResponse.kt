package com.chrislicoder.foorecipes.requests.responses

import com.chrislicoder.foorecipes.models.Recipe
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RecipeResponse(
    @SerializedName("recipe") @Expose val recipe: Recipe
) {
    override fun toString(): String {
        return "RecipeResponse{recipe=$recipe}"
    }
}