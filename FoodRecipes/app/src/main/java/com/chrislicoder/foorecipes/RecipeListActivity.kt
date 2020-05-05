package com.chrislicoder.foorecipes

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.activity_recipe_list.*

class RecipeListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)

        test.setOnClickListener {
            showProgressBar(progress_bar.visibility != View.VISIBLE)
        }
    }
}
