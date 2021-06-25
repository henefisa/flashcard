package com.xfs.flashcard.activities

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.xfs.flashcard.R
import com.xfs.flashcard.adapter.MenuAdapter
import com.xfs.flashcard.adapter.SubjectAdapter
import com.xfs.flashcard.models.Menu
import com.xfs.flashcard.models.Subject
import java.util.*

class HomepageActivity : AppCompatActivity() {
    var toolbar: Toolbar? = null
    var navigationView: NavigationView? = null
    var drawerLayout: DrawerLayout? = null

    lateinit var listSubj: ListView
    lateinit var lvHomepage: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)
        var subj = Subject(
            1,
            "fruit",
            "https://dictionary.cambridge.org/vi/images/thumb/fruit_noun_002_15105.jpg?version=5.0.176"
        )

        val subjectArrayList = ArrayList<Subject>()
        subjectArrayList.add(subj)
        subjectArrayList.add(subj)
        subjectArrayList.add(subj)
        subjectArrayList.add(subj)
        subjectArrayList.add(subj)

        toolbar = findViewById(R.id.toolbar)
        listSubj = findViewById<ListView>(R.id.list)
        lvHomepage = findViewById<ListView>(R.id.lvHomepage)
//        lvAbout = findViewById<ListView>(R.id.lvAbout)
        navigationView = findViewById(R.id.navigationview)
        drawerLayout = findViewById(R.id.drawerlayout)
        val MenuArrayList = ArrayList<Menu>()
        MenuArrayList.add(Menu("Learn", R.drawable.ic_round_work_24))
        MenuArrayList.add(Menu("About us", R.drawable.ic_round_work_24))

        val adapterSubj = SubjectAdapter(subjectArrayList)
        listSubj.adapter = adapterSubj
        val adapterMenu = MenuAdapter(MenuArrayList)
        lvHomepage.adapter = adapterMenu

        lvHomepage.onItemClickListener =  OnItemClickListener() { _, _, position, _ ->
            val menu = adapterMenu.getItem(position)
            if(position === 0){
                val intent = Intent(this, HomepageActivity::class.java)
                startActivity(intent)
            }
            else if(position === 1){
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
            }
        }

        actionBar()
    }


    private fun actionBar() {
        if (toolbar != null)
            setSupportActionBar(toolbar);
        //set nút cho actionbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //tạo icon toolbar
        toolbar?.setNavigationIcon(R.drawable.ic_menu)
        toolbar?.setNavigationOnClickListener { drawerLayout!!.openDrawer(GravityCompat.START) }
    }
}
