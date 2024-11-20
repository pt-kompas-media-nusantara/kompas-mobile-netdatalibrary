package com.kompasid.netdata

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform