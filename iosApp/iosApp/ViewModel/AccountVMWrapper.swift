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
    @Published var myAccountInformationData: MyAccountInformationModel?
    
    private let myAccountUseCase: MyAccountUseCase
    
    init() {
        self.myAccountUseCase = KoinInjector().myAccountUseCase
        
        Task {
            try await self.accountMenus()
        }
    }
    
    func getTracker() async throws {
        do {
            try await self.myAccountUseCase.stateUserType()
//            try await self.myAccountUseCase.nativeTrackerDelegate()
        } catch {
            print("Unexpected error: \(error.localizedDescription)")
        }
    }
    
    func stateUserType() async throws {
        do {
            let result = try await self.myAccountUseCase.stateUserType()
            switch result {
            case .anon:
                print("Unexpected anon")
            case .regon:
                print("Unexpected regon")
            case .suber:
                print("Unexpected suber")
            }
        } catch {
            print("Unexpected error: \(error.localizedDescription)")
        }
    }
    
    func myAccountInformation() async throws {
        do {
            let result = try await self.myAccountUseCase.myAccountInformation()
            self.myAccountInformationData = MyAccountInformationModel(
                idUserName: result.idUserName,
                firstName: result.firstName,
                lastName: result.lastName,
                dateExpired: result.dateExpired,
                stateMembership: result.stateMembership
            )
            print(result)
        } catch {
            print("Unexpected error: \(error.localizedDescription)")
        }
    }
    
    func accountMenus() async throws {
        do {
            let result = try await self.myAccountUseCase.accountMenus()
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
    
    func aboutHarianKompasMenus() async throws {
        do {
            let result = try await self.myAccountUseCase.aboutHarianKompasMenus()
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

    
    func aboutAppMenus() async throws {
        do {
            let result = try await self.myAccountUseCase.aboutAppMenus()
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
    
    func settingMenus() async throws {
        do {
            let result = try await self.myAccountUseCase.settingMenus()
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


struct MyAccountInformationModel {
    let idUserName: String
    let firstName: String
    let lastName: String
    let dateExpired: String
    let stateMembership: String
}
