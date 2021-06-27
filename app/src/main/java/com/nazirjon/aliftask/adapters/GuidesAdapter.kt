package com.nazirjon.aliftask.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nazirjon.aliftask.R
import com.nazirjon.aliftask.data.models.Data


class GuidesAdapter(
    private val guideActions: GuideActions
) : RecyclerView.Adapter<GuidesAdapter.GuidesViewHolder>() {

    private var guides = mutableListOf<Data>()
    private lateinit var mCtx: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GuidesViewHolder {
        mCtx = parent.context
        val inflater = LayoutInflater.from(parent.context)
        val view: View =
            inflater.inflate(R.layout.item_layout, null, false)
        val lp = RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        view.layoutParams = lp
        return GuidesViewHolder(view)
    }

    fun setDataToAdapter(items: MutableList<Data>) {
        if(guides != items) {
            this.guides.addAll(items)
            notifyDataSetChanged()
        }
    }

    override fun onBindViewHolder(
        holder: GuidesViewHolder,
        position: Int
    ) {
        val guide: Data = guides[position]

        holder.name.text = guide.name
        holder.city.text = "City / State"
        holder.endDate.text = guide.endDate

        Glide.with(mCtx).load(guide.icon).centerCrop().into(holder.logo)
        holder.guide_layout.setOnClickListener {
            guideActions.openGuide(guide)
        }

    }

    override fun getItemCount(): Int {
        return guides.size
    }

    inner class GuidesViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        var name: TextView = view.findViewById(R.id.item_name)
        var city: TextView = view.findViewById(R.id.item_city)
        var endDate: TextView = view.findViewById(R.id.item_end_date)
        var logo: ImageView = view.findViewById(R.id.item_logo)
        var guide_layout: CardView = view.findViewById(R.id.guide_layout)
    }

    interface GuideActions {
        fun openGuide(guide: Data)
    }

}