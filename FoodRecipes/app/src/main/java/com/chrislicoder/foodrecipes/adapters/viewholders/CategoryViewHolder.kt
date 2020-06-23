package com.chrislicoder.foodrecipes.adapters.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chrislicoder.foodrecipes.R
import com.chrislicoder.foodrecipes.adapters.OnRecipeListener
import de.hdodenhof.circleimageview.CircleImageView

class CategoryViewHolder(itemView: View, private val mOnRecipeListener: OnRecipeListener) :
    RecyclerView.ViewHolder(itemView), View.OnClickListener {

    var categoryImage: CircleImageView = itemView.findViewById(R.id.category_image)
    var categoryTitle: TextView = itemView.findViewById(R.id.category_title)

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        mOnRecipeListener.onCategoryClick(categoryTitle.text.toString())
    }
}
