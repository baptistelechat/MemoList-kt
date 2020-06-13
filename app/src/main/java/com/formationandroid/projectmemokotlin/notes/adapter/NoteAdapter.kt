package com.formationandroid.projectmemokotlin.notes.adapter

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.formationandroid.projectmemokotlin.R
import com.formationandroid.projectmemokotlin.activities.DetailActivity
import com.formationandroid.projectmemokotlin.fragment.DetailFragment
import com.formationandroid.projectmemokotlin.notes.pojos.NoteDTO
import com.formationandroid.projectmemokotlin.notes.adapter.NoteAdapter.NoteViewHolder
import com.formationandroid.projectmemokotlin.notes.helpers.NotesDatabaseHelper
import kotlinx.android.synthetic.main.item_note.view.*

class NoteAdapter(listeNotes: MutableList<NoteDTO>, main: AppCompatActivity) : RecyclerView.Adapter<NoteViewHolder?>() {
    private var listeNotes: MutableList<NoteDTO> = ArrayList()
    private val main: AppCompatActivity
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewNote: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(viewNote)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(listeNotes[position])
    }

    override fun getItemCount(): Int {
        return listeNotes.size
    }

    fun onItemDismiss(view: RecyclerView.ViewHolder) {
        if (view.adapterPosition > -1) {
            NotesDatabaseHelper.getDatabase(view.itemView.context).noteDAO().delete(listeNotes[view.adapterPosition])
            listeNotes.removeAt(view.adapterPosition)
            notifyItemRemoved(view.adapterPosition)
        }
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener { view ->
                val note: NoteDTO = listeNotes[adapterPosition]
                val preferences: SharedPreferences =
                    androidx.preference.PreferenceManager.getDefaultSharedPreferences(view.context)
                val editor = preferences.edit()
                editor.putInt("last", adapterPosition)
                editor.apply()

                if (main.findViewById<View?>(R.id.large_detail) == null) {
                    val intent = Intent(view.context, DetailActivity::class.java)
                    intent.putExtra("detail", note.intitule)
                    intent.putExtra("description", note.description)
                    view.context.startActivity(intent)
                } else {
                    // fragment :
                    val fragment = DetailFragment()
                    val bundle = Bundle()
                    bundle.putString(DetailFragment.EXTRA_NAME, note.intitule)
                    bundle.putString(DetailFragment.EXTRA_DESCRIPTION, note.description)
                    fragment.arguments = bundle

                    // fragment manager :
                    val fragmentManager =
                        main.supportFragmentManager
                    // transaction :
                    val fragmentTransaction =
                        fragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.large_detail, fragment, "exemple2")
                    fragmentTransaction.commit()
                }
            }
        }

        fun bind(note:NoteDTO) = with(itemView){
            LabelMemo.text = note.intitule
        }
    }

    init {
        this.listeNotes = listeNotes
        this.main = main
    }
}