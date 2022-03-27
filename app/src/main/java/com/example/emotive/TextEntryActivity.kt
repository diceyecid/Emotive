package com.example.emotive

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_text_entry.*

class TextEntryActivity : AppCompatActivity() {

    private lateinit var viewModel: MoodViewModel
    private lateinit var mood: Mood

    // camera permissions
    private var cameraPermissionGranted = false
    private var readPermissionGranted = false
    private var writePermissionGranted = false
    private val cameraPermissionCode = 100
    private var photoPathUri: Uri? = null

    @SuppressLint("QueryPermissionsNeeded") override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_entry)

        // initialize view model and get mood
        viewModel = ViewModelProvider(this, MoodViewModelFactory(this.application))[MoodViewModel::class.java]
        mood = intent.getSerializableExtra("mood") as Mood
        moodFace.setImageResource(mood.resource)

        // back button
        tePrevImage.setOnClickListener {
            finish()
        }

        // if there are already texts, display them
        if (mood.text != null) inputText.setText(mood.text)

        // done button to save mood
        doneButtonImage.setOnClickListener {
            // save new mood to database
            if (mood.text == null) {
                mood.text = inputText.text.toString()
                viewModel.insertMood(mood)

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            // save edited mood to database
            else {
                mood.text = inputText.text.toString()
                viewModel.updateMood(mood)
                super.onBackPressed()

            }
        }

        //PHOTO PART
        if (mood.uri != null) inputPhoto.setImageURI(mood.uri!!.toUri())

        takePhotoButton.setOnClickListener {
            checkCameraPermission()
        }
    }

    private fun checkCameraPermission() {
        val camera = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        val read = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
        val write = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (camera == PackageManager.PERMISSION_GRANTED && read == PackageManager.PERMISSION_GRANTED && write == PackageManager.PERMISSION_GRANTED) {
            //Log.d("CHECK CAMERA", "if")
            cameraPermissionGranted = true
            readPermissionGranted = true
            writePermissionGranted = true
            takePhoto()
        }
        else {
            //Log.d("CHECK CAMERA", "else")
            makeRequest()
        }
    }

    private fun takePhoto() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        val values = ContentValues(1)
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        photoPathUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)

        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoPathUri)
        //Log.d("Take Photo", "if" + photoPathUri.toString())
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)

        getResult.launch(intent)
    }

    private val getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data
            inputPhoto.setImageURI(photoPathUri)
            inputPhoto.tag = photoPathUri.toString()
            mood.uri = photoPathUri.toString()

        }
    }

    //Request for the camera permission
    private fun makeRequest() {
        val camera = Manifest.permission.CAMERA
        val read = Manifest.permission.READ_EXTERNAL_STORAGE
        val write = Manifest.permission.WRITE_EXTERNAL_STORAGE
        ActivityCompat.requestPermissions(this, arrayOf(camera, read, write), cameraPermissionCode)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == cameraPermissionCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                cameraPermissionGranted = true
                readPermissionGranted = true
                writePermissionGranted = true
                takePhoto()
            }
            else Toast.makeText(this, "No Permission", Toast.LENGTH_SHORT).show()
        }
    }

}






















