package com.example.newnoteplayground.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.newnoteplayground.MainActivity
import com.example.newnoteplayground.R
import com.example.newnoteplayground.databinding.FragmentUpdateNoteBinding
import com.example.newnoteplayground.helper.toast
import com.example.newnoteplayground.models.Note
import com.example.newnoteplayground.viewmodel.NoteViewModel

class UpdateNoteFragment : Fragment() {

    private var _binding: FragmentUpdateNoteBinding? = null
    private val binding get() = _binding!!

    private val args: UpdateNoteFragmentArgs by navArgs()
    private lateinit var currentNote: Note
    private lateinit var noteViewModel: NoteViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateNoteBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteViewModel = (activity as MainActivity).noteViewModel

        currentNote = args.note!!
        binding.etNoteTitleUpdate.setText(currentNote.noteTitle)
        binding.etNoteBodyUpdate.setText(currentNote.noteBody)
        binding.fabUpdate.setOnClickListener {
            val title = binding.etNoteTitleUpdate.text.toString().trim()
            val body = binding.etNoteBodyUpdate.text.toString().trim()
            if (title.isNotEmpty()) {
                val note = Note(currentNote.id, title, body)
                noteViewModel.updateNote(note)
                view.findNavController().navigate(
                    R.id.action_updateNoteFragment_to_homeFragment
                )
            } else {
                activity?.toast("Введите заголовок заметки")
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.update_note_menu, menu)

        super.onCreateOptionsMenu(menu, inflater)

    }

    private fun deleteNote() {
        AlertDialog.Builder(activity).apply {
            setTitle("Удаление заметки")
            setMessage("Вы действительно хотите удалить заметку?")
            setPositiveButton("Удалить") { _, _ ->
                noteViewModel.deleteNote(currentNote)
                view?.findNavController()?.navigate(R.id.action_updateNoteFragment_to_homeFragment)
            }
            setNegativeButton("Отменить", null)
        }.create().show()


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_delete -> deleteNote()
        }
        return super.onOptionsItemSelected(item)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}