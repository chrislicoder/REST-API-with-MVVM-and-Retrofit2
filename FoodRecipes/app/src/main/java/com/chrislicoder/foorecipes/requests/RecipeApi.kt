package com.chrislicoder.foorecipes.requests

import com.chrislicoder.foorecipes.requests.responses.RecipeResponse
import com.chrislicoder.foorecipes.requests.responses.RecipeSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApi {
    // SEARCH
    @GET("api/search")
    fun searchRecipe(
        @Query("key") key: String?,
        @Query("q") query: String?,
        @Query("page") page: String?
    ): Call<RecipeSearchResponse>

    // GET RECIPE REQUEST
    @GET("api/get")
    fun getRecipe(
        @Query("key") key: String?,
        @Query("rId") recipe_id: String?
    ): Call<RecipeResponse>
}