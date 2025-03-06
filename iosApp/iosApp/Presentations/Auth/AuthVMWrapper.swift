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
    private let settingsHelper: SettingsHelper
    private let settingsObserver: SettingsObserver = SettingsObserver()
    
    
    init() {
        self.authUseCase = KoinInjector().authUseCase
        self.settingsHelper = KoinInjector().settingsHelper
        
        Task {
            await observeSettings()
        }
        
    }
    
    func removeAllSettingsHelper() async {
        do {
            try await self.settingsHelper.removeAll()
        } catch {
            print("removeAllSettingsHelper Error \(error.localizedDescription)")
        }
    }
    
    private func observeSettings() async {
        do {
            settingsObserver.observeStringSetting(for: .accessToken) { [weak self] newValue in
                print("Updated Access Token: \(newValue)")
            }
            
            settingsObserver.observeStringSetting(for: .refreshToken) { [weak self] newValue in
                print("Updated Refresh Token: \(newValue)")
            }
            
            settingsObserver.observeBooleanSetting(for: .isVerified) { [weak self] newValue in
                print("Updated Is Verified: \(newValue)")
            }
            
            settingsObserver.observeStringSetting(for: .deviceKeyId) { [weak self] newValue in
                print("Updated Device Key ID: \(newValue)")
            }
            
            settingsObserver.observeBooleanSetting(for: .isSocial) { [weak self] newValue in
                print("Updated Is Social: \(newValue)")
            }
        } catch {
            print("observeSettings Error \(error.localizedDescription)")
        }
    }
    
    func stopObserving() async {
        do {
            settingsObserver.stopAllObserving()
        } catch {
            print("stopObserving Error \(error.localizedDescription)")
        }
    }
    
    deinit {
        settingsObserver.stopAllObserving()
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
            case let success as ResultsSuccess<KotlinUnit>:
                let data = success.data
                print("API loginByEmail: Success")
            case let error as ResultsError<NetworkError>:
                let networkError = error.error
                print("API NetworkError : loginByEmail \(networkError.description)")
                
            default:
                print("API loginByEmail : default")
            }
        } catch {
            print("API loginByEmail : catch \(error.localizedDescription)")
        }
    }
    
    func logout() async throws {
        do {
            let result = try await authUseCase.logout()
            switch result {
            case let success as ResultsSuccess<KotlinUnit>:
                print("API logout: Success")
            case let error as ResultsError<NetworkError>:
                let networkError = error.error
                print("API NetworkError : logout \(networkError.description)")
                
            default:
                print("API logout : default")
            }
        } catch {
            print("API logout : catch \(error.localizedDescription)")
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

