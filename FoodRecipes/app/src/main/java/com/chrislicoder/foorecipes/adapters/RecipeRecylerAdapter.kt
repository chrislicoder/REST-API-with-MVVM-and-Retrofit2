package com.chrislicoder.foorecipes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chrislicoder.foorecipes.R
import com.chrislicoder.foorecipes.models.Recipe

class RecipeRecylerAdapter(
    private var mRecipes: List<Recipe>,
    private val mOnRecipeListener: OnRecipeListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(viewGroup.getContext())
            .inflate(R.layout.layout_recipe_list_item, viewGroup, false)
        return RecipeViewHolder(view, mOnRecipeListener)
    }

    override fun getItemCount(): Int {
        return mRecipes.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RecipeViewHolder) {
            holder.title.text = mRecipes[position].title
            holder.publisher.text = mRecipes[position].publisher
            holder.socialScore.text = this.mRecipes[position].social_rank.toString()
        }
    }

    fun setRecipes(mRecipes: List<Recipe>) {
        this.mRecipes = mRecipes
    }
}