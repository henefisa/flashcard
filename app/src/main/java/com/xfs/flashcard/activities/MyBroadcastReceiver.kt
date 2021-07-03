package com.xfs.flashcard.activities

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val intent = Intent(context, HomepageActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        Toast.makeText(context.getApplicationContext(), "Have a good time!", Toast.LENGTH_SHORT).show()
        context.startActivity(intent)
    }
}