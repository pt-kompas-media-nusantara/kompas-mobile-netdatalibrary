package com.kompasid.netdatalibrary.core.data.loginEmail.models.local

interface ILoginEmailDataSource {
    suspend fun save(accessToken: String, refreshToken: String, isVerified: Boolean, deviceKeyId: String, isSocial: Boolean)
}