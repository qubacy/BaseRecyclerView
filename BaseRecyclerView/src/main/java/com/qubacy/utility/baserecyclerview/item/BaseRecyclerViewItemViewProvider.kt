package com.qubacy.utility.baserecyclerview.item

import com.qubacy.utility.baserecyclerview._common.view.provider.ViewProvider
import com.qubacy.utility.baserecyclerview.item.data.BaseRecyclerViewItemData

interface BaseRecyclerViewItemViewProvider<
    RecyclerViewItemDataType : BaseRecyclerViewItemData
> : ViewProvider {
    fun setData(data: RecyclerViewItemDataType)
}