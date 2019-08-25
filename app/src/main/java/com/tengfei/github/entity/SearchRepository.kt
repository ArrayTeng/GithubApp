package com.tengfei.github.entity

import com.tengfei.common.anno.PoKo

/**
 * @author tengfei
 * date 2019-08-25 18:49
 * email tengfeigo@outlook.com
 * description
 */
@PoKo
data class SearchRepository(val total_count: Int,
                            val incomplete_results: Boolean,
                            val items: List<Repository>) : PagingWrapper<Repository>() {
    override fun getElements(): List<Repository> {
        return items
    }
}








