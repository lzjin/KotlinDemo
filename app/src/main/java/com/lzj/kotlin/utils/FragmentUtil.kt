package com.lzj.kotlin.utils

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/**
 * Fragment  切换
 */
object FragmentUtil {
    fun setChildContentFragment(
        id: Int,
        context: Fragment,
        show: Fragment,
        hide: Fragment
    ) {
        if (show === hide) {
            return
        }
        //		if (first_fragment != show) {
//			first_fragment = show;
//		}
        val fragmentManager =
            context.childFragmentManager
        val fragmentTransaction =
            fragmentManager.beginTransaction()
        if (show.isAdded) {
            fragmentTransaction.show(show)
            fragmentTransaction.hide(hide)
        } else {
            fragmentTransaction.hide(hide)
            fragmentTransaction.add(id, show)
        }
        fragmentTransaction.commit()
    }

    fun addChildFragment(
        id: Int,
        context: Fragment,
        fragment: Fragment?
    ) {
        val fragmentManager =
            context.childFragmentManager
        val fragmentTransaction =
            fragmentManager.beginTransaction()
        fragmentTransaction.add(id, fragment!!)
        fragmentTransaction.commit()
    }

    fun setContentFragment(
        id: Int,
        context: AppCompatActivity,
        show: Fragment,
        hide: Fragment
    ) {
        if (show === hide) {
            return
        }
        //		if (first_fragment != show) {
//			first_fragment = show;
//		}
        val fragmentManager: FragmentManager =
            context.getSupportFragmentManager()
        val fragmentTransaction =
            fragmentManager.beginTransaction()
        //		fragmentTransaction.setCustomAnimations(R.anim.alpha_anim_show, R.anim.alpha_anim_hide);
        if (show.isAdded) {
            fragmentTransaction.show(show)
            fragmentTransaction.hide(hide)
        } else {
            fragmentTransaction.hide(hide)
            fragmentTransaction.add(id, show)
        }
        fragmentTransaction.commitAllowingStateLoss()
    }

    fun addFragment(
        id: Int,
        context: AppCompatActivity,
        fragment: Fragment
    ) {
        if (!fragment.isAdded) {
            val fragmentManager: FragmentManager =
                context.getSupportFragmentManager()
            val fragmentTransaction =
                fragmentManager.beginTransaction()
            fragmentTransaction.add(id, fragment)
            fragmentTransaction.commitAllowingStateLoss()
        }
    }

    fun replaceFragment(
        id: Int,
        context: AppCompatActivity,
        fragment: Fragment?
    ) {
        val fragmentManager: FragmentManager =
            context.getSupportFragmentManager()
        val fragmentTransaction =
            fragmentManager.beginTransaction()
        fragmentTransaction.replace(id, fragment!!)
        fragmentTransaction.commit()
    }
}