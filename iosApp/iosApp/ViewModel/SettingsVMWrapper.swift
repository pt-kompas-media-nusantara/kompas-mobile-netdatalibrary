//
//  SettingsVMWrapper.swift
//  iosApp
//
//  Created by Kompas Digital on 21/01/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation
import KompasIdLibary
// import NetDataLibrary

@MainActor
class SettingsVMWrapper: ObservableObject {
    private let settingsUseCase: SettingsUseCase
    
    init() {
        self.settingsUseCase = KoinInjector().settingsUseCase
    }
    
    // mungkin disini harus pakai skie, OMG semoga bisa pakai async await aja
    func loadAllSettings() async throws {
        do {
            try await self.settingsUseCase.loadAllSettings()
        } catch {
            KompasIdLibary.Logger().debug(tag: nil) {
                error.localizedDescription
            }
        }
    }
    
    func loadAccessToken() async throws {
        do {
            try await self.settingsUseCase.loadAccessToken()
        } catch {
            KompasIdLibary.Logger().debug(tag: nil) {
                error.localizedDescription
            }
        }
    }
    
}
