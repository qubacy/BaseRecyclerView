package com.qubacy.utility.baserecyclerview.view

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.qubacy.utility.baserecyclerview.adapter.BaseRecyclerViewAdapter

class BaseRecyclerView(
    context: Context,
    attrs: AttributeSet
) : RecyclerView(context, attrs) {
    companion object {
        const val TAG = "BaseRecyclerView"
    }

    private var mCallback: BaseRecyclerViewCallback? = null

    private var mIsEndReached: Boolean = false

    fun setCallback(callback: BaseRecyclerViewCallback) {
        mCallback = callback
    }

    fun isAtStart(): Boolean {
        val layoutManager = layoutManager

        if (layoutManager == null) return false

        return when (layoutManager::class) {
            LinearLayoutManager::class ->
                checkLinearLayoutManagerIsAtStart(layoutManager as LinearLayoutManager)
            else -> false
        }
    }

    private fun checkLinearLayoutManagerIsAtStart(layoutManager: LinearLayoutManager): Boolean {
        val firstVisibleItemPosition = layoutManager.findFirstCompletelyVisibleItemPosition()

        return (firstVisibleItemPosition == 0)
    }

    override fun onScrolled(dx: Int, dy: Int) {
        super.onScrolled(dx, dy)

        checkIsEndReached()

    }

    @CallSuper
    protected open fun checkIsEndReached(): Boolean {
        val layoutManager = layoutManager
        val adapter = adapter

        if (mCallback == null || layoutManager == null
        || adapter == null || adapter !is BaseRecyclerViewAdapter<*, *, *, *>
        ) {
            return true
        }

        when (layoutManager::class) {
            LinearLayoutManager::class ->
                checkLinearLayoutManagerForEndReach(layoutManager as LinearLayoutManager, adapter)
            else -> return false
        }

        return true
    }

    private fun checkLinearLayoutManagerForEndReach(
        linearLayoutManager: LinearLayoutManager,
        adapter: BaseRecyclerViewAdapter<*, *, *, *>
    ) {
        val lastVisiblePosition = linearLayoutManager.findLastVisibleItemPosition()
        val endPosition = adapter.getEndPosition()

        checkIsEndReachedByPositions(lastVisiblePosition, endPosition)
    }

    protected fun checkIsEndReachedByPositions(
        lastVisiblePosition: Int,
        endPosition: Int
    ) {
        if (lastVisiblePosition == endPosition) {
            if (mIsEndReached) return

            mIsEndReached = true

            mCallback!!.onEndReached()

        } else {
            if (mIsEndReached) mIsEndReached = false
        }
    }

    override fun isPaddingOffsetRequired(): Boolean {
        return true
    }

    override fun getTopPaddingOffset(): Int {
        return -paddingTop
    }

    override fun getBottomPaddingOffset(): Int {
        return paddingBottom
    }

    override fun getLeftPaddingOffset(): Int {
        return -paddingLeft
    }

    override fun getRightPaddingOffset(): Int {
        return paddingRight
    }
}