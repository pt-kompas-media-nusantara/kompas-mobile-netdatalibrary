package com.kompasid.netdatalibrary.helper.persistentStorage.example.SerializationNoArgModuleSettings.model

import kotlinx.serialization.Serializable


@Serializable
data class UserProfile(
    val name: String,
    val age: Int,
    val email: String
)

@Serializable
data class Address(
    val street: String,
    val city: String,
    val postalCode: String
)