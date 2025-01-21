//
//  AuthVMWrapper.swift
//  iosApp
//
//  Created by Kompas Digital on 21/01/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation
import Shared

@MainActor
class AuthVMWrapper: ObservableObject {
    private let authUseCase: AuthUseCase
    
    init() {
        self.authUseCase = KoinInjector().authUseCase
    }
    
    // mungkin disini harus pakai skie, OMG semoga bisa pakai async await aja
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
