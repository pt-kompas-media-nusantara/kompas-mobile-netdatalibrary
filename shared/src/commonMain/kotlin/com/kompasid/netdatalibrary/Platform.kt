package com.kompasid.netdatalibrary

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform