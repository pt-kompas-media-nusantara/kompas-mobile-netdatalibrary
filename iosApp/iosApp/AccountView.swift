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
    @StateObject private var authVM = AuthVMWrapper()
    @StateObject private var settingsVM = SettingsVMWrapper()
    
    var body: some View {
        ScrollView {
            VStack(spacing: 10) {
                Button("Status Login User") {
                    Task {
                        try await self.myAccountVM.stateLoginUser()
                    }
                }
                
                Button("Get Status Suber User") {
                    Task {
                        try await self.myAccountVM.isAccountSubcriber()
                    }
                }
                
                Button("Get Profile") {
                    Task {
                        try await self.myAccountVM.myAccountInformation()
                    }
                }
                
                Button("Test Multiple Callback") {
                    Task {
                        try await self.authVM.loginGuestOne()
                        try await self.authVM.loginAnonTwo()
                    }
                }
                Button("Login Anon") {
                    Task {
                        try await self.authVM.loginGuest()
                    }
                }
                Button("Login Regon") {
                    Task {
                        try await self.authVM.loginByEmail(type: .regonByEmail)
                    }
                }
                Button("Login Login Suber by Email") {
                    Task {
                        try await self.authVM.loginByEmail(type: .suberByEmail)
                    }
                }
                Button("Refresh Token") {
                    
                }
                Button("Logout") {
                    
                }
                
                Button("Cetak All Data Source") {
                    Task {
                        try await self.settingsVM.loadAllSettings()
                    }
                }
                
                Button("Cetak Access Token") {
                    Task {
                        try await self.settingsVM.loadAccessToken()
                    }
                }
                
                Text("NP")
                Text("Nurirppan")
                Text("aktif berlangganan")
                Text("rang sub")
                
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
                    
                }
            }
        }
    }
}



