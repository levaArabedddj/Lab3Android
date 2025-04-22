package com.example.lab3android

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import java.io.File

class AddContactActivity : AppCompatActivity() {

    private lateinit var imageUri: Uri
    private lateinit var imageView: ImageView
    private val TAKE_PHOTO = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

        imageView = findViewById(R.id.contactImage)
        val btnTakePhoto = findViewById<Button>(R.id.btnTakePhoto)
        val btnAdd = findViewById<Button>(R.id.btnAdd)

        btnTakePhoto.setOnClickListener {
            val photoFile = File.createTempFile("contact_photo", ".jpg", cacheDir)
            imageUri = FileProvider.getUriForFile(this, "${packageName}.provider", photoFile)

            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
            startActivityForResult(intent, TAKE_PHOTO)
        }

        btnAdd.setOnClickListener {
            val name = findViewById<EditText>(R.id.inputName).text.toString()
            val email = findViewById<EditText>(R.id.inputEmail).text.toString()
            val phone = findViewById<EditText>(R.id.inputPhone).text.toString()

            val resultIntent = Intent().apply {
                putExtra("name", name)
                putExtra("email", email)
                putExtra("phone", phone)
                putExtra("photoUri", imageUri)
            }
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == TAKE_PHOTO && resultCode == RESULT_OK) {
            imageView.setImageURI(imageUri)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
