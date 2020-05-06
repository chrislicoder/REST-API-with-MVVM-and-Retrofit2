package com.chrislicoder.foorecipes.requests

import com.chrislicoder.foorecipes.util.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceGenerator {
    private val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
    private val retrofit = retrofitBuilder.build()

    val recipeApi: RecipeApi =
        retrofit.create<RecipeApi>(RecipeApi::class.java)

}
