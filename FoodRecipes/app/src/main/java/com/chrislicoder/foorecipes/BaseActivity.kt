package com.chrislicoder.foorecipes

import android.view.View
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_base.*

open class BaseActivity : AppCompatActivity() {


    override fun setContentView(@LayoutRes layoutResID: Int) {
        val constraintLayout = layoutInflater.inflate(R.layout.activity_base, null)
        val frameLayout = constraintLayout.findViewById<FrameLayout>(R.id.activity_content)

        layoutInflater.inflate(layoutResID, frameLayout, true)

        super.setContentView(layoutResID)
    }

    fun showProgressBar(visibility: Boolean) {
        progress_bar.visibility = if (visibility) View.VISIBLE else View.INVISIBLE
    }
}