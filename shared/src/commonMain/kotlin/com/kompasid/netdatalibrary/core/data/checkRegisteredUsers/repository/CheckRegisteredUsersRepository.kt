package com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.repository

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.dto.interceptor.CheckRegisteredUsersResInterceptor
import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.network.CheckRegisteredUsersApiService
import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.dataSource.CheckVerifiedUserDataSource
import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.mappers.toInterceptor


class CheckRegisteredUsersRepository(
    private val checkRegisteredUsersApiService: CheckRegisteredUsersApiService,
    private val checkVerifiedUserDataSource: CheckVerifiedUserDataSource,
) : ICheckRegisteredUsersRepository {

    suspend fun checkRegisteredUsers(value: String): Results<CheckRegisteredUsersResInterceptor, NetworkError> {
        return try {
            when (val result = checkRegisteredUsersApiService.checkRegisteredUsers(value)) {
                is ApiResults.Success -> {
                    val resultInterceptor = result.data.toInterceptor(value)

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
