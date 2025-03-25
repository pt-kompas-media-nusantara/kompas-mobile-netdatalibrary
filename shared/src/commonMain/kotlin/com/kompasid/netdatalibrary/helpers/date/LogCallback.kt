package com.kompasid.netdatalibrary.helpers.date

import com.kompasid.netdatalibrary.base.logger.Logger

inline fun <T> T.logged(tag: String = "", prefix: String = "Result"): T {
    Logger.debug { "$prefix: ${this}" }
    return this
}