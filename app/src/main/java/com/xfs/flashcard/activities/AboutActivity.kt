package com.xfs.flashcard.activities

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.xfs.flashcard.R

class AboutActivity : AppCompatActivity() {
    var aboutUs: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        aboutUs = findViewById<View>(R.id.aboutUs) as TextView
        val about = """Ứng dụng được làm bởi nhóm Ưhat's up"""
        aboutUs!!.text = about
    }
}