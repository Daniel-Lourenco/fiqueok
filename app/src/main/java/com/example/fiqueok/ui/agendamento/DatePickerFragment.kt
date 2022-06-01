package com.example.fiqueok.ui.agendamento

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class DatePickerFragment(val callback: (result: String) -> Unit) : DialogFragment(),
    DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val myCalendar: Calendar = Calendar.getInstance()
        val year: Int = myCalendar.get(Calendar.YEAR)
        val month: Int = myCalendar.get(Calendar.MONTH)
        val day: Int = myCalendar.get(Calendar.DAY_OF_MONTH)
        return DatePickerDialog(requireContext(), this, year, month, day)
    }

    override fun onDateSet(picker: DatePicker?, year: Int, month: Int, day: Int) {
        val monthString = (month + 1).toString()
        val dayString = day.toString()
        val yearString = year.toString()
        callback("$dayString / $monthString / $yearString")
    }
}