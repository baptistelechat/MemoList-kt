package com.formationandroid.projectmemokotlin.notes.helpers

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.RIGHT
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.formationandroid.projectmemokotlin.notes.adapter.NoteAdapter

class ItemTouchHelperCallback(adapter: NoteAdapter) : ItemTouchHelper.Callback() {
    private val myAdapter: NoteAdapter = adapter
    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: ViewHolder): Int {
        val dragFlagsRight = RIGHT
        return makeMovementFlags(
            dragFlagsRight,
            dragFlagsRight
        )
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: ViewHolder,
        target: ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
        myAdapter.onItemDismiss(viewHolder)
        myAdapter.notifyDataSetChanged()
    }

}