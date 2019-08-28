package com.tengfei.github.view.common

import android.animation.ObjectAnimator
import android.support.annotation.LayoutRes
import android.support.v4.view.ViewCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.tengfei.github.R
import com.tengfei.github.utils.AdapterList
import kotlinx.android.synthetic.main.item_card.view.*
import org.jetbrains.anko.dip

/**
 * @author tengfei
 * date 2019-08-28 10:10
 * email tengfeigo@outlook.com
 * description
 */
abstract class CommonRecyclerAdapter<DataType>(@LayoutRes val itemLayoutResId: Int) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val CARD_TAP_DURATION: Long = 100
    }

    init {
        setHasStableIds(true)
    }

    var oldPosition: Int = -1

    val data = AdapterList<DataType>(this)


    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        LayoutInflater.from(itemView.context).inflate(itemLayoutResId, itemView.contentContainer)
        return CommonViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        onBindData(holder, data[position])

        holder.itemView.setOnClickListener {
            itemClick(holder.itemView, data[position])
        }

        holder.itemView.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    ViewCompat.animate(holder.itemView).scaleX(1.03F)
                            .scaleY(1.03F)
                            //holder.itemView.dip是 anko 里的方法，有时间看下 anko 里定义里哪些方法
                            .translationZ(holder.itemView.dip(10).toFloat())
                            .duration = CARD_TAP_DURATION
                }
                MotionEvent.ACTION_UP -> {

                }
                //当用户保持按下操作并从当前控件滑动到其它位置（可以理解为你的手指不在这个控件上的时候）会触发这个方法
                MotionEvent.ACTION_CANCEL -> {
                    ViewCompat.animate(holder.itemView).scaleX(1F)
                            .scaleY(1F)
                            //holder.itemView.dip是 anko 里的方法，有时间看下 anko 里定义里哪些方法
                            .translationZ(holder.itemView.dip(0).toFloat())
                            .duration = CARD_TAP_DURATION
                }

            }
            false
        }
    }

    abstract fun itemClick(itemView: View, dataType: DataType)

    abstract fun onBindData(holder: RecyclerView.ViewHolder, dataType: DataType)


    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        // holder.layoutPosition > oldPosition 表示是新的item，它必须得要是在下面出来的
        if (holder is CommonViewHolder && holder.layoutPosition > oldPosition) {
            addItemAnimation(holder.itemView)
            oldPosition = holder.layoutPosition
        }
    }

    private fun addItemAnimation(itemView: View) {
        ObjectAnimator.ofFloat(itemView, "translationY", 500F, 0F).setDuration(500).start()
    }


    class CommonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}