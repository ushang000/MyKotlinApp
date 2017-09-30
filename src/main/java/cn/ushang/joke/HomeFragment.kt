package cn.ushang.joke

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.layout_hot.*
import kotlinx.android.synthetic.main.layout_hot.view.*
import org.jetbrains.anko.async
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.uiThread
import org.jsoup.Jsoup

/**
 * Created by ushang000 on 2017/9/27.
 */
 class HomeFragment(val category:String) : Fragment (){
    val user = ArrayList<User>()
    val adapter = JokeAdapter(user){
        val intent=Intent(activity,Main2Activity::class.java)
        intent.putExtra("href",it.href)
        intent.putExtra("img",it.img)
        intent.putExtra("name",it.name)
        startActivity(intent)
        toast("${it.name} Clicked")
    }
    constructor():this(String())


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view= inflater!!.inflate(R.layout.layout_hot,container,false)
        view.recycler.layoutManager=LinearLayoutManager(activity)
        view.recycler.addItemDecoration(DividerItemDecoration(activity,DividerItemDecoration.VERTICAL))
        view.recycler.adapter=adapter
        getHTML()
        return view
    }
    private fun getHTML() {
        async {
            for (item in 1..5) {
                val doc = Jsoup.connect("http://www.qiushibaike.com/$category/page/$item").get()
                val els_auth = doc.select(".author.clearfix")
                val image=els_auth.select("img")
                val els_content = doc.select("a.contentHerf")
                for (i in 0 until els_content.size) {
                    val href = els_content[i].attr("href")
                    val els_detail = els_content[i].select(".content")
                    val img=image[i].attr("src")
                    val name = els_auth.select("h2")[i].text()
                    val content = els_detail.text()
                    val u = User(content, name,img,href)
                    user.add(u)
                    //println(name + "  " + age)
                    uiThread {
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }
}