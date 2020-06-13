package com.formationandroid.projectmemokotlin.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.formationandroid.projectmemokotlin.R
import com.formationandroid.projectmemokotlin.notes.adapter.NoteAdapter
import com.formationandroid.projectmemokotlin.notes.helpers.ItemTouchHelperCallback
import com.formationandroid.projectmemokotlin.notes.helpers.NotesDatabaseHelper
import com.formationandroid.projectmemokotlin.notes.pojos.NoteDTO
import com.formationandroid.projectmemokotlin.repository.MainRepository
import com.formationandroid.projectmemokotlin.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var noteAdapter: NoteAdapter
    private var listNotes: MutableList<NoteDTO>? = ArrayList()
    private lateinit var itemTouchHelper: ItemTouchHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.init(MainRepository())
        listNotes = mainViewModel.getLiveDataMemo()?.toMutableList()

        noteAdapter = NoteAdapter(listNotes!!, this)

        myrecyclerview.setHasFixedSize(true)
        myrecyclerview.layoutManager = LinearLayoutManager(this)
        itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(noteAdapter))
        itemTouchHelper.attachToRecyclerView(myrecyclerview)
        myrecyclerview.adapter = noteAdapter
    }

    fun insertMemo(view: View) {
        if (inputName.text.toString().isNotEmpty() && inputDescription.text.toString().isNotEmpty()) {
            val n = NoteDTO(
                inputName!!.text.toString(),
                inputDescription!!.text.toString())
            listNotes?.add(n)
            mainViewModel.insertNote(n)
            myrecyclerview.adapter?.notifyDataSetChanged()
            Toast.makeText(view.context, "Mémo ajouté !", Toast.LENGTH_SHORT).show()
            inputName.text.clear()
            inputDescription.text.clear()
        } else {
            when {
                inputName.text.toString().isEmpty() && inputDescription.text.toString().isEmpty()-> {
                    Toast.makeText(view.context, "Memo vide !", Toast.LENGTH_SHORT).show()
                }
                inputName.text.toString().isEmpty() -> {
                    Toast.makeText(view.context, "Titre vide !", Toast.LENGTH_SHORT).show()
                }
                inputDescription.text.toString().isEmpty() -> {
                    Toast.makeText(view.context, "Description vide !", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun deleteAll(view: View) {
        val position = 0
        var nbLoop = 0
        while (position != noteAdapter.itemCount) {
            NotesDatabaseHelper.getDatabase(view.context).noteDAO().delete(listNotes!![position])
            listNotes!!.removeAt(position)
            noteAdapter.notifyItemRemoved(position)
            nbLoop++
        }
        when (nbLoop) {
            0 -> {
                Toast.makeText(view.context, "Aucun mémo !", Toast.LENGTH_SHORT).show()
            }
            1 -> {
                Toast.makeText(view.context, "Mémo supprimé !", Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(
                    view.context,
                    "$nbLoop Mémos supprimés !",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    fun showHelp(view: View) {
            val intent = Intent(view.context, HelpActivity::class.java)
            view.context.startActivity(intent)
    }
}