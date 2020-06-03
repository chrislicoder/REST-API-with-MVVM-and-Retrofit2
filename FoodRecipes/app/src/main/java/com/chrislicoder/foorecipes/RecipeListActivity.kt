package com.chrislicoder.foorecipes

import android.os.Bundle
import android.util.Log
import com.chrislicoder.foorecipes.requests.ServiceGenerator
import com.chrislicoder.foorecipes.requests.responses.RecipeResponse
import com.chrislicoder.foorecipes.util.Constants
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.activity_recipe_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class RecipeListActivity : BaseActivity() {
    companion object {
        const val TAG = "TESTING RETROFIT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)

        test.setOnClickListener {
            testRetrofit()
        }
    }

    private fun testRetrofit() {
        // do get using retrofit
        val responseCall: Call<RecipeResponse> = ServiceGenerator.recipeApi
            .getRecipe(
                Constants.API_KEY,
                "35382"
            )

        responseCall.enqueue(object : Callback<RecipeResponse> {
            override fun onResponse(
                call: Call<RecipeResponse>,
                response: Response<RecipeResponse>
            ) {
                Log.d(
                    TAG,
                    "onResponse: Server Response: $response"
                )
                if (response.code() == 200) {
                    Log.d(TAG, "onResponse: " + response.body()?.recipe?.toString())
                } else {
                    try {
                        Log.d(
                            TAG,
                            "onResponse: " + response.errorBody()?.string()
                        )
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(
                call: Call<RecipeResponse>,
                t: Throwable
            ) {
                Log.d(TAG, "onResponse: ERROR: " + t.message)
            }
        })
    }
}
