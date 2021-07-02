package com.xfs.flashcard.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xfs.flashcard.R
import kotlinx.android.synthetic.main.activity_quiz.*
import kotlinx.android.synthetic.main.activity_result_quiz.*

class ResultQuizActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_quiz)

        val totalQuestions = intent.getIntExtra("total_question",0)
        val correctAnswer = intent.getIntExtra("correct_answers",0)

        tv_score.text = "Your score is $correctAnswer out of $totalQuestions"
        btn_complete.setOnClickListener{
            startActivity(Intent(this,HomepageActivity::class.java))
            finish()
        }
    }
}