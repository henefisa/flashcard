package com.xfs.flashcard.activities

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.xfs.flashcard.R
import com.xfs.flashcard.adapter.FlashcardAdapter
import com.xfs.flashcard.models.Word

class Flashcard : AppCompatActivity() {
    private val db = Firebase.firestore
    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flashcard)
        val subjectId = intent.extras?.get("subjectId")
        recyclerView = findViewById(R.id.recycler_flashcard_view)
        getData(subjectId as String)

    }

    private fun getData(subjectId: String) {
        db.collection("Words").whereEqualTo("subject", subjectId).get().addOnCompleteListener() { p ->
            val words = ArrayList<Word>()
            if (p.isSuccessful) {
                for (doc in p.result!!) {
                    val data = doc.data
                    val word = Word(
                        doc.id,
                        data["value"] as String,
                        data["mean"] as String,
                        data["spell"] as String,
                        data["image"] as String,
                        data["examples"] as List<String>
                    )
                    words.add(word)
                }
            }

            val helper = PagerSnapHelper();
            helper.attachToRecyclerView(recyclerView)
            val adapter = FlashcardAdapter(words)
            recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.setHasFixedSize(true)
            recyclerView.itemAnimator = DefaultItemAnimator()
            recyclerView.addItemDecoration(
                DividerItemDecoration(
                    recyclerView.context,
                    DividerItemDecoration.HORIZONTAL
                )
            )
            recyclerView.adapter = adapter
        }

    }
}