package com.xfs.flashcard.activities

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.xfs.flashcard.R
import com.xfs.flashcard.adapter.MyWordAdapter
import com.xfs.flashcard.models.Word

class MyWordActivity : AppCompatActivity() {
    lateinit var myWordListView: ListView

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.my_word_list_view)
        val sampleWords = ArrayList<Word>()
        val word = Word("123123", "Sample", "Sample", "Sample", "Sample", arrayListOf("123123"))
        sampleWords.add(word)
        sampleWords.add(word)
        sampleWords.add(word)
        sampleWords.add(word)
        sampleWords.add(word)
        sampleWords.add(word)
        sampleWords.add(word)
        sampleWords.add(word)
        sampleWords.add(word)
        sampleWords.add(word)
        sampleWords.add(word)
        val myWordAdapter = MyWordAdapter(sampleWords)

        myWordListView = findViewById(R.id.my_words_list)
        myWordListView.adapter = myWordAdapter

        myWordListView.onItemClickListener = OnItemClickListener() { _, _, position, _ ->
            val word = myWordAdapter.getItem(position)
            Toast.makeText(this, word.value, Toast.LENGTH_SHORT).show()
        }

    }
}