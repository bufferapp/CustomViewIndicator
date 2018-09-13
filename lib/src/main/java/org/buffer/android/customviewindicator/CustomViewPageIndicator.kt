package org.buffer.android.customviewindicator

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout

open class CustomViewPageIndicator @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0)
    : LinearLayout(context, attrs, defStyle) {

    init {
        orientation = HORIZONTAL
        handleAttributes(context, attrs)
    }

    var indicatorViews: List<View>? = null
        set(value) {
            field = value
            setupPageIndicator()
        }
    private var pager: ViewPager? = null
    private var selectedItem = 0
    private var itemRightMargin = 0
    private var itemleftMargin = 0

    fun attachToViewPager(viewPager: ViewPager) {
        if (pager != null) throw IllegalStateException("A ViewPager has already been attached")
        pager = viewPager
        viewPager.addOnPageChangeListener(pageChangeListener)
    }

    fun detachFromViewPager() {
        pager?.removeOnPageChangeListener(pageChangeListener)
    }

    private fun handleAttributes(context: Context, attrs: AttributeSet?) {
        attrs?.let {
            val styleAttributes = context.obtainStyledAttributes(it,
                    R.styleable.CustomViewPageIndicator, 0, 0)
            try {
                selectedItem = styleAttributes
                        .getInteger(R.styleable.CustomViewPageIndicator_defaultItem, 0)
                itemRightMargin = styleAttributes.getDimensionPixelSize(
                        R.styleable.CustomViewPageIndicator_itemRightMargin, 0)
                itemleftMargin = styleAttributes.getDimensionPixelSize(
                        R.styleable.CustomViewPageIndicator_itemleftMargin, 0)
            } finally {
                styleAttributes.recycle()
            }
        }
    }

    private fun setupPageIndicator() {
        removeAllViews()
        val params = LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        params.rightMargin = itemRightMargin
        params.leftMargin = itemleftMargin
        indicatorViews?.forEach {
            it.layoutParams = params
            addView(it)
        }
        assignAlphaToChildViews()
    }

    private fun assignAlphaToChildViews() {
        repeat(childCount) {
            getChildAt(it).alpha = if (it == selectedItem) 1f else 0.4f
        }
    }

    private val pageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {}

        override fun onPageScrolled(position: Int,
                                    positionOffset: Float,
                                    positionOffsetPixels: Int) {
        }

        override fun onPageSelected(position: Int) {
            selectedItem = position
            assignAlphaToChildViews()
        }
    }
}