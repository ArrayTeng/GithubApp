package com.tengfei.github.model.repo

import com.tengfei.github.entity.Repository
import com.tengfei.github.entity.User
import com.tengfei.github.model.page.ListPage
import com.tengfei.github.network.service.RepositoryService
import com.tengfei.github.utils.format
import retrofit2.adapter.rxjava.GitHubPaging
import rx.Observable
import java.util.*

/**
 * @author tengfei
 * date 2019-08-25 22:19
 * email tengfeigo@outlook.com
 * description
 */

class RePoDataList(val user: User?) : ListPage<Repository>() {
    override fun getDate(page: Int): Observable<GitHubPaging<Repository>> {
        return if (user == null) {
            RepositoryService.listRepositoriesOfSearch(q = "pushed:<" + Date().format("yyyy-MM-dd"), page = page).map { it.paging }
        } else {
            RepositoryService.listRepositoriesOfUser(userName = user.login, page = page)

        }
    }
}