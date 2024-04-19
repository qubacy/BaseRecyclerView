package com.qubacy.utility.baserecyclerview.component.list.adapter

import androidx.annotation.UiThread
import com.qubacy.utility.baserecyclerview.adapter.BaseRecyclerViewAdapter
import com.qubacy.utility.baserecyclerview.component.list.adapter.producer.CustomRecyclerViewItemViewProviderProducer
import com.qubacy.utility.baserecyclerview.component.list.item.CustomRecyclerViewItemViewProvider
import com.qubacy.utility.baserecyclerview.component.list.item.data.CustomRecyclerViewItemData

class CustomRecyclerViewAdapter(
    itemViewProviderProducer: CustomRecyclerViewItemViewProviderProducer
) : BaseRecyclerViewAdapter<
    CustomRecyclerViewItemData,
    CustomRecyclerViewItemViewProvider,
    CustomRecyclerViewItemViewProviderProducer,
    CustomRecyclerViewAdapter.ViewHolder
>(
    itemViewProviderProducer
) {
    class ViewHolder(
        itemViewProvider: CustomRecyclerViewItemViewProvider
    ) : BaseRecyclerViewAdapter.ViewHolder<
        CustomRecyclerViewItemData, CustomRecyclerViewItemViewProvider
    >(itemViewProvider) {

    }

    override fun createViewHolder(itemView: CustomRecyclerViewItemViewProvider): ViewHolder {
        return ViewHolder(itemView)
    }

    @UiThread
    fun setItems(items: List<CustomRecyclerViewItemData>) {
        resetItems()
        mItems.addAll(items)

        wrappedNotifyItemRangeInserted(0, items.size)
    }

    @UiThread
    fun addItem(item: CustomRecyclerViewItemData) {
        mItems.add(item)

        wrappedNotifyItemInserted(mItems.size - 1)
    }

    @UiThread
    fun removeLastItem() {
        if (mItems.isEmpty()) return

        mItems.removeLast()

        wrappedNotifyItemRemoved(mItems.size)
    }

    @UiThread
    fun moveLastToTop() {
        val itemCount = mItems.size

        if (itemCount < 2) return

        val lastItem = mItems.removeLast()

        mItems.add(0, lastItem)

        wrappedNotifyItemMoved(itemCount - 1, 0)
    }

    @UiThread
    fun updateItemAtPosition(position: Int, updatedItem: CustomRecyclerViewItemData) {
        if (mItems.size <= position) return

        mItems[position] = updatedItem

        wrappedNotifyItemChanged(position)
    }
}