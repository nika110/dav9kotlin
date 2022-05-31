package com.zura.mysuperapp.adapters

import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zura.mysuperapp.R
import com.zura.mysuperapp.ResourceActivity
import com.zura.mysuperapp.api.dto.Resource

class ResourceRecyclerAdapter(private val resources: List<Resource>):RecyclerView.Adapter<ResourceRecyclerAdapter.ResourceViewHolder>(){

    class ResourceViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{

        init{
            itemView.setOnClickListener(this)
        }

        private val id: TextView = itemView.findViewById(R.id.id)
        private val name: TextView = itemView.findViewById(R.id.name)
        private val year: TextView = itemView.findViewById(R.id.year)
        private val color: TextView = itemView.findViewById(R.id.color)

        fun onBind(resource: Resource){
            id.text = resource.id.toString()
            name.text = resource.name
            year.text = resource.year.toString()
            color.text = resource.color
            color.setBackgroundColor(Color.parseColor(resource.color))
        }

        override fun onClick(clickedView: View?) {
            Log.d("zura", "Clicked")
            val context = itemView.context
            val intent = Intent(context, ResourceActivity::class.java)
            intent.putExtra("resource_id", id.text.toString().toInt())
            context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResourceViewHolder {
       val itemView =  LayoutInflater.from(parent.context).inflate(R.layout.item_resource, parent, false)
        return ResourceViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ResourceViewHolder, position: Int) {
        holder.onBind(resources[position])
    }

    override fun getItemCount() = resources.size
}