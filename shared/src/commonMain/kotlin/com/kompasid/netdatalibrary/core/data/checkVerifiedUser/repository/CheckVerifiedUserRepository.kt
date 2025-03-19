package com.kompasid.netdatalibrary.core.data.checkVerifiedUser.repository

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.checkVerifiedUser.dto.interceptor.CheckVerifiedUserResInterceptor
import com.kompasid.netdatalibrary.core.data.checkVerifiedUser.dto.request.CheckVerifiedUserRequest
import com.kompasid.netdatalibrary.core.data.checkVerifiedUser.network.CheckVerifiedUserApiService
import com.kompasid.netdatalibrary.core.data.checkVerifiedUser.dataSource.CheckVerifiedUserDataSource
import com.kompasid.netdatalibrary.core.data.checkVerifiedUser.mappers.toInterceptor
import com.kompasid.netdatalibrary.core.domain.personalInfo.interceptor.PersonalInfoInterceptor

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


class CheckVerifiedUserRepository(
    private val checkVerifiedUserApiService: CheckVerifiedUserApiService,
    private val checkVerifiedUserDataSource: CheckVerifiedUserDataSource,
    private val personalInfoState: PersonalInfoState
) : ICheckVerifiedUserRepository {

    suspend fun postCheckVerifiedUser(): Results<CheckVerifiedUserResInterceptor, NetworkError> =
        runCatching {
            checkVerifiedUserApiService.postCheckVerifiedUser()
        }.fold(
            onSuccess = { result ->
                when (result) {
                    is ApiResults.Success -> {
                        val resultInterceptor = result.data.toInterceptor()

                        coroutineScope {
                            launch {
                                runCatching {
                                    checkVerifiedUserDataSource.save(resultInterceptor)
                                }
                            }
                            launch {
                                runCatching {
                                    personalInfoState.updatePersonalInfo(
                                        PersonalInfoInterceptor(
                                            checkVerifiedUser = resultInterceptor
                                        )
                                    )
                                }
                            }
                        }

                        Results.Success(resultInterceptor)
                    }

                    is ApiResults.Error -> Results.Error(result.error)
                }
            },
            onFailure = { exception ->
                Results.Error(NetworkError.Error(exception))
            }
        )
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
