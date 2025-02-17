package com.kompasid.netdatalibrary.core.data.myRubriks.dataSource

import com.kompasid.netdatalibrary.base.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.base.persistentStorage.SettingsDataSource
import com.kompasid.netdatalibrary.core.data.myRubriks.dto.interceptor.MyRubriksResInterceptor


class MyRubriksDataSource(
    private val settingsDataSource: SettingsDataSource
) {

    fun save(data: List<MyRubriksResInterceptor>) {
        if (data.isEmpty()) return

        val result = data.filter { it.isChoosen }

        settingsDataSource.save(KeySettingsType.MY_RUBRIKS, result)
    }
}