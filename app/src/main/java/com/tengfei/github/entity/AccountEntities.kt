package com.tengfei.github.entity

import com.tengfei.common.anno.PoKo
import com.tengfei.github.settings.Configs

/**
 * @author tengfei
 * date 2019/8/17 9:31 PM
 * email tengfeigo@outlook.com
 * description  PUT /authorizations/clients/:client_id/:fingerprint
 * 给特定的程序创建和生成鉴权
 */

@PoKo
data class AccountEntitiesRequest(var client_secret: String = Configs.Account.clientSecret,
                                  var scopes: List<String> = Configs.Account.SCOPES,
                                  var note: String = Configs.Account.note,
                                  var note_url: String = Configs.Account.noteUrl)


@PoKo
data class AccountEntitiesResponse(var id: String,
                                   var url: String,
                                   var scopes: List<String>,
                                   var token: String,
                                   var token_last_eight: String,
                                   var hashed_token: String,
                                   var app: App,
                                   var note: String,
                                   var note_url: String,
                                   var updated_at: String,
                                   var created_at: String,
                                   var fingerprint: String)


@PoKo
data class App(var url: String,
               var name: String,
               var client_id: String)
