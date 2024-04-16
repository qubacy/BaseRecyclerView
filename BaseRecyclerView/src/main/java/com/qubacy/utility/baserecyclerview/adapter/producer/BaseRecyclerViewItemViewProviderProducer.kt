package com.qubacy.utility.baserecyclerview.adapter.producer

import android.view.ViewGroup
import com.qubacy.utility.baserecyclerview.item.BaseRecyclerViewItemViewProvider
import com.qubacy.utility.baserecyclerview.item.data.BaseRecyclerViewItemData

abstract class BaseRecyclerViewItemViewProviderProducer<
    RecyclerViewItemDataType : BaseRecyclerViewItemData,
    RecyclerViewItemViewProviderType : BaseRecyclerViewItemViewProvider<RecyclerViewItemDataType>
>() {
    abstract fun createItemViewProvider(
        parent: ViewGroup, viewType: Int
    ): RecyclerViewItemViewProviderType
}