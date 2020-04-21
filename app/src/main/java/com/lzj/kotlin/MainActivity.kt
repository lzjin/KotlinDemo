package com.lzj.kotlin

import android.os.Bundle
import android.util.TypedValue
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lzj.kotlin.ui.fragment.HomeFragment
import com.lzj.kotlin.ui.fragment.MyFragment
import com.lzj.kotlin.utils.FragmentUtil
import kotlinx.android.synthetic.main.activity_main.*


/**
 * 主页面
 */
class MainActivity : AppCompatActivity() {

    private val fragmentList = arrayListOf<Fragment>()

    var homeFragment: HomeFragment = HomeFragment.instance!!
    var myFragment: MyFragment = MyFragment.instance!!

    var indexFragment: Fragment = homeFragment
    var mExitTime :Long = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFragment()
        init()

    }
    fun initFragment() {
        fragmentList.clear()
        fragmentList.add(homeFragment)
        fragmentList.add(myFragment)
    }
    private fun init() {

        val navView = main_navigation
        navView.itemIconTintList = null //取消导航栏图标着色
        //默认
       // navView.selectedItemId=navView.menu.getItem(1).itemId
        //默认
         showFragment(homeFragment)

        navView.setOnNavigationItemSelectedListener(object : BottomNavigationView.OnNavigationItemSelectedListener{
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.navigation_home -> {
                        showFragment(homeFragment)
                        return true
                    }

                    R.id.navigation_personal -> {
                        showFragment(myFragment)
                        return true
                    }
                }
                return false
            }
        })
        adjustNavigationIcoSize(navView)
    }
    //调整底部导航栏图标大小
    private fun adjustNavigationIcoSize(navigation: BottomNavigationView) {
        val menuView = navigation.getChildAt(0) as BottomNavigationMenuView
        for (i in 0 until menuView.childCount) {
            val iconView: View = menuView.getChildAt(i).findViewById(com.google.android.material.R.id.icon)
            val layoutParams = iconView.layoutParams
            val displayMetrics = resources.displayMetrics
            layoutParams.height =
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20f, displayMetrics).toInt()
            layoutParams.width =
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20f, displayMetrics).toInt()
            iconView.layoutParams=layoutParams
        }
    }

    private fun showFragment( fragment : Fragment) {
        FragmentUtil.setContentFragment(
            R.id.main_fragment,
            this@MainActivity,
            fragment, indexFragment
        )
        indexFragment = fragment
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis().minus(mExitTime) <= 3000) {
                finish()
                // toast!!.cancel()
            } else {
                mExitTime = System.currentTimeMillis()
                //  toast = showToast("再按一次退出程序")
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    /**
     * 使用show、hide来管理fragment
     */
    private fun showFragment(position: Int) {
        if (fragmentList.size > 0) {
            val fragment: Fragment = fragmentList[position]
            if (indexFragment !== fragment) {
                val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                if (indexFragment != null) {
                    transaction.hide(indexFragment!!)
                }
                indexFragment = fragment
                if (!fragment.isAdded) {
                    transaction.add(R.id.main_fragment, fragment)
                } else {
                    transaction.show(fragment)
                }
                transaction.commit()
            }
        }
    }

}
