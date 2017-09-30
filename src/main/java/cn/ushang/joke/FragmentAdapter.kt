package cn.ushang.joke

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by ushang000 on 2017/9/27.
 */
class FragmentAdapter (fm : FragmentManager,private var mFragments : MutableList<Fragment>):FragmentPagerAdapter(fm){
    override fun getItem(position: Int): Fragment=mFragments[position]

    override fun getCount(): Int= mFragments.size

}