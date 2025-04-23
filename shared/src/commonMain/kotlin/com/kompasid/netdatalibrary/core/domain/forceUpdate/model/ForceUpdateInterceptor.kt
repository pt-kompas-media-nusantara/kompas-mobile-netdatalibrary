package com.kompasid.netdatalibrary.core.domain.forceUpdate.model

import com.kompasid.netdatalibrary.core.data.forceUpdate.dto.enums.ForceUpdateType

data class ForceUpdateInterceptor(
    val type: ForceUpdateType,
    val versionInfo: MinMaxVersionAppInterceptor
)