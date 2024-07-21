package com.example.project


import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.text.SimpleDateFormat
import java.util.*

class DebitFragment : Fragment() {

    private lateinit var containers: LinearLayout
    private val debitRecords: MutableList<DebitRecord> = mutableListOf()

    data class DebitRecord(
        var amount: Double,
        val category: String,
        val creationTime: Date,
        var lastModifiedTime: Date? = null
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_debit_fragement, container, false)
        containers = view.findViewById(R.id.containers)
        loadDebitRecordsFromSharedPreferences()
        val fabAdd: FloatingActionButton = view.findViewById(R.id.fabAdd)
        fabAdd.setOnClickListener { addEditBoxAndDropdown() }
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Add this fragment to the fragment manager with the tag "creditFragmentTag"
        parentFragmentManager.beginTransaction()
            .replace(R.id.frame_container, this, "debitFragmentTag")
            .commit()
    }

    private fun addEditBoxAndDropdown() {
        val inflater = LayoutInflater.from(requireContext())
        val view = inflater.inflate(R.layout.edit_dropdown, containers, false)

        val editText: EditText = view.findViewById(R.id.editText)
        val dropdown: Spinner = view.findViewById(R.id.dropdown)
        val okButton: Button = view.findViewById(R.id.okButton)

        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.options_array,
            R.layout.custom_background
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        dropdown.adapter = adapter

        okButton.setOnClickListener {
            val selectedOption = dropdown.selectedItem.toString()
            val text = editText.text.toString()
            if (text.isNotEmpty()) {
                val currentTime = Calendar.getInstance().time
                val record = DebitRecord(text.toDouble(), selectedOption, currentTime)
                debitRecords.add(record) // Corrected line
                addRecordTextView(record)
                saveDebitRecordsToSharedPreferences()
            }
            containers.removeView(view)
        }

        containers.addView(view)
    }

    private fun addRecordTextView(record: DebitRecord) {
        val container = LinearLayout(requireContext())
        container.orientation = LinearLayout.HORIZONTAL
        container.gravity = Gravity.CENTER_VERTICAL
        container.setPadding(16, 16, 16, 16)
        container.background = createBackgroundDrawable() // Set background drawable for the record

        val textView = TextView(requireContext())
        textView.text = "${record.category}: ${record.amount}"
        textView.textSize = 18f
        textView.setTextColor(Color.BLACK)
        val textLayoutParams = LinearLayout.LayoutParams(
            0,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        textLayoutParams.weight = 1f
        textView.layoutParams = textLayoutParams

        val editIcon = ImageView(requireContext())
        editIcon.setImageResource(R.drawable.baseline_edit_24)
        val editLayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        editIcon.layoutParams = editLayoutParams
        editIcon.setOnClickListener { editRecord(record, textView) }

        val deleteIcon = ImageView(requireContext())
        deleteIcon.setImageResource(R.drawable.baseline_delete_24)
        val deleteLayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        deleteLayoutParams.setMargins(16, 0, 0, 0) // Add margin between edit and delete icons
        deleteIcon.layoutParams = deleteLayoutParams
        deleteIcon.setOnClickListener { showDeleteConfirmationDialog(record, container) }

        container.addView(textView)
        container.addView(editIcon)
        container.addView(deleteIcon)

        container.setOnClickListener {
            showRecordDetails(record)
        }

        containers.addView(container)
    }

    private fun showRecordDetails(record: DebitRecord) {
        val dialogBuilder = AlertDialog.Builder(requireContext())
        dialogBuilder.setTitle("Record Details")

        val dateFormat = SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.getDefault())
        val creationTimeString = dateFormat.format(record.creationTime)
        val lastModifiedTimeString = record.lastModifiedTime?.let { dateFormat.format(it) } ?: "Not modified"

        dialogBuilder.setMessage("Amount: ${record.amount}\nCategory: ${record.category}\nCreated: $creationTimeString\nLast Modified: $lastModifiedTimeString")

        dialogBuilder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = dialogBuilder.create()
        dialog.show()
    }

    private fun editRecord(record: DebitRecord, textView: TextView) {
        val dialogBuilder = AlertDialog.Builder(requireContext())
        dialogBuilder.setTitle("Edit Record")

        val editText = EditText(requireContext())
        editText.setText(record.amount.toString())

        dialogBuilder.setView(editText)

        dialogBuilder.setPositiveButton("Save") { _, _ ->
            val newText = editText.text.toString()
            if (newText.isNotEmpty()) {
                val currentTime = Calendar.getInstance().time
                record.amount = newText.toDouble()
                record.lastModifiedTime = currentTime
                textView.text = "${record.category}: ${record.amount}" // Update the text view with the new record name
                saveDebitRecordsToSharedPreferences()
                Toast.makeText(requireContext(), "Record updated", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Please enter a valid name", Toast.LENGTH_SHORT).show()
            }
        }

        dialogBuilder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = dialogBuilder.create()
        dialog.show()
    }

    private fun showDeleteConfirmationDialog(record: DebitRecord, container: LinearLayout) {
        val dialogBuilder = AlertDialog.Builder(requireContext())
        dialogBuilder.setTitle("Delete Record")
            .setMessage("Are you sure you want to delete this record?")

        dialogBuilder.setPositiveButton("Delete") { _, _ ->
            debitRecords.remove(record)
            containers.removeView(container)
            saveDebitRecordsToSharedPreferences()
            Toast.makeText(requireContext(), "Record deleted", Toast.LENGTH_SHORT).show()
        }

        dialogBuilder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = dialogBuilder.create()
        dialog.show()
    }

    private fun createBackgroundDrawable(): GradientDrawable {
        val shape = GradientDrawable()
        shape.shape = GradientDrawable.RECTANGLE
        shape.setColor(Color.LTGRAY)
        shape.setStroke(2, Color.BLACK)
        shape.cornerRadius = 10f
        return shape
    }

    private fun saveDebitRecordsToSharedPreferences() {
        val sharedPreferences = requireContext().getSharedPreferences("DebitRecords", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(debitRecords)
        editor.putString("DebitRecords", json)
        editor.apply()
    }

    private fun loadDebitRecordsFromSharedPreferences() {
        val sharedPreferences = requireContext().getSharedPreferences("DebitRecords", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("DebitRecords", null)
        val type = object : TypeToken<MutableList<DebitRecord>>() {}.type
        debitRecords.clear()
        debitRecords.addAll(gson.fromJson(json, type) ?: emptyList())
        debitRecords.forEach { addRecordTextView(it) }
    }
    internal fun calculateCategorySums(): Map<String, Double> {
        val categorySums = mutableMapOf<String, Double>()

        for (record in debitRecords) {
            val amount = record.amount.toDouble() ?: continue
            val category = record.category

            // Update category sum
            categorySums[category] = (categorySums[category] ?: 0.0) + amount
        }

        return categorySums
    }
    fun getTotalCategorySums(): Map<String, Double> {
        return calculateCategorySums()
    }

}