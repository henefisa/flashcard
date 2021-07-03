package com.xfs.flashcard.preference

import android.content.Context
import android.util.AttributeSet
import androidx.preference.DialogPreference

// This class is used in our preference where user can pick a time for notifications to appear.
// Specifically, this class is responsible for saving/retrieving preference data.
class TimePickerPreference(context: Context?, attrs: AttributeSet?) : DialogPreference(context, attrs) {

    fun getPersistedMinutesFromMidnight(): Int {
        return super.getPersistedInt(DEFAULT_MINUTES_FROM_MIDNIGHT)
    }

    // Save preference
    fun persistMinutesFromMidnight(minutesFromMidnight: Int) {
        super.persistInt(minutesFromMidnight)
        notifyChanged()
    }

    override fun onSetInitialValue(defaultValue: Any?) {
        super.onSetInitialValue(defaultValue)
    }

    // Mostly for default values
    companion object {
        // By default we want notification to appear at 9 AM each time.
        private const val DEFAULT_HOUR = 9
        const val DEFAULT_MINUTES_FROM_MIDNIGHT = DEFAULT_HOUR * 60
    }

}