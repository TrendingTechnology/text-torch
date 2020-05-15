package com.mileskrell.texttorch.intro

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.mileskrell.texttorch.intro.IntroViewModel.PAGE.*

class IntroPagerAdapter(val introViewModel: IntroViewModel, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val pages = mutableListOf(
        IntroPage1(),
        IntroPage2()
    ).apply {
        if (introViewModel.lastPageVisible.ordinal >= ANALYTICS.ordinal) add(IntroPage3())
        if (introViewModel.lastPageVisible.ordinal >= ENTER_APP.ordinal) add(IntroPage4())
    }

    fun addAnalyticsPage() {
        if (introViewModel.lastPageVisible.ordinal < ANALYTICS.ordinal) {
            introViewModel.lastPageVisible = ANALYTICS
            pages.add(IntroPage3())
            notifyDataSetChanged()
        }
    }

    fun addEnterAppPage() {
        if (introViewModel.lastPageVisible.ordinal < ENTER_APP.ordinal) {
            introViewModel.lastPageVisible = ENTER_APP
            pages.add(IntroPage4())
            notifyDataSetChanged()
        }
    }

    override fun getItem(position: Int) = pages[position]

    override fun getCount() = pages.size

    /**
     * Fix intro fragment references after configuration changes.
     *
     * See https://stackoverflow.com/a/17629575
     */
    // TODO: Hold on, I'm actually not sure if I need this. Figure out what's going on here.
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        return super.instantiateItem(container, position).also {
            pages[position] = it as Fragment
        }
    }
}