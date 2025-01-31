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
    
    @State private var navigationTo = false
    @State private var openFrom: OpenFromType = .Empty
    
    @StateObject private var myAccountVM = AccountVMWrapper()
    @StateObject private var authVM = AuthVMWrapper()
    @StateObject private var settingsVM = SettingsVMWrapper()
    
    
    var body: some View {
        NavigationStack {
            ScrollView {
                VStack(spacing: 10) {
                    Button("Get Tracker") {
                        Task {
                            try await self.myAccountVM.getTracker()
                        }
                    }
                    
                    Button("Status State User Type") {
                        Task {
                            try await self.myAccountVM.stateUserType()
                        }
                    }
                    
                    Button("Informasi Akun") {
                        Task {
                            try await self.myAccountVM.myAccountInformation()
                        }
                    }
                    
                    Button("accountMenus") {
                        Task {
                            try await self.myAccountVM.accountMenus()
                        }
                    }
                    
                    Button("aboutHarianKompasMenus") {
                        Task {
                            try await self.myAccountVM.aboutHarianKompasMenus()
                        }
                    }
                    
                    Button("aboutAppMenus") {
                        Task {
                            try await self.myAccountVM.aboutAppMenus()
                        }
                    }
                    
                    Button("settingMenus") {
                        Task {
                            try await self.myAccountVM.settingMenus()
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
                    
                    Text("\(self.myAccountVM.myAccountInformationData)")
                    Text("Nurirppan")
                    
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
                                        self.openFrom = .AboutKompasDailyPage
                                        self.navigationTo = true
                                    }
                                }
                        }
                        
                    }
                }
            }
            .navigationDestination(isPresented: $navigationTo) {
                DestinationView(openFrom: self.openFrom)
            }
        }
    }
}


struct PageViewedView: View {
    let openFrom: OpenFromType
    let handler: () -> Void
    
    @StateObject private var trackerVM = TrackerVMWrapper()

    var body: some View {
        ZStack {
            Text("\(self.openFrom)")
        }
        .onAppear {
            Task {
                // Pastikan menunggu task selesai sebelum memanggil handler
                do {
                    try await self.trackerVM.send(openFrom: self.openFrom)
                    self.handler()
                } catch {
                    // Handle error jika terjadi masalah dengan send()
                    print("Error sending tracker: \(error)")
                }
            }
        }
    }
}

struct DestinationView: View {
    let openFrom: OpenFromType
    
    var body: some View {
        PageViewedView(openFrom: openFrom) {
            VStack {
                Text("\(self.openFrom)")
            }
        }
    }
}





