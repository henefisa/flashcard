package com.xfs.flashcard.adapter

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Context
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import com.xfs.flashcard.R
import com.xfs.flashcard.holders.FlashcardHolder
import com.xfs.flashcard.models.Word

class FlashcardAdapter(w: ArrayList<Word>) : RecyclerView.Adapter<FlashcardHolder>() {
    private val words = w
    val db = Firebase.firestore
    private lateinit var front_anim: AnimatorSet
    private lateinit var back_anim: AnimatorSet
    lateinit var context: Context
    var isFront = true

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlashcardHolder {
        front_anim = AnimatorInflater.loadAnimator(parent.context, R.animator.flashcard_front_anim) as AnimatorSet
        back_anim = AnimatorInflater.loadAnimator(parent.context, R.animator.flashcard_back_anim) as AnimatorSet
        context = parent.context

        return FlashcardHolder(LayoutInflater.from(parent.context).inflate(R.layout.flashcard_content, parent, false))
    }

    override fun onBindViewHolder(holder: FlashcardHolder, position: Int) {
        val scale = context.resources.displayMetrics.density
        val id: String = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        if (holder != null) {

            holder.word.text = words[position].value
            holder.front.cameraDistance = 8000 * scale
            holder.back.cameraDistance = 8000 * scale
            holder.back.visibility = View.INVISIBLE
            holder.layout.setOnClickListener {
                isFront = if (isFront) {
                    front_anim.setTarget(holder.front)
                    back_anim.setTarget(holder.back)
                    front_anim.start()
                    back_anim.start()
                    holder.back.visibility = View.VISIBLE
                    false
                } else {
                    front_anim.setTarget(holder.back)
                    back_anim.setTarget(holder.front)
                    front_anim.start()
                    back_anim.start()
                    true
                }
            }
            holder.mean.text = words[position].mean
            holder.spell.text = words[position].spell
            Picasso.get().load(words[position].image).placeholder(R.drawable.icon_img).into(holder.image)
            val exampleAdapter = WordExamplesAdapter(words[position].examples)
            holder.examples.adapter = exampleAdapter

            holder.addToMyWord.setOnClickListener {
                val ref = db.collection("Favorites").document(id)
                ref.get().addOnSuccessListener() { docs ->
                    if (docs.exists()) {
                        ref.update(
                            mapOf(
                                "words" to FieldValue.arrayUnion(words[position].id)
                            )
                        )
                    } else {
                        ref.set(
                            mapOf(
                                "words" to ArrayList<String>().add(words[position].id)
                            )
                        )
                    }
                }
            }

        }
    }

    override fun getItemCount(): Int {
        return words.size
    }

}