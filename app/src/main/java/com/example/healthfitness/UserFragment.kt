package com.example.healthfitness

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.lang.StringBuilder

class UserFragment : Fragment() {

    private lateinit var editTextName: EditText
    private lateinit var editTextAge: EditText
    private lateinit var editTextHeight: EditText
    private lateinit var editTextBMI: EditText
    private lateinit var editTextCurrentWeight: EditText
    private lateinit var editTextTargetWeight: EditText
    private lateinit var datePicker: DatePicker
    private lateinit var buttonSave: Button
    private lateinit var buttonRetrieve: Button
    private lateinit var buttonClear: Button
    private lateinit var buttonDelete: Button
    private lateinit var buttonSaveToSDCard: Button
    private lateinit var textViewUserData: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user, container, false)

        // Initialize views
        editTextName = view.findViewById(R.id.editTextName)
        editTextAge = view.findViewById(R.id.editTextAge)
        editTextHeight = view.findViewById(R.id.editTextHeight)
        editTextBMI = view.findViewById(R.id.editTextBMI)
        editTextCurrentWeight = view.findViewById(R.id.editTextCurrentWeight)
        editTextTargetWeight = view.findViewById(R.id.editTextTargetWeight)
        datePicker = view.findViewById(R.id.datePicker)
        buttonSave = view.findViewById(R.id.buttonSave)
        buttonRetrieve = view.findViewById(R.id.buttonRetrieve)
        buttonClear = view.findViewById(R.id.buttonClear)
        buttonDelete = view.findViewById(R.id.buttonDelete)
        buttonSaveToSDCard = view.findViewById(R.id.buttonSaveToSDCard)
        textViewUserData = view.findViewById(R.id.textViewUserData)

        // Set click listeners
        buttonSave.setOnClickListener {
            saveUserData(requireActivity())
        }

        buttonRetrieve.setOnClickListener {
            displayUserData()
        }

        buttonClear.setOnClickListener {
            clearUserData()
        }

        buttonDelete.setOnClickListener {
            if (deleteUserData(requireActivity())) {
                showToast("User data deleted successfully")
            } else {
                showToast("No user data found to delete")
            }
        }

        buttonSaveToSDCard.setOnClickListener {
            saveToSDCard(requireActivity())
        }

        return view
    }

    private fun displayUserData() {
        val filename = "user_data.txt"

        try {
            val fileInputStream = requireActivity().openFileInput(filename)
            val inputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            val stringBuilder = StringBuilder()
            var line: String? = bufferedReader.readLine()

            while (line != null) {
                stringBuilder.append(line).append("\n")
                line = bufferedReader.readLine()
            }

            textViewUserData.text = stringBuilder.toString()

            bufferedReader.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun saveUserData(activity: Context) {
        val userData = buildUserDataString()
        val filename = "user_data.txt"
        val fileContents = userData.toString()

        try {
            activity.openFileOutput(filename, Context.MODE_PRIVATE).use { out ->
                out.write(fileContents.toByteArray())
            }
            showToast("User data saved successfully")
        } catch (e: Exception) {
            e.printStackTrace()
            showToast("Error saving user data")
        }
    }

    private fun clearUserData() {
        editTextName.text.clear()
        editTextAge.text.clear()
        editTextHeight.text.clear()
        editTextBMI.text.clear()
        editTextCurrentWeight.text.clear()
        editTextTargetWeight.text.clear()
        textViewUserData.text = "" // Clear the displayed user data
    }

    private fun deleteUserData(activity: Context): Boolean {
        val filename = "user_data.txt"

        try {
            val file = activity.getFileStreamPath(filename)
            if (file.exists()) {
                file.delete()
                textViewUserData.text = "" // Clear the displayed user data
                return true // Indicate successful deletion
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false // Indicate failure to delete
    }

    private fun saveToSDCard(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // Request permission if not granted
                requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
                return
            }
        }

        val userData = buildUserDataString()
        val filename = "user_data.txt"

        val externalStorageState = Environment.getExternalStorageState()
        if (externalStorageState == Environment.MEDIA_MOUNTED) {
            val externalDirectory = Environment.getExternalStorageDirectory()
            val file = File(externalDirectory.absolutePath, filename)
            try {
                FileOutputStream(file).use { outputStream ->
                    outputStream.write(userData.toByteArray())
                }
                showToast("User data saved to SD Card successfully")
            } catch (e: Exception) {
                e.printStackTrace()
                showToast("Error saving data to SD Card")
            }
        } else {
            showToast("External storage not available")
        }
    }

    private fun buildUserDataString(): String {
        val userData = StringBuilder()
        userData.append("Name: ").append(editTextName.text.toString()).append("\n")
        userData.append("Age: ").append(editTextAge.text.toString()).append("\n")
        userData.append("Height: ").append(editTextHeight.text.toString()).append(" cm\n")
        userData.append("BMI: ").append(editTextBMI.text.toString()).append("\n")
        userData.append("Current Weight: ").append(editTextCurrentWeight.text.toString()).append("\n")
        userData.append("Target Weight: ").append(editTextTargetWeight.text.toString()).append("\n")
        userData.append("Start Date: ").append(datePicker.month + 1).append("/")
            .append(datePicker.dayOfMonth).append("/").append(datePicker.year).append("\n")
        return userData.toString()
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
