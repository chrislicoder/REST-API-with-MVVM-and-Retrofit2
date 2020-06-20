package com.chrislicoder.foorecipes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chrislicoder.foorecipes.R
import com.chrislicoder.foorecipes.models.Recipe

class RecipeRecylerAdapter(
    private val mOnRecipeListener: OnRecipeListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var mRecipes: List<Recipe>

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(viewGroup.getContext())
            .inflate(R.layout.layout_recipe_list_item, viewGroup, false)
        return RecipeViewHolder(view, mOnRecipeListener)
    }

    override fun getItemCount(): Int {
        return if (!this::mRecipes.isInitialized) 0 else mRecipes.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
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

    fun setRecipes(mRecipes: List<Recipe>?) {
        mRecipes?.let {
            this.mRecipes = mRecipes
            notifyDataSetChanged()
        }
    }
}
