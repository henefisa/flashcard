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

}

