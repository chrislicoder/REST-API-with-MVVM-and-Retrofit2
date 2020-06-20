package com.chrislicoder.foorecipes

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chrislicoder.foorecipes.adapters.OnRecipeListener
import com.chrislicoder.foorecipes.adapters.RecipeRecylerAdapter
import com.chrislicoder.foorecipes.util.Testing
import com.chrislicoder.foorecipes.viewmodels.RecipeListViewModel

private const val TAG = "RecipeListActivity"

class RecipeListActivity : BaseActivity(), OnRecipeListener {
    private lateinit var mRecipeListViewModel: RecipeListViewModel
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: RecipeRecylerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)
        mRecyclerView = findViewById(R.id.recipe_list)
        mRecipeListViewModel = ViewModelProvider(this).get(RecipeListViewModel::class.java)

        initRecyclerView()
        subscribeObservers()
        testRetrofit()
    }

    private fun subscribeObservers() {
        mRecipeListViewModel.recipes
            .observe(
                this,
                Observer { recipeList ->
                    recipeList?.let {
                        Testing.printRecipes("network test", recipeList)
                        mAdapter.setRecipes(recipeList)
                    }
                }
            )

    }

    private fun initRecyclerView() {
        mAdapter = RecipeRecylerAdapter( this)
        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun testRetrofit() {
        mRecipeListViewModel.searchRecipes("chicken", 1)
    }

    override fun onRecipeClick(position: Int) {
    }

    override fun onCategoryClick(category: String) {
    }
}
