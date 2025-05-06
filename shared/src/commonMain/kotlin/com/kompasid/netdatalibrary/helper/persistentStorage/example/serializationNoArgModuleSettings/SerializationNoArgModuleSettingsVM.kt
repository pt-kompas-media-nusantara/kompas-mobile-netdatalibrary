package com.kompasid.netdatalibrary.helper.persistentStorage.example.serializationNoArgModuleSettings

import com.kompasid.netdatalibrary.BaseVM
import com.kompasid.netdatalibrary.helper.persistentStorage.example.serializationNoArgModuleSettings.model.Address
import com.kompasid.netdatalibrary.helper.persistentStorage.example.serializationNoArgModuleSettings.model.UserProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SerializationNoArgModuleSettingsVM(private val settingsDataSource: SerializationNoArgModuleSettingsHelper) : BaseVM() {

    private val _userProfile = MutableStateFlow(settingsDataSource.getUserProfile())
    val userProfile = _userProfile.asStateFlow()

    fun updateUserProfile(newProfile: UserProfile) {
        settingsDataSource.saveUserProfile(newProfile)
        _userProfile.value = newProfile
    }

    fun removeUserProfile() {
        settingsDataSource.removeUserProfile()
        _userProfile.value = UserProfile("Guest", 0, "guest@example.com") // Set default
    }



    private val _addressList = MutableStateFlow(settingsDataSource.getAddressList())
    val addressList = _addressList.asStateFlow()

    fun updateAddressList(newList: List<Address>) {
        settingsDataSource.saveAddressList(newList)
        _addressList.value = newList
    }

    fun removeAddressList() {
        settingsDataSource.removeAddressList()
        _addressList.value = emptyList() // Set ke empty list
    }
}