package com.xfs.flashcard.adapter

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import com.squareup.picasso.Picasso
import com.xfs.flashcard.R
import com.xfs.flashcard.activities.AboutActivity
import com.xfs.flashcard.activities.Flashcard
import com.xfs.flashcard.models.Subject
import java.util.*


class SubjectAdapter(a: ArrayList<Subject>) : BaseAdapter() {
    private val subjects: ArrayList<Subject> = a

    override fun getCount(): Int {
        return subjects.size
    }

    override fun getItem(position: Int): Subject {
        return subjects[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View = convertView ?: View.inflate(parent?.context, R.layout.subjects, null)

        val subject = getItem(position)
        view.findViewById<TextView>(R.id.nameSubj).text = subject.name
        Picasso.get().load(subject.image).placeholder(R.drawable.icon_img).into(view.findViewById<ImageView>(R.id.imgSubj))
        return view
    }
}