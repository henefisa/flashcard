package com.xfs.flashcard.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.xfs.flashcard.R
import com.xfs.flashcard.models.Subject
import java.util.*


class SubjectAdapter(a: ArrayList<Subject>) : BaseAdapter() {

//    private var context: Context? = null
//    var listSubj: ArrayList<Subject>
//
//    constructor(context: Context?, listSubj: ArrayList<Subject>) {
//        this.context = context
//        this.listSubj = listSubj
//    }
//    override fun getCount(): Int {
//        return listSubj.size
//    }
//
//    override fun getItem(position: Int): Any {
//        return listSubj.get(position)
//    }
//
//    override fun getItemId(position: Int): Long {
//        return position.toLong()
//    }
//
//    fun filterList(filteredList: ArrayList<Subject>) {
//        listSubj = filteredList
//        notifyDataSetChanged()
//    }
//
//    class ViewHolder {
//        var nameSubj: TextView? = null
//        var imgSubj: ImageView? = null
//    }
//
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//        var viewHolder: ViewHolder
//
//        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        convertView = inflater.inflate(R.layout.subjects, null)
//        viewHolder.nameSubj = convertView.findViewById(R.id.nameSubj)
//        viewHolder.imgSubj = convertView.findViewById(R.id.imgSubj)
//        convertView.tag = viewHolder
//        val subject: Subject = getItem(position) as Subject
//        viewHolder.nameSubj?.text=subject.name
//
//        return convertView
//    }

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
//        view.findViewById<ImageView>(R.id.img).setImageResource(subject.image)
        return view
    }
}