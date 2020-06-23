package com.chrislicoder.foodrecipes.adapters.viewholders

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.chrislicoder.foodrecipes.R
import com.chrislicoder.foodrecipes.adapters.OnRecipeListener

class RecipeViewHolder(itemView: View, private val onRecipeListener: OnRecipeListener) :
    RecyclerView.ViewHolder(itemView), View.OnClickListener {
    var title: TextView = itemView.findViewById(R.id.recipe_title)
    var publisher: TextView = itemView.findViewById(R.id.recipe_publisher)
    var socialScore: TextView = itemView.findViewById(R.id.recipe_social_score)
    var image: AppCompatImageView = itemView.findViewById(R.id.recipe_image)

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        onRecipeListener.onRecipeClick(adapterPosition)
    }
}
