package uz.itschool.api

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(var list: MutableList<Item>): RecyclerView.Adapter<Adapter.MyHolder>() {
    class MyHolder(itemView:View): RecyclerView.ViewHolder(itemView) {
            var img = itemView.findViewById<ImageView>(R.id.img)
            var title = itemView.findViewById<TextView>(R.id.title)
            var category = itemView.findViewById<TextView>(R.id.category)
            var published = itemView.findViewById<TextView>(R.id.publishedAt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return MyHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
//        Glide.with(getApplicationContext())
//            .load(list[position].image)
//            .into(holder.img);

        holder.title.text = list[position].title
        holder.category.text = list[position].category
        holder.published.text = list[position].publishedAt
    }

}