package com.xfs.flashcard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.xfs.flashcard.R
import com.xfs.flashcard.models.Menu
import com.xfs.flashcard.models.Subject

class MenuAdapter(list: ArrayList<Menu>) : BaseAdapter() {
    private val ls: ArrayList<Menu> = list
    override fun getCount(): Int {
        return ls.size
    }

    override fun getItem(position: Int): Menu {
        return ls[position]
    }

    override fun getItemId(position: Int): Long {
        return ls[position].hashCode().toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: View.inflate(parent?.context, R.layout.array_menu, null)

        val menu = getItem(position)
        view.findViewById<TextView>(R.id.name).text = menu.nameMenu
        view.findViewById<ImageView>(R.id.img).setImageResource(menu.icon)
        return view

    }

//    private var context: Context? = null
//    private var layout = 0
//    private var list: ArrayList<Menu>? = null
//
//    constructor(context: Context?, layout: Int, list: ArrayList<Menu>?) {
//        this.context = context
//        this.layout = layout
//        this.list = list
//    }
//
//    private class ViewHolder {
//        var name: TextView? = null
//        var img: ImageView? = null
//    }
//
//    override fun getCount(): Int {
//        return list!!.size
//    }
//
//    override fun getItem(position: Int): String {
//        return list!![position].toString()
//    }
//
//    override fun getItemId(position: Int): Long {
//        return position.toLong()
//    }
//
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
//        var convertView = convertView
//        var viewHolder : ViewHolder
//        if (convertView == null) {
//            val inflater =
//                context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//            convertView = inflater.inflate(layout, null)
//            viewHolder = ViewHolder()
//            viewHolder.name = convertView.findViewById<View>(R.id.name) as TextView
//            viewHolder.img = convertView.findViewById<View>(R.id.img) as ImageView
//            convertView.tag = viewHolder
//
//        } else {
//            viewHolder =
//                convertView.tag as ViewHolder
//        }
//        viewHolder.name?.setText(list!![position].nameMenu)
//        viewHolder.img?.setImageResource(list!![position].icon)
//        return convertView
//    }

}

