package com.xfs.flashcard.holders

import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.xfs.flashcard.R

class FlashcardHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val word: TextView = itemView.findViewById(R.id.word)
    val mean: TextView = itemView.findViewById(R.id.mean)
    val spell: TextView = itemView.findViewById(R.id.spell)
    val layout: ConstraintLayout = itemView.findViewById(R.id.flashcard_front_back)
    val front: LinearLayout = itemView.findViewById(R.id.flashcard_front)
    val back: LinearLayout = itemView.findViewById(R.id.flashcard_back)
    val image: ImageView = itemView.findViewById(R.id.word_image)
    val examples: ListView = itemView.findViewById(R.id.word_example_list)
    val addToMyWord: Button = itemView.findViewById(R.id.add_to_my_word)
    val speak: ImageButton = itemView.findViewById(R.id.text_to_speech_btn)


}