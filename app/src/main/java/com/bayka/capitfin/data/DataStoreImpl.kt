package com.bayka.capitfin.data

import android.content.Context
import androidx.core.content.edit
import com.bayka.capitfin.domain.DataStore
import javax.inject.Inject

class DataStoreImpl @Inject constructor(private val context: Context) : DataStore {
    private val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)


    override fun saveGoals(goals: List<String>) {
        val joined = goals.joinToString("||")
        sharedPreferences.edit { putString(KEY_SAVINGS, joined) }
    }

    override fun loadGoals(): List<String> {
        val saved = sharedPreferences.getString(KEY_SAVINGS, null) ?: return emptyList()
        return saved.split("||")
    }

    companion object {
        const val PREFS_NAME = "prefs"
        const val KEY_SAVINGS = "savings_list"
    }


}