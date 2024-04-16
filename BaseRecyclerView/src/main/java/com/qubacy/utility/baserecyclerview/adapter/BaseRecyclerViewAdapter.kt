package com.qubacy.utility.baserecyclerview.adapter

import android.view.ViewGroup
import androidx.annotation.UiThread
import androidx.recyclerview.widget.RecyclerView
import com.qubacy.utility.baserecyclerview.adapter.producer.BaseRecyclerViewItemViewProviderProducer
import com.qubacy.utility.baserecyclerview.item.BaseRecyclerViewItemViewProvider
import com.qubacy.utility.baserecyclerview.item.data.BaseRecyclerViewItemData

/**
 * Note: In order to successfully compose Unit tests, it's recommended to use WRAPPED version
 * of notify..() methods.
 */
abstract class BaseRecyclerViewAdapter<
    RecyclerViewItemDataType : BaseRecyclerViewItemData,
    RecyclerViewItemViewProviderType : BaseRecyclerViewItemViewProvider<RecyclerViewItemDataType>,
    RecyclerViewItemViewProviderProducerType: BaseRecyclerViewItemViewProviderProducer<
        RecyclerViewItemDataType, RecyclerViewItemViewProviderType
    >,
    ViewHolderType: BaseRecyclerViewAdapter.ViewHolder<
        RecyclerViewItemDataType, RecyclerViewItemViewProviderType
    >
>(
    val itemViewProviderProducer: RecyclerViewItemViewProviderProducerType
) : RecyclerView.Adapter<ViewHolderType>() {
    open class ViewHolder<
        RecyclerViewItemDataType : BaseRecyclerViewItemData,
        RecyclerViewItemViewProviderType : BaseRecyclerViewItemViewProvider<RecyclerViewItemDataType>
    >(
        val baseItemViewProvider: RecyclerViewItemViewProviderType
    ) : RecyclerView.ViewHolder(baseItemViewProvider.getView()) {
        open fun setData(data: RecyclerViewItemDataType) {
            baseItemViewProvider.setData(data)
        }
    }

    companion object {
        const val TAG = "BaseRecyclerViewAdapter"
    }


    protected val mItems: MutableList<RecyclerViewItemDataType> = mutableListOf()
    val items: List<RecyclerViewItemDataType> get() = mItems

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderType {
        val itemView = itemViewProviderProducer.createItemViewProvider(parent, viewType)

        return createViewHolder(itemView)
    }

    abstract fun createViewHolder(itemView: RecyclerViewItemViewProviderType): ViewHolderType

    override fun onBindViewHolder(holder: ViewHolderType, position: Int) {
        val itemData = mItems[position]

        holder.setData(itemData)
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    /**
     * Used for detecting reaching the end of the list;
     */
    open fun getEndPosition(): Int {
        return mItems.size - 1
    }

    protected fun replaceItems(items: List<RecyclerViewItemDataType>) {
        mItems.apply {
            clear()
            addAll(items)
        }
    }

    @UiThread
    fun resetItems() {
        wrappedNotifyItemRangeRemoved(0, mItems.size)
        mItems.clear()
    }

    open fun wrappedNotifyDataSetChanged() {
        notifyDataSetChanged()
    }

    open fun wrappedNotifyItemInserted(position: Int) {
        notifyItemInserted(position)
    }

    open fun wrappedNotifyItemRemoved(position: Int) {
        notifyItemRemoved(position)
    }

    open fun wrappedNotifyItemChanged(position: Int) {
        notifyItemChanged(position)
    }

    open fun wrappedNotifyItemMoved(fromPosition: Int, toPosition: Int) {
        notifyItemMoved(fromPosition, toPosition)
    }

    open fun wrappedNotifyItemRangeChanged(positionStart: Int, itemCount: Int) {
        notifyItemRangeChanged(positionStart, itemCount)
    }

    open fun wrappedNotifyItemRangeRemoved(positionStart: Int, itemCount: Int) {
        notifyItemRangeRemoved(positionStart, itemCount)
    }

    open fun wrappedNotifyItemRangeInserted(positionStart: Int, itemCount: Int) {
        notifyItemRangeInserted(positionStart, itemCount)
    }
}