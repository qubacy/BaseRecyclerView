package com.qubacy.utility.baserecyclerview.view.layoutmanager

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import com.qubacy.utility.baserecyclerview.view.layoutmanager._common.BaseRecyclerViewLayoutManager

open class LinearRecyclerViewLayoutManager(
    context: Context,
    attributeSet: AttributeSet,
    defStyleAttribute: Int,
    defStyleRes: Int
) : LinearLayoutManager(
    context, attributeSet, defStyleAttribute, defStyleRes
), BaseRecyclerViewLayoutManager {
    private var mIsScrollEnabled: Boolean = true

    override fun setScrollEnabled(isEnabled: Boolean) {
        mIsScrollEnabled = isEnabled
    }

    override fun canScrollVertically(): Boolean {
        return mIsScrollEnabled && super.canScrollVertically()
    }

    override fun canScrollHorizontally(): Boolean {
        return mIsScrollEnabled && super.canScrollHorizontally()
    }
}