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
import com.google.firebase.firestore.FirebaseFirestore
import com.xfs.flashcard.R
import com.xfs.flashcard.adapter.MenuAdapter
import com.xfs.flashcard.adapter.SubjectAdapter
import com.xfs.flashcard.models.Menu
import com.xfs.flashcard.models.Subject
import java.util.*

class HomepageActivity : AppCompatActivity() {
    private lateinit var database: FirebaseFirestore
    var toolbar: Toolbar? = null
    var navigationView: NavigationView? = null
    var drawerLayout: DrawerLayout? = null

    lateinit var listSubj: ListView
    lateinit var lvHomepage: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)
        database = FirebaseFirestore.getInstance()
        toolbar = findViewById(R.id.toolbar)
        listSubj = findViewById<ListView>(R.id.list)
        lvHomepage = findViewById<ListView>(R.id.lvHomepage)
        navigationView = findViewById(R.id.navigationview)
        drawerLayout = findViewById(R.id.drawerlayout)
        val MenuArrayList = ArrayList<Menu>()
        MenuArrayList.add(Menu("Home", R.drawable.ic_home))
        MenuArrayList.add(Menu("My words", R.drawable.ic_round_work_24))
        MenuArrayList.add(Menu("Quiz", R.drawable.learn))
        MenuArrayList.add(Menu("Setting", R.drawable.ic_round_settings_24))
        MenuArrayList.add(Menu("About us", R.drawable.ic_info))
        getData()

        toolbar = findViewById(R.id.toolbar)
        listSubj = findViewById(R.id.list)
        lvHomepage = findViewById(R.id.lvHomepage)
        navigationView = findViewById(R.id.navigationview)
        drawerLayout = findViewById(R.id.drawerlayout)

        val adapterMenu = MenuAdapter(MenuArrayList)
        lvHomepage.adapter = adapterMenu

        lvHomepage.onItemClickListener = OnItemClickListener() { _, _, position, _ ->
            val menu = adapterMenu.getItem(position)
            when {
                position === 0 -> {
                    val intent = Intent(this, HomepageActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                position === 1 -> {
                    val intent = Intent(this, MyWordActivity::class.java)
                    startActivity(intent)
                }
                position === 2 -> {
                    val intent = Intent(this, QuizActivity::class.java)
                    startActivity(intent)
                }
                position === 3 -> {
                    val intent = Intent(this, SettingsActivity::class.java)
                    startActivity(intent)
                }
                position === 4 -> {
                    val intent = Intent(this, AboutActivity::class.java)
                    startActivity(intent)

                }
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

    fun getData() {
        database.collection("Subjects").get().addOnCompleteListener { p0 ->
            var list = ArrayList<Subject>()
            if (p0.isSuccessful) {
                for (data in p0.result!!) {
                    list.add(
                        Subject(
                            data.id,
                            data.get("name") as String,
                            data.get("cover") as String
                        )
                    )
                }
                var adapter = SubjectAdapter(list)
                listSubj.adapter = adapter
                listSubj.onItemClickListener = OnItemClickListener() { parent, view, position, id ->
                    val intent = Intent(this, Flashcard::class.java)
                    val subject = adapter.getItem(position)
                    intent.putExtra("subjectId", subject.id)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

}
