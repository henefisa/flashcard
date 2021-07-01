package com.xfs.flashcard.activities

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import com.xfs.flashcard.R
import com.xfs.flashcard.models.Question
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var database: FirebaseFirestore

    private var currentPosition : Int = 1
    private var questionList = ArrayList<Question>()
    private var selectOptionPosition : Int = 0
    private var correctAnswers : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        database = FirebaseFirestore.getInstance()
        val que1 = Question("1","What is this?","https://www.lushusa.com/dw/image/v2/BDMQ_PRD/on/demandware.static/-/Library-Sites-LushSharedLibrary/default/dwf97cb700/images/ingredients/10424.jpg?sw=250&sh=250&sm=fit",
            "orange","banana","mango","grape",1)
        val que2 = Question("2","What is this?","https://www.lushusa.com/dw/image/v2/BDMQ_PRD/on/demandware.static/-/Library-Sites-LushSharedLibrary/default/dwf97cb700/images/ingredients/10424.jpg?sw=250&sh=250&sm=fit",
            "mango","banana","orange","grape",3)
        val que3 = Question("3","What is this?","https://www.lushusa.com/dw/image/v2/BDMQ_PRD/on/demandware.static/-/Library-Sites-LushSharedLibrary/default/dwf97cb700/images/ingredients/10424.jpg?sw=250&sh=250&sm=fit",
            "banana","orange","mango","grape",2)
//        questionList.add(que1)
        questionList.add(que2)
        questionList.add(que3)
        getQuestion()

        setQuiz()
        tv_optionOne.setOnClickListener(this)
        tv_optionTwo.setOnClickListener(this)
        tv_optionThree.setOnClickListener(this)
        tv_optionFour.setOnClickListener(this)
        btn_submit.setOnClickListener(this)
    }

    fun getQuestion() {
        database.collection("Questions").get().addOnCompleteListener { p0 ->
            if (p0.isSuccessful) {
                for (data in p0.result!!) {
                    questionList.add(
                        Question(
                            data.id,
                            data.get("question") as String,
                            data.get("img") as String,
                            data.get("optionOne") as String,
                            data.get("optionTwo") as String,
                            data.get("optionThree") as String,
                            data.get("optionFour") as String,
                            data.get("correctAnswer") as Long
                        )
                    )

                }
            }
        }
    }

    override fun onClick(v : View?) {
        when(v?.id){
            R.id.tv_optionOne -> {
                selectedOptionView(tv_optionOne,1)
            }
            R.id.tv_optionTwo -> {
                selectedOptionView(tv_optionTwo,2)
            }
            R.id.tv_optionThree -> {
                selectedOptionView(tv_optionThree,3)
            }
            R.id.tv_optionFour -> {
                selectedOptionView(tv_optionFour,4)
            }
            R.id.btn_submit ->{
                if(selectOptionPosition.toInt() == 0){
                    currentPosition+=1

                    when{
                        currentPosition <= questionList.size -> {
                            setQuiz()
                        }else ->{
                        val intent = Intent(this,ResultQuizActivity::class.java)
                        intent.putExtra("correct_answers",correctAnswers)
                        intent.putExtra("total_question",questionList.size)
                        startActivity(intent)
                    }
                    }
                }else{
                    val question = questionList[currentPosition-1]
                    if(question.correctAnswer.toInt() != selectOptionPosition){
                        answerView(selectOptionPosition.toInt(),R.drawable.wrong_option_border)
                    }else{
                        correctAnswers+=1
                    }
                    answerView(question.correctAnswer.toInt(),R.drawable.correct_option_border)
                    if(currentPosition == questionList.size){
                        btn_submit.text = "FINISH"
                    }else{
                        btn_submit.text = "NEXT QUESTION"
                    }
                    selectOptionPosition = 0
                }
            }
        }
    }

    private fun answerView(answer: Int, drawableView: Int){
        when(answer){
            1 ->{
                tv_optionOne.background = ContextCompat.getDrawable(this,drawableView)
            }
            2 ->{
                tv_optionTwo.background = ContextCompat.getDrawable(this,drawableView)
            }
            3 ->{
                tv_optionThree.background = ContextCompat.getDrawable(this,drawableView)
            }
            4 ->{
                tv_optionFour.background = ContextCompat.getDrawable(this,drawableView)
            }
        }
    }

    private fun setQuiz(){
        val question = questionList[currentPosition-1]
        defaultOptionView()

        if(currentPosition == questionList.size){
            btn_submit.text = "FINISH"
        }else{
            btn_submit.text = "SUBMIT"
        }

        progressBar.progress = currentPosition
        tv_progress.text="$currentPosition" + "/" + progressBar.max
        tv_question.text = question.question
        Picasso.get().load(question.img).placeholder(R.drawable.icon_img).into(findViewById<ImageView>(R.id.iv_image))
        tv_optionOne.text = question.optionOne
        tv_optionTwo.text = question.optionTwo
        tv_optionThree.text = question.optionThree
        tv_optionFour.text = question.optionFour
    }

    private  fun selectedOptionView(tv : TextView, selectedOptionNum: Int){
        defaultOptionView()
        selectOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#333333"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this,R.drawable.selected_option_border)
    }

    private fun defaultOptionView() {
        val options = ArrayList<TextView>()
        options.add(0,tv_optionOne)
        options.add(1,tv_optionTwo)
        options.add(2,tv_optionThree)
        options.add(3,tv_optionFour)

        for (option in options){
            option.setTextColor(Color.parseColor("#808080"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this,R.drawable.default_option_border)
        }
    }


}