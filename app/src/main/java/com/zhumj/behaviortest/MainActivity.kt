package com.zhumj.behaviortest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val dataList = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (i in 0 until 20) {
            dataList.add(dataList.size + 1)
        }

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = MAdapter()
    }

    inner class MAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun getItemCount(): Int = dataList.size

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder =
                HolderItem(LayoutInflater.from(this@MainActivity).inflate(android.R.layout.simple_list_item_1, parent, false))

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
            if (holder is HolderItem) {
                holder.textView.gravity = Gravity.CENTER
                holder.textView.text = dataList[position].toString()
            }
        }

        inner class HolderItem(itemView: View): RecyclerView.ViewHolder(itemView) {
            var textView = itemView.findViewById<TextView>(android.R.id.text1)
        }
    }
}
