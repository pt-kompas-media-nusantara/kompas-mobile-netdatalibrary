package com.kompasid.netdatalibrary.core.domain.account.model

import com.kompasid.netdatalibrary.core.domain.account.enums.AccountNavigationType

data class AccountModel(
    val menuIcon: String,
    val title: String,
    val desc: String,
    val navigation: AccountNavigationType
)

