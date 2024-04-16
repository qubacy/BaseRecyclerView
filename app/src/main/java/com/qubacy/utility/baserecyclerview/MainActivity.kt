package com.qubacy.utility.baserecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.qubacy.utility.baserecyclerview.adapter.BaseRecyclerViewAdapter
import com.qubacy.utility.baserecyclerview.component.list.adapter.CustomRecyclerViewAdapter
import com.qubacy.utility.baserecyclerview.component.list.adapter.producer.CustomRecyclerViewItemViewProviderProducer
import com.qubacy.utility.baserecyclerview.component.list.item.data.CustomRecyclerViewItemData
import com.qubacy.utility.baserecyclerview.item.animator.BaseRecyclerViewItemAnimator
import com.qubacy.utility.baserecyclerview.view.BaseRecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var mAdapter: CustomRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<BaseRecyclerView>(R.id.list)

        initList(recyclerView)
    }

    private fun initList(recyclerView: BaseRecyclerView) {
        mAdapter = CustomRecyclerViewAdapter(CustomRecyclerViewItemViewProviderProducer())

        recyclerView.apply {
            adapter = mAdapter
            itemAnimator = BaseRecyclerViewItemAnimator()
        }
    }

    override fun onStart() {
        super.onStart()

        mAdapter.setItems(listOf(
            CustomRecyclerViewItemData("test 1"),
            CustomRecyclerViewItemData("test 2"),
            CustomRecyclerViewItemData("test 3")
        ))
    }
}