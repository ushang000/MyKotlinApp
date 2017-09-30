package cn.ushang.joke

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {
    private val titles = arrayOf("热门", "24小时", "热图", "文字", "穿越", "糗图", "新鲜")
    private val category = arrayOf("hot", "24hr", "imgrank", "text", "history", "pic", "textnew")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main)
        initTitleBar()
        val fragment = HomeFragment("hot")
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.content_main, fragment)
        transaction.commit()
    }

    private fun initTitleBar() {
        val wm = this.windowManager
        val dm = DisplayMetrics()
        wm.defaultDisplay.getMetrics(dm)
        val width = dm.widthPixels
        val params = LinearLayout.LayoutParams((width / 4.5).toInt(), ViewGroup.LayoutParams.MATCH_PARENT)
        for (item in 0 until titles.size) {
            val itemView = LayoutInflater.from(this).inflate(R.layout.activity_home, null) as TextView
            itemView.text = titles[item]
            itemView.layoutParams = params
            //itemView.textColor=R.color.white
            itemView.textSize = 16f
            itemView.setOnClickListener {
                for (i in 0 until line_top.childCount) {
                    val text = line_top.getChildAt(i) as TextView
                    text.textSize = 16f
                    text.textColor = 0x9fffffff.toInt()
                }
                val fragment = HomeFragment(category[item])
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.content_main, fragment)
                transaction.commit()
                itemView.textColor = 0xffffffff.toInt()
                itemView.textSize = 20f
            }
            line_top.addView(itemView)
        }
        val text = line_top.getChildAt(0) as TextView
        text.textColor = 0xffffffff.toInt()
        text.textSize = 20f
        line_top.invalidate()
    }

}
