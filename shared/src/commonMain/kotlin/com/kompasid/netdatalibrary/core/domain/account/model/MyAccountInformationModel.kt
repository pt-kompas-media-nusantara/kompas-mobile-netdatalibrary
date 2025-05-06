package com.kompasid.netdatalibrary.core.domain.account.model

data class MyAccountInformationModel(
    val idUserName: String,
    val firstName: String,
    val lastName: String,
    val dateExpired: String,
    val stateMembership: String,
)