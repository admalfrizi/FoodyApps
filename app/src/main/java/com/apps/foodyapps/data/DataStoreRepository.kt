package com.apps.foodyapps.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.apps.foodyapps.utils.Constants.Companion.DEFAULT_DIET_TYPE
import com.apps.foodyapps.utils.Constants.Companion.DEFAULT_MEAL_TYPE
import com.apps.foodyapps.utils.Constants.Companion.PREFERENCES_BACK_ONLINE
import com.apps.foodyapps.utils.Constants.Companion.PREFERENCES_DIET_TYPE
import com.apps.foodyapps.utils.Constants.Companion.PREFERENCES_DIET_TYPE_ID
import com.apps.foodyapps.utils.Constants.Companion.PREFERENCES_MEAL_TYPE
import com.apps.foodyapps.utils.Constants.Companion.PREFERENCES_MEAL_TYPE_ID
import com.apps.foodyapps.utils.Constants.Companion.PREFERENCES_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class DataStoreRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private object PreferenceKeys{
        val selectedMlType = stringPreferencesKey(PREFERENCES_MEAL_TYPE)
        val selectedMlTypeId = intPreferencesKey(PREFERENCES_MEAL_TYPE_ID)
        val selectedDietType = stringPreferencesKey(PREFERENCES_DIET_TYPE)
        val selectedDietTypeId = intPreferencesKey(PREFERENCES_DIET_TYPE_ID)
        val backOnline = booleanPreferencesKey(PREFERENCES_BACK_ONLINE)
    }

    private val Context._dataStore: DataStore<Preferences> by preferencesDataStore(PREFERENCES_NAME)

    private val dataStore : DataStore<Preferences> = context._dataStore

    suspend fun saveMealAndDietType(mealType: String, mealTypeId: Int, dietType: String, dietTypeId : Int) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.selectedMlType] = mealType
            preferences[PreferenceKeys.selectedMlTypeId] = mealTypeId
            preferences[PreferenceKeys.selectedDietType] = dietType
            preferences[PreferenceKeys.selectedDietTypeId] = dietTypeId
        }
    }

    suspend fun saveBackOnline(backOnline: Boolean) {
        dataStore.edit {
            preferences ->
            preferences[PreferenceKeys.backOnline] = backOnline

        }
    }

    val readMealAndDietType : Flow<MealAndDietType>  = dataStore.data
        .catch {exception ->
            if(exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map {preferences ->
            val selectedMealType = preferences[PreferenceKeys.selectedMlType] ?: DEFAULT_MEAL_TYPE
            val selectedMealTypeId = preferences[PreferenceKeys.selectedMlTypeId] ?: 0
            val selectedDietType = preferences[PreferenceKeys.selectedDietType] ?: DEFAULT_DIET_TYPE
            val selectedDietTypeId = preferences[PreferenceKeys.selectedDietTypeId] ?: 0

            MealAndDietType(
                selectedMealType,
                selectedMealTypeId,
                selectedDietType,
                selectedDietTypeId
            )
        }
    val readBackOnline: Flow<Boolean> = dataStore.data
        .catch {exception ->
            if(exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map {preferences ->
            val backOnline = preferences[PreferenceKeys.backOnline] ?: false
            backOnline
        }
}

data class MealAndDietType(
    val selectedMealType: String,
    val selectedMealTypeId: Int,
    val selectedDietType: String,
    val selectedDietTypeId: Int
)