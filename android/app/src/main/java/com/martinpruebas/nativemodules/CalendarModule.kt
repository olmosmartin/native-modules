package com.martinpruebas.nativemodules

import android.app.DatePickerDialog
import android.util.Log
import android.widget.Toast
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import com.facebook.react.bridge.Callback


class CalendarModule(reactContext: ReactApplicationContext) :
    ReactContextBaseJavaModule(reactContext) {
    override fun getName(): String {
        return "CalendarModule"
    }

    @ReactMethod
    fun createCalendarEvent(name: String, location: String) {
        Log.d("CalendarModule", "Create event called with name: $name and location: $location")
    }

    @ReactMethod
    fun showDatePicker(callback: Callback) {
        val currentActivity = currentActivity
        if (currentActivity != null) { // Check if activity is available
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                currentActivity, // Use current activity
                { _, selectedYear, selectedMonth, selectedDay ->
                    val formattedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                    callback.invoke(null, formattedDate) // Invoca el callback con la fecha formateada con error en null
//                    Toast.makeText(reactApplicationContext, formattedDate, Toast.LENGTH_SHORT).show()
                },
                year,
                month,
                day
            )

            datePickerDialog.show()
        } else {
            // Handle the case where the activity is not available
            Log.e("CalendarModule", "Error: Current activity is null, cannot show DatePicker")
            callback.invoke("No se pudo mostrar el DatePicker", null)
            // You might want to send an error back to JavaScript here
        }
    }

}