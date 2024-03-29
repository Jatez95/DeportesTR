package com.example.deportestr.ui.screens.tenis

import android.content.ContentValues
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deportestr.ui.models.Athlete
import com.example.deportestr.ui.models.SportEvent
import com.example.deportestr.ui.models.Team
import com.example.deportestr.ui.models.User
import com.example.deportestr.usecases.SearchAthletesBySportUsecases
import com.example.deportestr.usecases.SearchEventBySportUsecases
import com.example.deportestr.usecases.SearchTeamsBySportUsecases
import com.example.deportestr.usecases.SearchUserByEmailUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TenisViewModel @Inject constructor(
    private val searchUserByEmailUseCases: SearchUserByEmailUseCases,
    private val searchAthletesBySportUsecases: SearchAthletesBySportUsecases,
    private val searchEventsBySportUsecases: SearchEventBySportUsecases
) : ViewModel() {
    var userLoaded by mutableStateOf(false)
    var user: User? = null
    var athletes: List<Athlete>? = null
    var events: List<SportEvent>? = null
    var email by mutableStateOf("")
    private var sportId = 3

    fun loadInfo(email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val responseBody = searchUserByEmailUseCases.searchUserByEmail(email)
            val responseTeams = searchAthletesBySportUsecases.searchAthletesBySport(sportId)
            val responseEvents = searchEventsBySportUsecases.searchEventBySport(sportId)
            user = responseBody.body()
            athletes = responseTeams.body()
            events = responseEvents.body()
            userLoaded = true
            Log.i(ContentValues.TAG, "User loaded: $user")
        }
    }
}
