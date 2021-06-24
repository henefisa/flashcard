package com.xfs.flashcard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

abstract class AboutAdapter : BaseAdapter() {

    private var context: Context? = null
    private var layout = 0

    fun AboutAdapter(context: Context?, layout: Int) {
        this.context = context
        this.layout = layout
    }

    fun getView(convertView: View?, parent: ViewGroup?): View? {
        val inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var convertView = convertView
        convertView = inflater.inflate(layout, null)
        return convertView
    }

}