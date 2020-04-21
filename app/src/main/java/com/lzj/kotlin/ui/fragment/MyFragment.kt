package com.lzj.kotlin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TabHost
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.lzj.kotlin.R
import com.lzj.kotlin.adapter.TabLayoutStatePagerAdapter

/**
 * Description: 我的
 * Author: Administrator
 * CreateDate: 2020/4/8
 */
class MyFragment : Fragment() {
    var tabLayout: TabLayout? = null
    var mViewPager: ViewPager? = null
    var fragments: ArrayList<Fragment>?=null
    var titles :  ArrayList<String>?=null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_my, container, false)
        init(view);
        return  view
    }

    private fun init(view :View) {
        tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)
        mViewPager = view.findViewById<ViewPager>(R.id.viewPager)

        intTitle()
    }

    private fun intTitle() {
        fragments= ArrayList<Fragment>();
        titles= ArrayList<String>();

        for (i in 1..9){
            titles!!.add("科目"+i)
            fragments!!.add(NewFragment.newInstance("科目"+i,i))
            tabLayout!!.addTab(tabLayout!!.newTab().setText("科目"+i))
        }
        tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL
        tabLayout!!.setTabMode(TabLayout.MODE_SCROLLABLE) //1.MODE_SCROLLABLE模式
        val tabsAdapter  = TabLayoutStatePagerAdapter(childFragmentManager, fragments, titles as ArrayList<String>
        )
         mViewPager!!.adapter = tabsAdapter
         tabLayout!!.setupWithViewPager(mViewPager)


        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                mViewPager!!.currentItem = tab!!.position
            }

        })
        //设置默认
        val tab = tabLayout!!.getTabAt(2)
        tabLayout!!.selectTab(tab)
    }

    companion object {
        @Volatile
        var instance: MyFragment? = null
            get() {
                if (field == null) {
                    synchronized(MyFragment::class.java) {
                        if (field == null) {
                            field = MyFragment()
                        }
                    }
                }
                return field
            }
            private set

    }
}