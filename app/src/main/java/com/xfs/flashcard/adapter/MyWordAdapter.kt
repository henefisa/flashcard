package com.xfs.flashcard.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.xfs.flashcard.R
import com.xfs.flashcard.models.Word

class MyWordAdapter(w: ArrayList<Word>) : BaseAdapter() {
    private val words: ArrayList<Word> = w


    override fun getCount(): Int {
        return words.size
    }

    override fun getItem(position: Int): Word {
        return words[position]
    }

    override fun getItemId(position: Int): Long {
        return words[position].id.hashCode().toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View = convertView ?: View.inflate(parent?.context, R.layout.my_word_list_view_item, null)

        val word = getItem(position)
        view.findViewById<TextView>(R.id.word).text = word.value
        view.findViewById<TextView>(R.id.spell).text = word.spell
        view.findViewById<TextView>(R.id.mean).text = word.mean
        view.findViewById<TextView>(R.id.example).text = word.examples[0]

        return view

    }
}