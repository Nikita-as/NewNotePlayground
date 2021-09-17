package com.example.newnoteplayground.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newnoteplayground.databinding.ItemNoteBinding
import com.example.newnoteplayground.fragments.HomeFragmentDirections
import com.example.newnoteplayground.models.Note

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private var binding: ItemNoteBinding? = null

    inner class NoteViewHolder(itemBinding: ItemNoteBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

    }

    private val differCallback =
        object : DiffUtil.ItemCallback<Note>() {
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem == newItem
            }

        }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        binding = ItemNoteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NoteViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = differ.currentList[position]

        holder.itemView.apply {
            binding?.tvNoteBody?.text = currentNote.noteBody
            binding?.tvNoteTitle?.text = currentNote.noteTitle
        }.setOnClickListener { mView ->
            val direction = HomeFragmentDirections
                .actionHomeFragmentToUpdateNoteFragment(currentNote)
            mView.findNavController().navigate(direction)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}