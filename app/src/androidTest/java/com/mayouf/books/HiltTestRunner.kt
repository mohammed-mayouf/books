package com.mayouf.books

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import dagger.hilt.android.testing.HiltTestApplication

class HiltTestRunner : AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        // Ensure the test uses HiltTestApplication instead of your production application.
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }
}
