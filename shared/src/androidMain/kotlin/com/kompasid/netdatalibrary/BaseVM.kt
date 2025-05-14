package com.kompasid.netdatalibrary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancel

actual open class BaseVM: ViewModel() {

    actual val scope = viewModelScope

}
