package com.qubacy.utility.baserecyclerview.component.list.item

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qubacy.utility.baserecyclerview.component.list.item.data.CustomRecyclerViewItemData
import com.qubacy.utility.baserecyclerview.databinding.ComponentListItemBinding
import com.qubacy.utility.baserecyclerview.item.BaseRecyclerViewItemViewProvider

class CustomRecyclerViewItemViewProvider(
    parent: ViewGroup
) : BaseRecyclerViewItemViewProvider<CustomRecyclerViewItemData> {
    private lateinit var mBinding: ComponentListItemBinding

    init {
        inflate(parent)
    }

    private fun inflate(parent: ViewGroup) {
        val layoutInflater = LayoutInflater.from(parent.context)

        mBinding = ComponentListItemBinding.inflate(layoutInflater, parent, false)
    }

    override fun setData(data: CustomRecyclerViewItemData) {
        mBinding.root.text = data.text
    }

    override fun getView(): View {
        return mBinding.root
    }
}