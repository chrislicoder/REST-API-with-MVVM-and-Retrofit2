package com.chrislicoder.foodrecipes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chrislicoder.foodrecipes.R
import com.chrislicoder.foodrecipes.adapters.viewholders.LoadingViewHolder
import com.chrislicoder.foodrecipes.adapters.viewholders.RecipeViewHolder
import com.chrislicoder.foodrecipes.models.Recipe

private const val RECIPE_TYPE = 1
private const val LOADING_TYPE = 2
private const val LOADING_TITLE = "LOADING..."

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
        if (itemViewType == RECIPE_TYPE) {
            if (holder is RecipeViewHolder) {
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
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (mRecipes[position].title.equals(LOADING_TITLE)) {
            LOADING_TYPE
        } else {
            RECIPE_TYPE
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

    private fun isLoading(): Boolean {
        if (this::mRecipes.isInitialized && mRecipes.isNotEmpty()) {
            if (mRecipes[mRecipes.size - 1].title.equals(LOADING_TITLE)) {
                return true
            }
        }
        return false
    }

    fun setRecipes(mRecipes: List<Recipe>?) {
        mRecipes?.let {
            this.mRecipes = mRecipes
            notifyDataSetChanged()
        }
    }
}
