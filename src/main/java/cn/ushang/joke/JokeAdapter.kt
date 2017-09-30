package cn.ushang.joke

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.joke_item.view.*

/**
 * Created by ushang000 on 2017/9/19.
 */
class JokeAdapter(val item : ArrayList<User>,val listener:(User)->Unit): Adapter<JokeAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v=LayoutInflater.from(parent.context).inflate(R.layout.joke_item,parent,false)
        return ViewHolder(v,parent.context)
    }
    override fun getItemCount(): Int = item.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int)=holder.bind(item[position],listener)
    class ViewHolder(itemView : View,val mContext:Context):RecyclerView.ViewHolder(itemView){
        fun bind(items:User,listener: (User) -> Unit)= with(itemView){
            content.text=items.content
            name.text=items.name
            Picasso.with(mContext).load("https:"+items.img).placeholder(R.mipmap.icon).into(image)
            setOnClickListener { listener(items) }
        }
    }
}