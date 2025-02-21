package com.kompasid.netdatalibrary

import com.kompasid.netdatalibrary.netData.domain.trackerDomain.enums.OpenFromEntryPoint

expect class Platform {
    val osName: String
    val osVersion: String
    val deviceModel: String
    val density: Int
    suspend fun logSystemInfo()
}

