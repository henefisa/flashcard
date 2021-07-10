package com.xfs.flashcard.adapter

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.app.Activity
import android.content.Context
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import com.xfs.flashcard.R
import com.xfs.flashcard.holders.FlashcardHolder
import com.xfs.flashcard.models.Word
import com.xfs.flashcard.utils.TTS

class FlashcardAdapter(w: ArrayList<Word>, a: Activity) : RecyclerView.Adapter<FlashcardHolder>() {

    private val words = w
    private val activity = a
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
            holder.examples.onItemClickListener = AdapterView.OnItemClickListener() { _, _, _, _ ->
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
            holder.speak.setOnClickListener {
                TTS(activity, words[position].value)
            }
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
                        val al = ArrayList<String>()
                        al.add(words[position].id)
                        ref.set(
                            mapOf(
                                "words" to al
                            )
                        )
                    }
                    Toast.makeText(context, "Word added to my words!", Toast.LENGTH_SHORT).show()
                }
            }

        }

    }


    override fun getItemCount(): Int {
        return words.size
    }
//    override fun onCreate(savedInstanceState: Bundle?): AppCompatActivity {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.flashcard_content)
//
//        // get reference to ImageView
//        val text_to_speech_btn = findViewById(R.id.text_to_speech_btn) as ImageView
//        // set on-click listener
//        text_to_speech_btn.setOnClickListener {
//            // your code to perform when the user clicks on the ImageView
//            Toast.makeText(this@FlashcardAdapter, "You clicked on ImageView.", Toast.LENGTH_SHORT).show()
//        }
//    }


}




