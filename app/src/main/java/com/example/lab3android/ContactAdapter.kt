package com.example.lab3android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab3android.Model.Contact

class ContactAdapter(
    private val contacts: MutableList<Contact>,
    private val onDeleteClick: (Int) -> Unit
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    inner class ContactViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val photo: ImageView = view.findViewById(R.id.contactPhoto)
        val name: TextView = view.findViewById(R.id.contactName)
        val email: TextView = view.findViewById(R.id.contactEmail)
        val phone: TextView = view.findViewById(R.id.contactPhone)
        val deleteButton: ImageButton = view.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        return ContactViewHolder(view)
    }

    override fun getItemCount() = contacts.size

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contacts[position]
        holder.photo.setImageURI(contact.photoUri)
        holder.name.text = contact.name
        holder.email.text = contact.email
        holder.phone.text = contact.phone
        holder.deleteButton.setOnClickListener {
            onDeleteClick(position)
        }
    }
}
