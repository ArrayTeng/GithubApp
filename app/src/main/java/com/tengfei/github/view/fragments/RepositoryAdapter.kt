package com.tengfei.github.view.fragments

import android.support.v7.widget.RecyclerView
import android.view.View
import com.tengfei.github.R
import com.tengfei.github.entity.Repository
import com.tengfei.github.utils.loadWithGlide
import com.tengfei.github.utils.toKilo
import com.tengfei.github.view.common.CommonRecyclerAdapter

import kotlinx.android.synthetic.main.item_repo.view.*

/**
 * @author tengfei
 * date 2019-08-29 21:59
 * email tengfeigo@outlook.com
 * description
 */
class RepositoryAdapter : CommonRecyclerAdapter<Repository>(R.layout.item_repo) {
    override fun onBindData(holder: RecyclerView.ViewHolder, dataType: Repository) {
        holder.itemView.apply {
            avatarView.loadWithGlide(dataType.owner.avatar_url, dataType.name.first())
            repoNameView.text = dataType.name
            descriptionView.text = dataType.description
            langView.text = dataType.language ?: "UnKnown"
            starView.text = dataType.stargazers_count.toKilo()
            forkView.text = dataType.forks_count.toKilo()
        }
    }

    override fun itemClick(itemView: View, dataType: Repository) {
    }
}