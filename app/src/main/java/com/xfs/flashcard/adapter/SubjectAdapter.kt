package com.xfs.flashcard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.xfs.flashcard.R
import com.xfs.flashcard.models.Subject

class SubjectAdapter : BaseAdapter {

    private var context: Context? = null
    private var layout = 0
    private var list: ArrayList<Subject>? = null

    constructor(context: Context?, layout: Int, list: java.util.ArrayList<Subject>?) {
        this.context = context
        this.layout = layout
        this.list = list
    }
    override fun getCount(): Int {
        return list!!.size
    }

    override fun getItem(position: Int): Any {
        TODO("Not yet implemented")
    }

    override fun getItemId(position: Int): Long {
        TODO("Not yet implemented")
    }

    private class ViewHolder {
        var nameSubj: TextView? = null
        var imgSubj: ImageView? = null
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var convertView = convertView
        val viewHolder: com.xfs.flashcard.adapter.SubjectAdapter.ViewHolder
        if (convertView == null) {
            val inflater =
                context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(layout, null)
            viewHolder = com.xfs.flashcard.adapter.SubjectAdapter.ViewHolder()
            viewHolder.nameSubj = convertView.findViewById<View>(R.id.nameSubj) as TextView
            viewHolder.imgSubj = convertView.findViewById<View>(R.id.imgSubj) as ImageView
            convertView.tag = viewHolder

        } else {
            viewHolder =
                convertView.tag as com.xfs.flashcard.adapter.SubjectAdapter.ViewHolder
        }
        viewHolder.nameSubj?.setText(list!![position].name)
        viewHolder.imgSubj?.setImageResource(list!![position].image)
        return convertView
    }

}

