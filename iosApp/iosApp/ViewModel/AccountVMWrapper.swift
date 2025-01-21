//
//  AccountVMWrapper.swift
//  iosApp
//
//  Created by Kompas Digital on 21/01/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation
import Shared


@MainActor
class AccountVMWrapper: ObservableObject {
    
    @Published var accountData: [AccountModel] = []
    
    private let myAccountUseCase: MyAccountUseCase
    
    init() {
        self.myAccountUseCase = KoinInjector().myAccountUseCase
        
        Task {
            await self.accountMenus()
        }
    }
    
    func accountMenus() async {
        do {
            let result = try await self.myAccountUseCase.suberAccountMenu()
            self.accountData = result.map { account in
                AccountModel(
                    menuIcon: account.menuIcon,
                    title: account.title,
                    desc: account.desc,
                    navigation: self.mappingNavigation(value: account.navigation.name)
                    
                )
            }
        } catch {
            print("Unexpected error: \(error.localizedDescription)")
        }
    }
    
    private func mappingNavigation(value: String) -> AccountNavigationType {
        switch value {
        case "LOGIN": return .login
        case "REGISTER": return .register
        case "MANAGE_ACCOUNT": return .manageAccount
        case "SUBSCRIPTION": return .subscription
        case "BOOKMARK": return .bookmark
        case "REWARD": return .reward
        case "SETTINGS": return .settings
        case "CONTACT_US": return .contactUs
        case "QNA": return .qna
        case "ABOUT_APP": return .aboutApp
        case "ABOUT_HARIAN_KOMPAS": return .aboutHarianKompas
        default: return .nothing
        }
    }
}
