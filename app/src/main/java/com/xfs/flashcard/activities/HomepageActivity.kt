package com.xfs.flashcard.activities

import android.os.Bundle
import android.view.View
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.xfs.flashcard.R
import com.xfs.flashcard.adapter.SubjectAdapter
import com.xfs.flashcard.models.Subject
import java.util.*

class HomepageActivity : AppCompatActivity() {
    var toolbar: Toolbar? = null
    var navigationView: NavigationView? = null
    var drawerLayout: DrawerLayout? = null
    var subjectArrayList: ArrayList<Subject>? = null
    var adapterSubject: SubjectAdapter? = null
    private var listView: ListView? = null
    private  var lvAbout:android.widget.ListView? = null
    var v : View? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        //val intent = intent
        var subj = Subject(
            1,
            "fruit",
            1
        )
        subjectArrayList?.add(subj)
        subjectArrayList?.add(subj)
        subjectArrayList?.add(subj)
        subjectArrayList?.add(subj)
        subjectArrayList?.add(subj)
        v = layoutInflater.inflate(R.layout.activity_flashcard, null)
        toolbar = findViewById(R.id.toolbar)
        lvAbout = findViewById<ListView>(R.id.lvAbout)
        listView = findViewById<ListView>(R.id.lvHomepage)
        navigationView = findViewById(R.id.navigationview)
        drawerLayout = findViewById(R.id.drawerlayout)
        adapterSubject = SubjectAdapter(this, R.layout.array_subj, subjectArrayList)
        actionBar()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)
    }

    private fun actionBar() {
        //hàm hỗ trợ toorbar
        if (toolbar != null)
            setSupportActionBar(toolbar);
        //set nút cho actionbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //tạo icon cho toorbar
        toolbar?.setNavigationIcon(R.drawable.ic_menu)
        //tạo sự kiện nhấn nút
        toolbar?.setNavigationOnClickListener { drawerLayout!!.openDrawer(GravityCompat.START) }
    }
}
