package com.qubacy.utility.baserecyclerview.component.list.adapter.producer

import android.view.ViewGroup
import com.qubacy.utility.baserecyclerview.adapter.producer.BaseRecyclerViewItemViewProviderProducer
import com.qubacy.utility.baserecyclerview.component.list.item.CustomRecyclerViewItemViewProvider
import com.qubacy.utility.baserecyclerview.component.list.item.data.CustomRecyclerViewItemData

class CustomRecyclerViewItemViewProviderProducer(

) : BaseRecyclerViewItemViewProviderProducer<
    CustomRecyclerViewItemData, CustomRecyclerViewItemViewProvider
>(){
    override fun createItemViewProvider(
        parent: ViewGroup,
        viewType: Int
    ): CustomRecyclerViewItemViewProvider {
        return CustomRecyclerViewItemViewProvider(parent)
    }
}