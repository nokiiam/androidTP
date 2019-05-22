package fr.epita.android.gameoflife

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class RowAdaptater(val context: Context
                   , val data : MutableList<MutableList<Cell>>
                   , val clickListenerCreator : (Int) -> View.OnClickListener)
    : RecyclerView.Adapter<RowAdaptater.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val gridView = LayoutInflater
                .from(context)
            .inflate(R.layout.row, p0, false)

        val viewHolder = RowAdaptater.ViewHolder(gridView)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return data.size //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val parent = data[p1];
        val childLayoutManager = LinearLayoutManager(p0.rowView.context, LinearLayoutManager.HORIZONTAL, false)
        p0.rowView.apply {
            layoutManager = childLayoutManager
            adapter = CellAdaptater(context, parent, clickListenerCreator(p1))
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rowView : RecyclerView = itemView.findViewById(R.id.cellRow)
    }
}