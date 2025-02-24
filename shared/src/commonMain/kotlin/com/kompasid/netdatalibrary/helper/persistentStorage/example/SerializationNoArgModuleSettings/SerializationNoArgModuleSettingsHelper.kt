package com.kompasid.netdatalibrary.helper.persistentStorage.example.SerializationNoArgModuleSettings

import com.kompasid.netdatalibrary.BaseVM
import com.kompasid.netdatalibrary.helper.persistentStorage.example.SerializationNoArgModuleSettings.model.Address
import com.kompasid.netdatalibrary.helper.persistentStorage.example.SerializationNoArgModuleSettings.model.UserProfile
import com.russhwolf.settings.ExperimentalSettingsApi
import kotlinx.serialization.Serializable

import com.russhwolf.settings.Settings
import com.russhwolf.settings.contains
import com.russhwolf.settings.get
import com.russhwolf.settings.set
import com.russhwolf.settings.serialization.decodeValue
import com.russhwolf.settings.serialization.decodeValueOrNull
import com.russhwolf.settings.serialization.encodeValue
import com.russhwolf.settings.serialization.removeValue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json

class SerializationNoArgModuleSettingsHelper(private val settings: Settings) {

    fun saveUserProfile(userProfile: UserProfile) {
        settings.encodeValue(UserProfile.serializer(), "user_profile", userProfile)
    }

    fun getUserProfile(defaultValue: UserProfile = UserProfile("Guest", 0, "guest@example.com")): UserProfile {
        return settings.decodeValue(UserProfile.serializer(), "user_profile", defaultValue)
    }

    fun getUserProfileOrNull(): UserProfile? {
        return settings.decodeValueOrNull(UserProfile.serializer(), "user_profile")
    }


    fun removeUserProfile() {
        settings.removeValue(UserProfile.serializer(), "user_profile")
    }

    fun containsUserProfile(): Boolean {
        return settings.contains("user_profile")
    }
//    ======================================================
    // Fungsi untuk menyimpan List<Address>
    fun saveAddressList(addressList: List<Address>) {
        settings.encodeValue(ListSerializer(Address.serializer()), "address_list", addressList)
    }

    // Fungsi untuk mengambil List<Address>, dengan default empty list jika tidak ditemukan
    fun getAddressList(): List<Address> {
        return settings.decodeValue(ListSerializer(Address.serializer()), "address_list", emptyList())
    }

    // Fungsi untuk mengambil List<Address> yang bisa null
    fun getAddressListOrNull(): List<Address>? {
        return settings.decodeValueOrNull(ListSerializer(Address.serializer()), "address_list")
    }

    // Fungsi untuk menghapus List<Address>
    fun removeAddressList() {
        settings.removeValue(ListSerializer(Address.serializer()), "address_list")
    }

    // Fungsi untuk mengecek apakah ada data List<Address> di dalam Settings
    fun containsAddressList(): Boolean {
        return settings.contains("address_list")
    }
}



