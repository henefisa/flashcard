package com.xfs.flashcard.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.xfs.flashcard.R

class WordExamplesAdapter(e: ArrayList<String>) : BaseAdapter() {
    private val words: ArrayList<String> = e

    override fun getCount(): Int {
        return words.size
    }

    override fun getItem(position: Int): String {
        return words[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View = convertView ?: View.inflate(parent?.context, R.layout.word_example, null)

        val example = getItem(position)
        view.findViewById<TextView>(R.id.word_example).text = example

        return view

    }
}