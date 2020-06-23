package com.chrislicoder.foodrecipes.requests.responses

import com.chrislicoder.foodrecipes.models.Recipe
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RecipeResponse(
    @SerializedName("recipe") @Expose val recipe: Recipe
) {
    override fun toString(): String {
        return "RecipeResponse{recipe=$recipe}"
    }
}
