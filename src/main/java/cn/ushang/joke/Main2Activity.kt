package cn.ushang.joke

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main2.*
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread
import org.jsoup.Jsoup

class Main2Activity : AppCompatActivity() {
   private val commentUser= ArrayList<CommentUser>()
   private val adapter=CommentAdapter(commentUser)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val intent=intent
        Picasso.with(this).load("https:"+intent.getStringExtra("img")).placeholder(R.mipmap.icon).into(img)
        detail_name.text=intent.getStringExtra("name")

        recyc.layoutManager=LinearLayoutManager(this)
        recyc.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyc.adapter=adapter
        recyc.isFocusable=false
        initDetailArticle(intent.getStringExtra("href"))
    }
   private fun initDetailArticle(href:String){
        async {
            val doc_detail = Jsoup.connect("http://www.qiushibaike.com" + href).get()
            val els_detail = doc_detail.select(".content")
            val detail_wrap=doc_detail.select(".comments-wrap")
            val detail_comments=detail_wrap.select(".comment-block.clearfix")
            val comments_image=detail_comments.select("img")

            for (j in 0 until comments_image.size){
                val comments_img=comments_image[j].attr("src")
                val comments_title=detail_comments[j].select("a.userlogin").text()
                val comments_content=detail_comments[j].select("span.body").text()
                val comments_floor=detail_comments[j].select("div.report").text()
                commentUser.add(CommentUser(comments_img,comments_title,comments_content,comments_floor))
            }
            val comment=detail_wrap.select("h3").text()
            val content=els_detail.text()
            val els_pic = doc_detail.select(".thumb img[src\$=jpg]")
            val pic = els_pic.attr("src")
            uiThread {
                detail_content.text=content
                comments.text=comment
                Picasso.with(this@Main2Activity).load("https:"+pic).into(picture)
                adapter.notifyDataSetChanged()
            }
        }

    }
}
