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
}