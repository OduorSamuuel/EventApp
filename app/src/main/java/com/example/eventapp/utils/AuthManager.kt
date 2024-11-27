package com.example.eventapp.utils

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AuthManager(private val context: Context) {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val _currentUser: MutableState<FirebaseUser?> = mutableStateOf(auth.currentUser)
    val currentUser: FirebaseUser? get() = _currentUser.value

    fun signIn(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _currentUser.value = auth.currentUser
                    onSuccess()
                } else {
                    onFailure(task.exception?.message ?: "Login failed")
                }
            }
    }

    fun signUp(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _currentUser.value = auth.currentUser
                    Toast.makeText(context, "Account created successfully!", Toast.LENGTH_SHORT).show()
                    onSuccess()
                } else {
                    onFailure(task.exception?.message ?: "Signup failed")
                }
            }
    }

    fun signOut() {
        auth.signOut()
        _currentUser.value = null
        Toast.makeText(context, "Logged out successfully", Toast.LENGTH_SHORT).show()
    }

    fun getUsername(): String {
        return currentUser?.email?.split("@")?.get(0) ?: "User"
    }

    fun isLoggedIn(): Boolean {
        return currentUser != null
    }
}