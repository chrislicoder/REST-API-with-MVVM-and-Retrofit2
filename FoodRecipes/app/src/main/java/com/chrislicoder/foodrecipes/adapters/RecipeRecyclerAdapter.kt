package com.chrislicoder.foodrecipes.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chrislicoder.foodrecipes.R
import com.chrislicoder.foodrecipes.adapters.viewholders.CategoryViewHolder
import com.chrislicoder.foodrecipes.adapters.viewholders.LoadingViewHolder
import com.chrislicoder.foodrecipes.adapters.viewholders.RecipeViewHolder
import com.chrislicoder.foodrecipes.adapters.viewholders.SearchExhaustedViewHolder
import com.chrislicoder.foodrecipes.models.Recipe
import com.chrislicoder.foodrecipes.util.Constants.DEFAULT_SEARCH_CATEGORIES
import com.chrislicoder.foodrecipes.util.Constants.DEFAULT_SEARCH_CATEGORY_IMAGES

private const val RECIPE_TYPE = 1
private const val LOADING_TYPE = 2
private const val CATEGORY_TYPE = 3
private const val EXHAUSTED_TYPE = 4
private const val INVALID_SOCIAL_RANK = -1F
private const val LOADING_TITLE = "LOADING..."
private const val EXHAUSTED_TITLE = "EXHAUSTED..."

class RecipeRecyclerAdapter(
    private val mOnRecipeListener: OnRecipeListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var mRecipes: List<Recipe>

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        when (viewType) {
            RECIPE_TYPE -> {
                view = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.layout_recipe_list_item, viewGroup, false)
                return RecipeViewHolder(
                    view,
                    mOnRecipeListener
                )
            }

            LOADING_TYPE -> {
                view = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.layout_loading_list_item, viewGroup, false)
                return LoadingViewHolder(
                    view
                )
            }

            CATEGORY_TYPE -> {
                view = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.layout_category_list_item, viewGroup, false)
                return CategoryViewHolder(
                    view,
                    mOnRecipeListener
                )
            }

            EXHAUSTED_TYPE -> {
                view = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.layout_search_exhausted_list_item, viewGroup, false)
                return SearchExhaustedViewHolder(
                    view
                )
            }

            else -> {
                view = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.layout_recipe_list_item, viewGroup, false)
                return RecipeViewHolder(
                    view,
                    mOnRecipeListener
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return if (!this::mRecipes.isInitialized) 0 else mRecipes.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemViewType = getItemViewType(position)
        if (itemViewType == RECIPE_TYPE && holder is RecipeViewHolder) {
            holder.title.text = mRecipes[position].title
            holder.publisher.text = mRecipes[position].publisher
            holder.socialScore.text = this.mRecipes[position].social_rank.toString()

            // set the image
            val options: RequestOptions =
                RequestOptions().placeholder(R.drawable.ic_launcher_background)

            Glide.with(holder.itemView.context)
                .setDefaultRequestOptions(options)
                .load(mRecipes[position].image_url)
                .into(holder.image)
        } else if (itemViewType == CATEGORY_TYPE && holder is CategoryViewHolder) {
            val options: RequestOptions =
                RequestOptions().placeholder(R.drawable.ic_launcher_background)
            val path: Uri =
                Uri.parse("android.resource://com.chrislicoder.foodrecipes/drawable/${mRecipes[position].image_url}")
            Glide.with(holder.itemView.context)
                .setDefaultRequestOptions(options)
                .load(path)
                .into(
                    holder.categoryImage
                )

            holder.categoryTitle.text = mRecipes[position].title
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (mRecipes[position].social_rank == INVALID_SOCIAL_RANK) {
            CATEGORY_TYPE
        } else if (mRecipes[position].title.equals(LOADING_TITLE)) {
            LOADING_TYPE
        } else if (mRecipes[position].title.equals(EXHAUSTED_TITLE)) {
            EXHAUSTED_TYPE
        } else if (position == mRecipes.size - 1 &&
            position != 0 &&
            !mRecipes[position].title.equals(EXHAUSTED_TITLE)
        ) {
            LOADING_TYPE
        } else {
            RECIPE_TYPE
        }
    }

    fun setQueryExhausted() {
        hideLoading()
        Recipe().apply {
            title = EXHAUSTED_TITLE
        }.also {
            val list = mRecipes.toMutableList()
            list.add(it)
            mRecipes = list.toList()
        }
    }

    fun displayLoading() {
        if (!isLoading()) {
            val recipe = Recipe(LOADING_TITLE)
            val loadingList: MutableList<Recipe> = ArrayList()
            loadingList.add(recipe)
            mRecipes = loadingList
            notifyDataSetChanged()
        }
    }

    fun displaySearchCategories() {
        val categories = mutableListOf<Recipe>()
        for ((index, cateboryString) in DEFAULT_SEARCH_CATEGORIES.withIndex()) {
            val mockRecipe = Recipe(
                title = cateboryString,
                image_url = DEFAULT_SEARCH_CATEGORY_IMAGES[index],
                social_rank = -1F
            )
            categories.add(mockRecipe)
        }
        mRecipes = categories
        notifyDataSetChanged()
    }

    private fun isLoading(): Boolean {
        if (this::mRecipes.isInitialized && mRecipes.isNotEmpty()) {
            if (mRecipes[mRecipes.size - 1].title.equals(LOADING_TITLE)) {
                return true
            }
        }
        return false
    }

    private fun hideLoading() {
        if(isLoading()) {
            val mutableList = mRecipes.toMutableList()
            for (recipe in mRecipes)
                if (recipe.title.equals(LOADING_TITLE)) {
                    mutableList.remove(recipe)
                }
            mRecipes = mutableList.toList()
        }
        notifyDataSetChanged()
    }

    fun setRecipes(mRecipes: List<Recipe>?) {
        mRecipes?.let {
            this.mRecipes = mRecipes
            notifyDataSetChanged()
        }
    }

    fun getSelectedRecipe(position: Int): Recipe? {
        return mRecipes.let {
            if (it.isNotEmpty()) mRecipes[position] else null
        }
    }
}
