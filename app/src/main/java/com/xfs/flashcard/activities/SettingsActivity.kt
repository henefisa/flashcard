package com.xfs.flashcard.activities

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.preference.*
import com.xfs.flashcard.R
import com.xfs.flashcard.preference.TimePickerPreference
import com.xfs.flashcard.preference.TimePickerPreferenceDialog
import kotlinx.android.synthetic.main.settings_activity.*
import java.util.*


class SettingsActivity : AppCompatActivity() {
    private val CHANNEL_ID = "channel_id"
    private val notificationId = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        backButton.setOnClickListener {
            onBackPressed()
        }
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.setting_content, SettingsFragment())
                    .commit()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun createNotification(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Notification example"
            val descriptionText = "Notification description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun sendNotification(minute: Int){
        val calendar = Calendar.getInstance()
        val date = Date()
        calendar.timeInMillis = date.time
        calendar.add(Calendar.MINUTE, -10)
        val intent = Intent(this, MyBroadcastReceiver::class.java).apply{
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or  Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setWhen(System.currentTimeMillis())
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Flashcard")
            .setContentText("Don't forget to learn flashcard")
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        with(NotificationManagerCompat.from(this)){
            notify(notificationId, builder.build())
        }
        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            System.currentTimeMillis() + minute * 60000,
            pendingIntent
        )

    }

    class SettingsFragment : PreferenceFragmentCompat() {

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
            val time1 = 1
            val time2 = 0
            val notificationPreference = findPreference<SwitchPreference>("practice_reminder") as SwitchPreferenceCompat
            val breakTimerPreference = findPreference<ListPreference>("break_time")
            breakTimerPreference?.setOnPreferenceChangeListener { preference, newValue ->
                if (preference is ListPreference) {
                    val index = preference.findIndexOfValue(newValue.toString())
                    val entry = preference.entries.get(index)
                    val valueEntry = entry.toString()
                    when(notificationPreference.isChecked == true) {
                        (valueEntry == "15min") -> {
                            (activity as SettingsActivity?)?.sendNotification(time1)
                            Log.d("Notification", notificationPreference.isChecked.toString())
                        }
                        (valueEntry == "30min") -> {
                            if (notificationPreference.isChecked == true) {
                                (activity as SettingsActivity?)?.sendNotification(time2)
                                Log.d("Notification", notificationPreference.isChecked.toString())
                            }
                        }
                    }
                }
                true
            }
            val generalPreference = findPreference<ListPreference>("mode")
            generalPreference?.setOnPreferenceChangeListener { preference, newValue ->
                if (preference is ListPreference) {
                    val index = preference.findIndexOfValue(newValue.toString())
                    val entry = preference.entries.get(index)
                    val valueEntry = entry.toString()
                    if(valueEntry == "Dark"){
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    }
                    else{
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    }
                }
                true
            }

        }
        override fun onDisplayPreferenceDialog(preference: Preference?) {
            if(preference is TimePickerPreference) {
                val timepickerdialog = TimePickerPreferenceDialog.newInstance(preference.key)
                timepickerdialog.setTargetFragment(this, 0)
                timepickerdialog.show(parentFragmentManager, "TimePickerDialog")
            }
            else {
                super.onDisplayPreferenceDialog(preference)
            }
        }
    }
}