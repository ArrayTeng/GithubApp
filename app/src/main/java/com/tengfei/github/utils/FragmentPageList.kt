package com.tengfei.github.utils

import com.tengfei.github.view.common.FragmentPagerAdapter
import java.util.function.UnaryOperator

/**
 * @author tengfei
 * date 2019-09-01 16:10
 * email tengfeigo@outlook.com
 * description
 */
class FragmentPageList<T>(val adapter: FragmentPagerAdapter) : ArrayList<T>() {

    override fun addAll(elements: Collection<T>): Boolean {
        return super.addAll(elements).apply {
            adapter.notifyDataSetChanged()
        }
    }

    override fun addAll(index: Int, elements: Collection<T>): Boolean {
        return super.addAll(index, elements).apply {
            adapter.notifyDataSetChanged()
        }
    }

    override fun clear() {
        super.clear().apply {
            adapter.notifyDataSetChanged()
        }
    }

    override fun replaceAll(operator: UnaryOperator<T>) {
        super.replaceAll(operator).apply {
            adapter.notifyDataSetChanged()
        }
    }

    override fun removeAll(elements: Collection<T>): Boolean {
        return super.removeAll(elements).apply {
            adapter.notifyDataSetChanged()
        }
    }

    override fun add(element: T): Boolean {
        return super.add(element).apply {
            adapter.notifyDataSetChanged()
        }
    }

    override fun add(index: Int, element: T) {
        super.add(index, element).apply {
            adapter.notifyDataSetChanged()
        }
    }

    override fun removeRange(fromIndex: Int, toIndex: Int) {
        super.removeRange(fromIndex, toIndex).apply {
            adapter.notifyDataSetChanged()
        }
    }

    override fun removeAt(index: Int): T {
        return super.removeAt(index).apply {
            adapter.notifyDataSetChanged()
        }
    }

    override fun remove(element: T): Boolean {
        return super.remove(element).apply {
            adapter.notifyDataSetChanged()
        }
    }
}