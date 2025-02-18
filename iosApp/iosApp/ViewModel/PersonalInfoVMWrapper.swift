//
//  PersonalInfoVMWrapper.swift
//  iosApp
//
//  Created by Kompas Digital on 13/02/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import KompasIdLibrary

@MainActor
class PersonalInfoVMWrapper: ObservableObject {
    private let personalInfoUseCase: PersonalInfoUseCase
    
    init() {
        self.personalInfoUseCase = KoinInjector().personalInfoUseCase
    }
    
    // mungkin disini harus pakai skie, OMG semoga bisa pakai async await aja
    func userDetailCall() async throws {
        do {
            try await self.personalInfoUseCase.userDetail()
        } catch {
            KompasIdLibrary.Logger().debug(tag: nil) {
                error.localizedDescription
            }
        }
    }
    
}

