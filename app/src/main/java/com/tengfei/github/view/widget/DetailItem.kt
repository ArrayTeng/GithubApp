package com.tengfei.github.view.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.ToggleButton
import com.tengfei.github.R
import kotlinx.android.synthetic.main.detail_item.view.*
import rx.Observable
import kotlin.reflect.KProperty

/**
 * @author tengfei
 * date 2019-09-11 19:15
 * email tengfeigo@outlook.com
 * description
 */

typealias CheckEvent = (Boolean) -> Observable<Boolean>

class ObjectPropertyDelegate<T, R>(private val receiver: R, val getter: ( (R) -> T)? = null, val setter: ((R, T) -> Unit)? = null, defaultValue: T? = null) {
    private var value: T? = defaultValue

    operator fun getValue(ref: Any, property: KProperty<*>): T {
        return getter?.invoke(receiver) ?: value!!
    }

    operator fun setValue(ref: Any, property: KProperty<*>, value: T) {
        setter?.invoke(receiver, value)
        this.value = value
    }

}

class DetailItem
@JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : RelativeLayout(context, attrs, defStyle) {

    var title: CharSequence by ObjectPropertyDelegate(titleView, TextView::getText, TextView::setText, "")

    var content: CharSequence by ObjectPropertyDelegate(contentView, TextView::getText, TextView::setText, "")

    var icon by ObjectPropertyDelegate(iconView, null, ImageView::setImageResource, 0)

    private var operatoricon by ObjectPropertyDelegate(operatorIconView, null, ToggleButton::setBackgroundResource, 0)


    var isChecked by ObjectPropertyDelegate(operatorIconView, ToggleButton::isChecked, ToggleButton::setChecked, false)

    var checkEvent: CheckEvent? = null

    init {
        View.inflate(context, R.layout.detail_item, this)
        attrs?.let {
            val attrDetail = context.obtainStyledAttributes(it, R.styleable.DetailItem)
            title = attrDetail.getString(R.styleable.DetailItem_item_title) ?: ""
            content = attrDetail.getString(R.styleable.DetailItem_item_content) ?: ""
            icon = attrDetail.getResourceId(R.styleable.DetailItem_item_icon, 0)
            operatoricon = attrDetail.getResourceId(R.styleable.DetailItem_item_op_icon, 0)
            attrDetail.recycle()
        }

        setOnClickListener {
            checkEvent?.invoke(isChecked)?.subscribe({
                isChecked = it
            }, {
                it.printStackTrace()
            })
        }
    }


}

