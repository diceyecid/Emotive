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
    private var cameraPermissionGranted = false
    private var readPermissionGranted = false
    private var writePermissionGranted = false
    private val cameraPermissionCode = 100
    private var photoPathUri: Uri? = null
    private lateinit var mood: Mood

    @SuppressLint("QueryPermissionsNeeded") override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_entry)
        ////THIS IS SO THAT ON CLICK THE TEXT BOX CLEARS. THE PROBLEM IS THE KEYBOARD STAYS AFTER ENTER
        ////WE MAY HAVE TO HAVE THE INPUT TEXT AND QUESTION TEXT SEPARATE
        /*
        inputText.setOnClickListener{
            if (inputText.getText().toString() == "Anything You Want To Share?"){
                inputText.getText().clear()
            }
        }
        */

        ///THIS DIDN'T WORK BUT I'M ARCHIVING IT
        /*
        inputText.setOnKeyListener { view, i, keyEvent ->
            val myText = inputText.text
            false
        }
         */

        viewModel = ViewModelProvider(this, MoodViewModelFactory(this.application))[MoodViewModel::class.java]

        var newMood = intent.getSerializableExtra("mood") as Mood
        moodFace.setImageResource(newMood.resource)
        mood = newMood


//        when(newFace){
//            "1" -> {
//                moodFace.setImageResource(R.drawable.tier01)
//                println("This is 2")
//            }
//
//            "2" -> {
//                moodFace.setImageResource(R.drawable.tier02)
//                println("When x is any number from 3,4,5,6,7,8")
//            }
//            "3" -> {
//                moodFace.setImageResource(R.drawable.tier03)
//                println("When x is any number from 3,4,5,6,7,8")
//            }
//            "4" -> {
//                moodFace.setImageResource(R.drawable.tier04)
//                println("When x is any number from 3,4,5,6,7,8")
//            }
//            "5" -> {
//                moodFace.setImageResource(R.drawable.tier05)
//                println("When x is any number from 3,4,5,6,7,8")
//            }
//            else -> {
//                println("Error")
//            }
//
//        }

        tePrevImage.setOnClickListener {
            finish()
        }
        /*
        doneButtonImage.setOnClickListener {
            // save newMood to database
            newMood.text = inputText.text.toString()
            viewModel.insert( newMood )

            val intent = Intent (this,
                MainActivity::class.java)
            startActivity(intent)
        }

         */

        if (newMood.text != null) inputText.setText(newMood.text)


        doneButtonImage.setOnClickListener {
            // save newMood to database
            if (newMood.text == null) {
                newMood.text = inputText.text.toString()
                viewModel.insertMood(newMood)

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            else {
                newMood.text = inputText.text.toString()
                viewModel.updateMood(newMood)
                super.onBackPressed()

            }
        }
        /*
        // if it's a mood entry edit
        if( newMood.text !== null )
        {
            // display text
            if( newMood.text != null )
                inputText.setText( newMood.text )

            // overwrite done button listener
            doneButtonImage.setOnClickListener {
                // save newMood to database
                //newMood.text = inputText.text.toString()
                //viewModel.setMoodEntry( newMood )
                //viewModel.updateMood(newMood)
                //super.onBackPressed()
                //finish()
            }
        }

         */


        //PHOTO PART
        if (newMood.uri != null) inputPhoto.setImageURI(newMood.uri!!.toUri())

        takePhotoButton.setOnClickListener {
            checkCameraPermission()
            newMood = mood
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






















