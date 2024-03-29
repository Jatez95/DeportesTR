package com.example.deportestr.ui.screens.registration

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deportestr.ui.models.User
import com.example.deportestr.usecases.AddUserUsecases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val addUserUseCases: AddUserUsecases
) :ViewModel() {
    var name: String by mutableStateOf("")
    var email: String by mutableStateOf("")
    var password: String by mutableStateOf("")
    var reapeatPassword: String by mutableStateOf("")
    var loginEnabled by mutableStateOf(false)



    fun addUser() {
        viewModelScope.launch(Dispatchers.IO) {
            val user = User(null, name, email, password, null, null)
            addUserUseCases.addUser(user)
        }
    }

    fun OnRegistrationChanged(email: String, password: String, repeatPassword: String) {
        this.email = email
        this.password = password
        this.reapeatPassword = repeatPassword
        loginEnabled = isValidEmail(email) && isValidPassword(password) && isEqualPassword(
            password,
            repeatPassword
        )
    }


    private fun isValidEmail(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun isValidPassword(password: String): Boolean = password.length > 6

    private fun isEqualPassword(password: String, repeatPassword: String): Boolean =
        password == repeatPassword

}
