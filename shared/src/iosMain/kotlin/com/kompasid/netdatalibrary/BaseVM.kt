package com.kompasid.netdatalibrary

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.cancel

actual open class BaseVM {

    actual val scope: CoroutineScope = CoroutineScope(Dispatchers.IO)

    fun clear() {
        scope.cancel()
    }
}