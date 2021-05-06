package com.example.lists

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class ClientDialogFragment : DialogFragment() {
    private val parentListener by lazy {
        parentFragment as? FragmentOnClickListener
    }
    private val neutralListener by lazy {
        parentFragment as? NeutralOnClickListener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        val view = activity?.layoutInflater?.inflate(R.layout.dialog_review, null)
        var rate = 0.0
        val spinner = view?.findViewById<Spinner>(R.id.spinner)
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.number_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner?.adapter = adapter
        }
        spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> rate = 1.0
                    1 -> rate = 2.0
                    2 -> rate = 3.0
                    3 -> rate = 4.0
                    4 -> rate = 5.0
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
        builder.setView(view)
        builder.setPositiveButton("Add review") { _, _ ->
            val inputName = dialog?.findViewById<EditText>(R.id.inputName)?.text.toString()
            val inputReview = dialog?.findViewById<EditText>(R.id.inputReview)?.text.toString()
            parentListener?.onFragmentClick(inputName, inputReview, rate)
        }
        builder.setNeutralButton("Add random company") { _, _ -> neutralListener?.onFragmentClick() }
        builder.setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }
        return builder.show()
    }
}