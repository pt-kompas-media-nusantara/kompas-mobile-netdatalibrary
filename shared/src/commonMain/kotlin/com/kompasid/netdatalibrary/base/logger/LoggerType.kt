package com.kompasid.netdatalibrary.base.logger

// LoggerType untuk kategori log
enum class LoggerType(val label: String) {
    INFO("INFO"),
    ERROR("ERROR"),
    WARNING("WARNING"),
    DEBUG("DEBUG"),
    NOTIFICATION("NOTIFICATION"),
    URL("URL"),
    RESPONSE("RESPONSE"),
    DEBUG_TRACKER("EVENT TRACKER")
}

