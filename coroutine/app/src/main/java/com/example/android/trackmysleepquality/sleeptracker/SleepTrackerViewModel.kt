package com.example.android.trackmysleepquality.sleeptracker

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.example.android.trackmysleepquality.database.SleepDatabaseDao
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.formatNights
import kotlinx.coroutines.launch

class SleepTrackerViewModel(
        val database: SleepDatabaseDao,
        application: Application
) : AndroidViewModel(application) {

        private var tonight = MutableLiveData<SleepNight?>()
        private val nights = database.getAllNights()

        val nightsString = Transformations.map(nights) { nights ->
                formatNights(nights, application.resources)
        }

        init {
                initializeTonight()
        }

        private fun initializeTonight() {
                viewModelScope.launch {
                        tonight.value = getTonightFromDatabase()
                }
        }

        private suspend fun getTonightFromDatabase(): SleepNight? {
                var night = database.getTonight()
                if (night?.endTimeMilli != night?.startTimeMilli) {
                        night = null
                }
                return night
        }

        fun onStartTracking() {
                viewModelScope.launch {
                        val newNight = SleepNight()
                        Log.d("flow", "$newNight")
                        insert(newNight)
                        tonight.value = getTonightFromDatabase()
                }
        }

        fun onStopTracking() {
                viewModelScope.launch {
                        val oldNight = tonight.value ?: return@launch
                        Log.d("flow", "$oldNight")
                        oldNight.endTimeMilli = System.currentTimeMillis()
                        update(oldNight)
                }
        }

        fun onClear() {
                viewModelScope.launch {
                        clear()
                        tonight.value = null
                }
        }

        private suspend fun clear() {
                database.clear()
        }

        private suspend fun insert(night: SleepNight) {
                database.insert(night)
        }

        private suspend fun update(night: SleepNight) {
                database.update(night)
        }
}

