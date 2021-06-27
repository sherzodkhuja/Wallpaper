package com.example.retrofit.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.retrofit.models.Category

class ViewPagerAdapter(
    fm: FragmentManager,
    categoryList: List<Category>
) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val categoryList: List<Category>
    override fun getItem(position: Int): Fragment {
        return com.example.retrofit.Category.newInstance(categoryList[position])
    }

    override fun getCount(): Int {
        return categoryList.size
    }

    init {
        this.categoryList = categoryList
    }


}