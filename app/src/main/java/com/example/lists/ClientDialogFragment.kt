package com.example.lists

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lists.databinding.DialogReviewBinding

class ClientDialogFragment : DialogFragment() {
    private val parentListener by lazy {
        parentFragment as? FragmentOnClickListener
    }
    private val neutralListener by lazy {
        parentFragment as? NeutralOnClickListener
    }
    private val binding:DialogReviewBinding by viewBinding(CreateMethod.INFLATE)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        val view = binding.root
        var rate = 0.0
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.number_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinner.adapter = adapter
        }
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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
            parentListener?.onFragmentClick(
                binding.inputName.text.toString(),
                binding.inputReview.text.toString(),
                rate
            )
        }
        builder.setNeutralButton("Add random company") { _, _ -> neutralListener?.onFragmentClick() }
        builder.setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }
        return builder.show()
    }
}