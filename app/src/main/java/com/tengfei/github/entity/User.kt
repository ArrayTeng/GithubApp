package com.tengfei.github.entity

import android.os.Parcelable
import com.tengfei.common.anno.PoKo
import kotlinx.android.parcel.Parcelize

/**
 * @author tengfei
 * date 2019/8/20 12:21 PM
 * email tengfeigo@outlook.com
 * description 定义鉴权User response
 */
@PoKo
@Parcelize
data class User(var login: String,
                var id: Int,
                var node_id: String,
                var avatar_url: String,
                var gravatar_id: String,
                var url: String,
                var html_url: String,
                var followers_url: String,
                var following_url: String,
                var gists_url: String,
                var starred_url: String,
                var subscriptions_url: String,
                var organizations_url: String,
                var repos_url: String,
                var events_url: String,
                var received_events_url: String,
                var type: String,
                var site_admin: Boolean,
                var name: String,
                var company: String,
                var blog:String,
                var location: String,
                var email: String,
                var hireable: Boolean,
                var bio: String,
                var public_repos: Int,
                var public_gists: Int,
                var followers: Int,
                var following: Int,
                var created_at: String,
                var updated_at: String,
                var private_gists: Int,
                var total_private_repos: Int,
                var owned_private_repos: Int,
                var disk_usage: Int,
                var collaborators: Int,
                var two_factor_authentication: Boolean,
                var plan: Plan
) : Parcelable

@PoKo
@Parcelize
data class Plan(var name: String,
                var space: Int,
                var private_repos: Int,
                var collaborators: Int) : Parcelable

