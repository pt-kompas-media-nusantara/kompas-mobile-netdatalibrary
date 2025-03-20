package com.kompasid.netdatalibrary

import kotlinx.coroutines.CoroutineScope

expect open class BaseVM() {

    val scope: CoroutineScope

}