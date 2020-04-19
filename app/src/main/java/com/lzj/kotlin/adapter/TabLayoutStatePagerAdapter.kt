package com.lzj.kotlin.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter

/**
 * 重用Fragment建议使用此类
 * (常用于动态创建Tab+Fragment,首页Fragment写死的不利于使用此类)
 */
class TabLayoutStatePagerAdapter(
    fm: FragmentManager?,
    private val fragmentList: List<Fragment>?,
    private val titleList: List<String>
) : FragmentStatePagerAdapter(fm!!) {
    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
        // 返回发生改变，让系统重新加载
// 系统默认返回的是     POSITION_UNCHANGED，未改变
    }

    /**
     * 得到每个页面
     */
    override fun getItem(position: Int): Fragment {
        return fragmentList?.get(position)!!
    }

    /**
     * 每个页面的title
     */
    override fun getPageTitle(position: Int): CharSequence? {
        return titleList[position]
    }

    /**
     * 页面的总个数
     */
    override fun getCount(): Int {
        return fragmentList?.size ?: 0
    }

}