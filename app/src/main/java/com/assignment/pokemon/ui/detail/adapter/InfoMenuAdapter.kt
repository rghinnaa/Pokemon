package com.assignment.pokemon.ui.detail.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.assignment.pokemon.ui.detail.BaseInfoFragment
import com.assignment.pokemon.ui.detail.BaseStatFragment

class InfoMenuAdapter(fragment: Fragment, dataDetail: String, dataSpecies: String) :
    FragmentStateAdapter(fragment) {

    private val pages = listOf(
        BaseInfoFragment.getInstance(dataDetail, dataSpecies),
        BaseStatFragment.getInstance(dataDetail)
    )

    override fun getItemCount(): Int {
        return pages.size
    }

    override fun createFragment(position: Int): Fragment {
        return pages[position]
    }

    private val pagesHash = pages.map { it.hashCode().toLong() }

    override fun getItemId(position: Int): Long = pages[position].hashCode().toLong()

    override fun containsItem(itemId: Long) = pagesHash.contains(itemId)
}