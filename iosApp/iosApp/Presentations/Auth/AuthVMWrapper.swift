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
    private let settingsState: SettingsState
    
    private var tasks: [KeySettingsType: Task<Void, Never>] = [:]
    
    init() {
        self.authUseCase = KoinInjector().authUseCase
        self.settingsState = KoinInjector().settingsState
           
        /// String
        observeStringSetting(for: .accessToken)
        observeStringSetting(for: .refreshToken)
        observeStringSetting(for: .deviceKeyId)
        /// Boolean
        observeBooleanSetting(for: .isVerified)
        observeBooleanSetting(for: .isSocial)
    }
    
    
    func observeStringSetting(for key: KeySettingsType) {
        // Batalkan task lama jika ada
        tasks[key]?.cancel()
        
        // Buat task baru untuk key tertentu
        let task = Task.detached(priority: .background) { [weak self] in
            guard let self = self else { return }
            
            do {
                for try await newValue in self.settingsState.streamStringSetting(key: key) {
                    await MainActor.run {
                        print("Updated Setting [\(key)]: \(newValue)")
                    }
                }
            } catch {
                print("Error observing setting [\(key)]: \(error)")
            }
            
            print("Stream ended for [\(key)]. Removing task.")
            await MainActor.run {
                self.tasks[key] = nil // Hapus task setelah stream berakhir
            }
        }
        
        // Simpan task di dictionary
        tasks[key] = task
    }
    
    func observeBooleanSetting(for key: KeySettingsType) {
        // Batalkan task lama jika ada
        tasks[key]?.cancel()
        
        // Buat task baru untuk key tertentu
        let task = Task.detached(priority: .background) { [weak self] in
            guard let self = self else { return }
            
            do {
                for try await newValue in self.settingsState.streamBooleanSetting(key: key) {
                    await MainActor.run {
                        print("Updated Setting [\(key)]: \(newValue)")
                    }
                }
            } catch {
                print("Error observing setting [\(key)]: \(error)")
            }
            
            print("Stream ended for [\(key)]. Removing task.")
            await MainActor.run {
                self.tasks[key] = nil // Hapus task setelah stream berakhir
            }
        }
        
        // Simpan task di dictionary
        tasks[key] = task
    }
    
    func stopObserving(for key: KeySettingsType) {
        tasks[key]?.cancel()
        tasks[key] = nil
    }
    
    func stopAllObserving() {
        tasks.values.forEach { $0.cancel() }
        tasks.removeAll()
    }
    
    deinit {
        tasks.values.forEach { $0.cancel() }
        tasks.removeAll()
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

