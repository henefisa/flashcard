package com.xfs.flashcard.activities

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.xfs.flashcard.R
import com.xfs.flashcard.adapter.WordExamplesAdapter

class WordActivity : AppCompatActivity() {
    lateinit var wordExamplesListView: ListView
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.word)
        val sample = ArrayList<String>()
        sample.add("lorem ipslum lorem lorem lorem lorem lorem lorem lorem lorem")
        sample.add("lorem ipslum lorem lorem lorem lorem lorem lorem lorem lorem")
        sample.add("lorem ipslum lorem lorem lorem lorem lorem lorem lorem lorem")

        val wordExamplesAdapter = WordExamplesAdapter(sample)
        wordExamplesListView = findViewById(R.id.word_example_list)
        wordExamplesListView.adapter = wordExamplesAdapter
        wordExamplesListView.divider = null
        wordExamplesListView.onItemClickListener = AdapterView.OnItemClickListener() { _, _, position, _ ->
            val sample = wordExamplesAdapter.getItem(position)
            Toast.makeText(this, sample, Toast.LENGTH_SHORT).show()
        }
    }
}