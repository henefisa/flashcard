package com.xfs.flashcard.activities

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.xfs.flashcard.R
import com.xfs.flashcard.adapter.MyWordAdapter
import com.xfs.flashcard.models.Word

class MyWordActivity : AppCompatActivity() {
    lateinit var myWordListView: ListView
    lateinit var emptyLayout: LinearLayout
    private val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_word_list_view)
        val id: String = Settings.Secure.getString(applicationContext.contentResolver, Settings.Secure.ANDROID_ID)
        val backToHome: Button = findViewById(R.id.back_to_home)
        backToHome.setOnClickListener {
            val intent = Intent(this, HomepageActivity::class.java)
            startActivity(intent)
            finish()
        }
        db.collection("Favorites").document(id).get().addOnSuccessListener { docs ->
            if (docs.exists()) {
                val words = ArrayList<Word>()
                val myWordAdapter = MyWordAdapter(words)
                val ws = docs.data?.get("words")
                if (ws != null && (ws as ArrayList<String>).size !== 0) {
                    for (w in ws as ArrayList<String>) {
                        db.collection("Words").document(w).get().addOnSuccessListener { docs ->
                            if (docs.exists()) {
                                val data = docs.data!!
                                val word = Word(
                                    docs.id,
                                    data["value"] as String,
                                    data["mean"] as String,
                                    data["spell"] as String,
                                    data["image"] as String,
                                    data["examples"] as ArrayList<String>
                                )
                                words.add(word)
                                myWordAdapter.notifyDataSetChanged()
                            }
                        }
                    }
                    myWordListView = findViewById(R.id.my_words_list)
                    myWordListView.adapter = myWordAdapter
                } else {
                    Toast.makeText(this, "Your words is empty!", Toast.LENGTH_SHORT).show()
                    emptyLayout = findViewById(R.id.empty_layout)
                    emptyLayout.visibility = View.VISIBLE
                }
            }
        }
    }
}