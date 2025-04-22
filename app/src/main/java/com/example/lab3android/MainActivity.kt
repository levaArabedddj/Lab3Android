package com.example.lab3android

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab3android.Model.Contact
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ContactAdapter
    private val contacts = mutableListOf<Contact>()
    private lateinit var emptyText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        emptyText = findViewById(R.id.emptyText)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val addButton = findViewById<FloatingActionButton>(R.id.addContactBtn)

        adapter = ContactAdapter(contacts) { index ->
            contacts.removeAt(index)
            updateUI()
        }

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        addButton.setOnClickListener {
            val intent = Intent(this, AddContactActivity::class.java)
            startActivityForResult(intent, 1)
        }

        updateUI()
    }

    private fun updateUI() {
        adapter.notifyDataSetChanged()
        emptyText.visibility = if (contacts.isEmpty()) View.VISIBLE else View.GONE
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            data?.let {
                val name = it.getStringExtra("name")!!
                val email = it.getStringExtra("email")!!
                val phone = it.getStringExtra("phone")!!
                val photoUri = it.getParcelableExtra<Uri>("photoUri")!!
                contacts.add(Contact(name, email, phone, photoUri))
                updateUI()
            }
        }
    }
}
