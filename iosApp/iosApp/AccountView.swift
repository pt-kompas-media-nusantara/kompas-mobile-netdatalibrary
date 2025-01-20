//
//  AccountView.swift
//  iosApp
//
//  Created by Kompas Digital on 20/01/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct AccountView: View {
    
    @StateObject private var myAccountVM = AccountVMWrapper()
    
    var body: some View {
        ScrollView {
            VStack {
                ForEach(self.myAccountVM.accountData, id: \.title) { account in
                    VStack(alignment: .leading) {
                        Text(account.title)
                            .onTapGesture {
                                switch account.navigation {
                                case .nothing:
                                    print("cek, di native, kalau misalnya anon pindah ke login. selain itu normal. pakai kode di KMP aja")
                                case .login:
                                    print("")
                                case .register:
                                    print("")
                                case .manageAccount:
                                    print("manageAccount")
                                case .subscription:
                                    print("")
                                case .bookmark:
                                    print("")
                                case .reward:
                                    print("")
                                case .settings:
                                    print("")
                                case .contactUs:
                                    print("")
                                case .qna:
                                    print("")
                                case .aboutApp:
                                    print("aboutApp")
                                case .aboutHarianKompas:
                                    print("")
                                }
                            }
                    }
                    .padding(.vertical, 8)
                }
            }
        }
    }
}

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
