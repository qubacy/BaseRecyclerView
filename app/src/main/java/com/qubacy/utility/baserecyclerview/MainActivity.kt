package com.qubacy.utility.baserecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.qubacy.utility.baserecyclerview.component.list.adapter.CustomRecyclerViewAdapter
import com.qubacy.utility.baserecyclerview.component.list.adapter.producer.CustomRecyclerViewItemViewProviderProducer
import com.qubacy.utility.baserecyclerview.component.list.item.data.CustomRecyclerViewItemData
import com.qubacy.utility.baserecyclerview.databinding.ActivityMainBinding
import com.qubacy.utility.baserecyclerview.item.animator.BaseRecyclerViewItemAnimator
import com.qubacy.utility.baserecyclerview.view.BaseRecyclerViewCallback

class MainActivity : AppCompatActivity(), BaseRecyclerViewCallback {
    companion object {
        val ITEM = CustomRecyclerViewItemData("test")

        val ITEMS = listOf(ITEM, ITEM, ITEM)
    }

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mAdapter: CustomRecyclerViewAdapter

    private var mLastRecyclerViewIsEnabled: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(mBinding.root)

        initList()
        initUiControls()
    }

    private fun initList() {
        mAdapter = CustomRecyclerViewAdapter(CustomRecyclerViewItemViewProviderProducer())

        mBinding.list.apply {
            adapter = mAdapter
            itemAnimator = BaseRecyclerViewItemAnimator()

            setCallback(this@MainActivity)
        }
    }

    private fun initUiControls() {
        mBinding.buttonAdd.setOnClickListener {
            mAdapter.addItem(ITEM)
        }
        mBinding.buttonRemoveLast.setOnClickListener {
            mAdapter.removeLastItem()
            mLastRecyclerViewIsEnabled = !mLastRecyclerViewIsEnabled

            mBinding.list.setIsEnabled(mLastRecyclerViewIsEnabled)
        }
        mBinding.buttonMoveLast.setOnClickListener {
            mAdapter.moveLastToTop()
        }
        mBinding.buttonUpdateLast.setOnClickListener {
            mAdapter.updateItemAtPosition(
                mAdapter.itemCount - 1, ITEM.copy("updated item"))
        }
    }

    override fun onStart() {
        super.onStart()

        mAdapter.setItems(ITEMS)
    }

    override fun onEndReached() {
        Log.d("TEST", "onEndReached(): entering..")
    }
}