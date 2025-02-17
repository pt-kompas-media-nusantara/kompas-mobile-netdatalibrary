//
//  AuthVMWrapper.swift
//  iosApp
//
//  Created by Kompas Digital on 21/01/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation
import KompasIdLibary
// import NetDataLibrary

@MainActor
class AuthVMWrapper: ObservableObject {
    private let authUseCase: AuthUseCase
    
    init() {
        self.authUseCase = KoinInjector().authUseCase
    }
    
    func loginAnonTwo() async throws {
        do {
            let result = try await authUseCase.loginAnonTwo()
            switch result {
            case let success as ResultsSuccess<KotlinPair<LoginAnonResInterceptor, LoginAnonResInterceptor>>:
                let firstInterceptor = success.data?.first
                let secondInterceptor = success.data?.second
                
                print("loginGuest : Success")
                print("First Interceptor: \(firstInterceptor)")
                print("Second Interceptor: \(secondInterceptor)")
                
            case let error as ResultsError<NetworkError>:
                let networkError = error.error
                print("loginGuest : Network Error: \(networkError.description)")
                
            default:
                print("loginGuest : Unhandled case")
            }
        } catch {
            // Handle unexpected errors
            print("loginGuest : Unexpected error: \(error.localizedDescription)")
        }
    }
    
    func loginGuestOne() async throws {
        do {
            let result = try await authUseCase.loginAnonOne()
            switch result {
            case let success as ResultsSuccess<LoginAnonResInterceptor>:
                let interceptor = success.data
                print("loginGuest : \(interceptor)")
                
            case let error as ResultsError<NetworkError>:
                let networkError = error.error
                print("loginGuest : Network Error: \(networkError.description)")
                
            default:
                print("loginGuest : Unhandled case")
            }
        } catch {
            // Handle unexpected errors
            print("loginGuest : Unexpected error: \(error.localizedDescription)")
        }
    }
    
    func loginGuest() async throws {
        do {
            let result = try await authUseCase.loginAnon()
            switch result {
            case let success as ResultsSuccess<Unit>:
                let interceptor = success.data
                print("loginGuest : \(interceptor)")
                
            case let error as ResultsError<NetworkError>:
                let networkError = error.error
                print("loginGuest : Network Error: \(networkError.description)")
                
            default:
                print("loginGuest : Unhandled case")
            }
        } catch {
            // Handle unexpected errors
            print("loginGuest : Unexpected error: \(error.localizedDescription)")
        }
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
            case let success as ResultsSuccess<KotlinPair<KotlinUnit, KotlinPair<UserDetailResInterceptor, UserMembershipHistoryResInterceptor>>>:
                let data = success.data
                let first = data?.first // KotlinUnit (bisa diabaikan jika tidak diperlukan)
                let second = data?.second
                
                let userDetail = second?.first
                let membershipHistory = second?.second
                
                print("Success:")
                print("User Detail: \(userDetail)")
                print("Membership History: \(membershipHistory)")
            case let error as ResultsError<NetworkError>:
                let networkError = error.error
                print("loginGuest : Network Error: \(networkError.description)")
                
            default:
                print("loginGuest : Unhandled case")
            }
        } catch {
            // Handle unexpected errors
            print("loginGuest : Unexpected error: \(error.localizedDescription)")
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

