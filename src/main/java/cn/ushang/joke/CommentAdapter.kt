package cn.ushang.joke

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.comment_item.view.*
import kotlinx.android.synthetic.main.joke_item.view.*

/**
 * Created by ushang000 on 2017/9/26.
 */
class CommentAdapter(val item : ArrayList<CommentUser>): RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.comment_item,parent,false)
        return ViewHolder(v,parent.context)
    }
    override fun getItemCount(): Int = item.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int)=holder.bind(item[position])
    class ViewHolder(itemView : View, val mContext: Context):RecyclerView.ViewHolder(itemView){
        fun bind(items:CommentUser)= with(itemView){
            comment_content.text=items.commentContent
            comment_name.text=items.commentName
            floor.text=items.floor+"楼"
            Picasso.with(mContext).load("https:"+items.commentImg).placeholder(R.mipmap.icon).into(comment_image)
        }
    }
}