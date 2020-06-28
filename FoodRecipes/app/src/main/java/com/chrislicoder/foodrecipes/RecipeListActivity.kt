package com.chrislicoder.foodrecipes

import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chrislicoder.foodrecipes.adapters.OnRecipeListener
import com.chrislicoder.foodrecipes.adapters.RecipeRecyclerAdapter
import com.chrislicoder.foodrecipes.util.Testing
import com.chrislicoder.foodrecipes.util.ui.VerticalSpacingDecorator
import com.chrislicoder.foodrecipes.viewmodels.RecipeListViewModel

private const val TAG = "RecipeListActivity"

class RecipeListActivity : BaseActivity(), OnRecipeListener {
    private lateinit var mRecipeListViewModel: RecipeListViewModel
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: RecipeRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)
        mRecyclerView = findViewById(R.id.recipe_list)
        mRecipeListViewModel = ViewModelProvider(this).get(RecipeListViewModel::class.java)

        initRecyclerView()
        initSearchView()
        subscribeObservers()
        if (!mRecipeListViewModel.isViewingRecipes) {
            displaySearchCategories()
        }
    }

    private fun subscribeObservers() {
        mRecipeListViewModel.recipes
            .observe(
                this,
                Observer { recipeList ->
                    recipeList?.let {
                        if (mRecipeListViewModel.isViewingRecipes) {
                            Testing.printRecipes("network test", recipeList)
                            mAdapter.setRecipes(recipeList)
                        }
                    }
                }
            )
    }

    private fun initRecyclerView() {
        mAdapter = RecipeRecyclerAdapter(this)
        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.addItemDecoration(VerticalSpacingDecorator(30))
    }

    private fun initSearchView() {
        findViewById<SearchView>(R.id.search_view).apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {

                    mAdapter.displayLoading()
                    // Search the database for a recipe
                    query?.let { mRecipeListViewModel.searchRecipes(it, 1) }
                    return false
                }

                override fun onQueryTextChange(query: String?): Boolean {

                    // Wait for the user to submit the search. So do nothing here.
                    return false
                }
            })
        }
    }

    override fun onRecipeClick(position: Int) {
    }

    override fun onCategoryClick(category: String) {
        mAdapter.displayLoading()
        mRecipeListViewModel.searchRecipes(category, 1)
    }

    private fun displaySearchCategories() {
        mRecipeListViewModel.isViewingRecipes = false
        mAdapter.displaySearchCategories()
    }

    override fun onBackPressed() {
        if (mRecipeListViewModel.onBackPressed()) {
            super.onBackPressed()
        } else {
            displaySearchCategories()
        }
    }
}
