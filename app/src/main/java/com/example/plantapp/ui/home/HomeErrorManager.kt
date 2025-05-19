package com.example.baseapp.ui.home

import android.content.Context
import androidx.appcompat.app.AlertDialog

class HomeErrorManager(private val context: Context) {

    fun handleError(errorMessage: String) {
        AlertDialog.Builder(context)
            .setTitle("Hata")
            .setMessage(errorMessage)
            .setPositiveButton("Tamam") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
} 