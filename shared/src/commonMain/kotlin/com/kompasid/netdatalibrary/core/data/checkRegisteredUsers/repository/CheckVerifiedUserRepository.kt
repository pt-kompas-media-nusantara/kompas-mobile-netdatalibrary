package com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.repository

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.dto.interceptor.CheckVerifiedUserResInterceptor
import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.network.CheckRegisteredUsersApiService
import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.dataSource.CheckVerifiedUserDataSource
import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.mappers.toInterceptor

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


class CheckVerifiedUserRepository(
    private val checkRegisteredUsersApiService: CheckRegisteredUsersApiService,
    private val checkVerifiedUserDataSource: CheckVerifiedUserDataSource,
) : ICheckVerifiedUserRepository {

    suspend fun checkRegisteredUsers(value: String): Results<CheckVerifiedUserResInterceptor, NetworkError> {
        return try {
            when (val result = checkRegisteredUsersApiService.checkRegisteredUsers(value)) {
                is ApiResults.Success -> {
                    val resultInterceptor = result.data.toInterceptor()

                    checkVerifiedUserDataSource.save(resultInterceptor)

                    Results.Success(resultInterceptor)
                }

                is ApiResults.Error -> Results.Error(result.error)
            }
        } catch (exception: Exception) {
            Results.Error(NetworkError.Error(exception))
        }
    }

}
/*
static func getRegisteredOn(from data: [String]?) -> [RegisteredType] {
    var registeredOn: [RegisteredType] = []
    if let registeredOnData = data {
        for method in registeredOnData {
            switch method {
                case "google":
                registeredOn.append(RegisteredType.google)
                case "apple":
                registeredOn.append(RegisteredType.apple)
                case "phoneNumber":
                registeredOn.append(RegisteredType.phoneNumber)
                case "email":
                registeredOn.append(RegisteredType.email)
                default:
                break
            }
        }
    }

    return registeredOn
}

static func getRegisteredOn(from registeredBy: String) -> [RegisteredType] {
        var registeredOn: [RegisteredType] = []
        switch registeredBy {
        case "google":
            registeredOn.append(RegisteredType.google)
        case "apple":
            registeredOn.append(RegisteredType.apple)
        case "phoneNumber":
            registeredOn.append(RegisteredType.phoneNumber)
        case "email":
            registeredOn.append(RegisteredType.email)
        default:
            break
        }
        return registeredOn
    }

*/
