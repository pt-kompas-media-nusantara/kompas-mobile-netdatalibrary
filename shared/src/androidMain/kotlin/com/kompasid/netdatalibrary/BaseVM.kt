package com.kompasid.netdatalibrary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

actual open class BaseVM: ViewModel() {

    actual val scope = viewModelScope
}