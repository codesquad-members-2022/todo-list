package com.example.todolist.ui

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.google.android.material.snackbar.Snackbar

class CreateCardDialogFragment(private val recyclerView: RecyclerView) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            builder.setView(inflater.inflate(R.layout.dialog_fragment, null))
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun test() {
        Snackbar.make(recyclerView, "등록 ", Snackbar.LENGTH_SHORT).show()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false

    }


}
