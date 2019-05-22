package fr.epita.android.gameoflife

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class CellAdaptater(val context : Context, val data : MutableList<Cell>, private val onClickListener : View.OnClickListener) : RecyclerView.Adapter<CellAdaptater.ViewHolder>()  {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val rowView = LayoutInflater
            .from(context)
            .inflate(R.layout.cell, p0, false)

        rowView.setOnClickListener(onClickListener);

        val viewHolder = ViewHolder(rowView)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return data.size;
    }


    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val currentItem = data[p1]
        p0.cellView.setBackgroundResource(if (currentItem.isAlive) R.drawable.filledrect else R.drawable.contour)
        p0.itemView.tag = p1;
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cellView : View = itemView.findViewById(R.id.cell)
    }
}