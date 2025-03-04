//
//  AuthVMWrapper.swift
//  iosApp
//
//  Created by Kompas Digital on 21/01/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation
import KompasIdLibrary

@MainActor
class AuthVMWrapper: ObservableObject {
    private let authUseCase: AuthUseCase
    
    init() {
        self.authUseCase = KoinInjector().authUseCase
    }
    func loginByEmail(type: UserLoginType) async throws {
        var email = ""
        var password = ""
        var device = ""
        var deviceType = ""
        
        switch type {
        case .regonByEmail:
            email = "kompastesting16@yopmail.com"
            password = "kompas12345"
            device = "testKMP"
            deviceType = "testKMP"
        case .suberByEmail:
            email = "nur.irfan@kompas.com"
            password = "Nurirppankompas@28"
            device = "testKMP"
            deviceType = "testKMP"
        }
        
        do {
            let result = try await authUseCase.loginByEmail(
                request:
                    LoginEmailRequest(
                        email: email,
                        password: password,
                        device: device,
                        deviceType: deviceType
                    )
            )
            switch result {
            case let success as ResultsSuccess<KotlinPair<KotlinUnit, KotlinPair<UserDetailResInterceptor, UserHistoryMembershipResInterceptor>>>:
                let data = success.data
                let first = data?.first
                let second = data?.second
                
                let userDetail = second?.first
                let membershipHistory = second?.second
                
                print("API Success:")
                print("API User Detail: \(userDetail)")
                print("API Membership History: \(membershipHistory)")
            case let error as ResultsError<NetworkError>:
                let networkError = error.error
                print("API loginByEmail : NetworkError: \(networkError.description)")
                
            default:
                print("API loginByEmail : default")
            }
        } catch {
            print("API loginByEmail : \(error.localizedDescription)")
        }
    }
    
    func logout() async throws {
        do {
            let result = try await authUseCase.logout()
            switch result {
            case let success as ResultsSuccess<KotlinUnit>:
                print("API logout")
            case let error as ResultsError<NetworkError>:
                let networkError = error.error
                print("API logout : \(networkError.description)")
                
            default:
                print("API default logout")
            }
        } catch {
            print("API catch logout : \(error.localizedDescription)")
        }
    }
}

enum UserLoginType {
    case regonByEmail
    case suberByEmail
}

struct AccountModel {
    let menuIcon: String
    let title: String
    let desc: String
    let navigation: AccountNavigationType
}

enum AccountNavigationType {
    case nothing
    case login
    case register
    case manageAccount
    case subscription
    case bookmark
    case reward
    case settings
    case contactUs
    case qna
    case aboutApp
    case aboutHarianKompas
}

