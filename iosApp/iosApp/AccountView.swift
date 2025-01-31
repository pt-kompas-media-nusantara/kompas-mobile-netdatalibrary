//
//  AccountView.swift
//  iosApp
//
//  Created by Kompas Digital on 20/01/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import Shared
// import NetDataLibrary


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
                                        self.openFrom = .Empty
                                    case .login:
                                        self.openFrom = .Empty
                                    case .register:
                                        self.openFrom = .Empty
                                    case .manageAccount:
                                        self.openFrom = .Empty
                                    case .subscription:
                                        self.openFrom = .Empty
                                    case .bookmark:
                                        self.openFrom = .BookmarkPage
                                    case .reward:
                                        self.openFrom = .Empty // beda event type
                                    case .settings:
                                        self.openFrom = .SettingPage
                                    case .contactUs:
                                        self.openFrom = .ContactUsPage
                                    case .qna:
                                        self.openFrom = .QuestionAnswerPage
                                    case .aboutApp:
                                        self.openFrom = .AboutAppPage
                                    case .aboutHarianKompas:
                                        self.openFrom = .AboutKompasDailyPage
                                    }
                                    self.navigationTo = true
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


struct asdasd: View {
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
                    try await self.trackerVM.send(eventName: .pageViewed(self.openFrom))
                    self.handler()
                } catch {
                    // Handle error jika terjadi masalah dengan send()
                    print("Error sending tracker: \(error)")
                }
            }
        }
    }
}

struct PageViewedView<Content: View>: View {
    let openFrom: OpenFromType
    let content: () -> Content
    
    @StateObject private var trackerVM = TrackerVMWrapper()
    
    init(openFrom: OpenFromType, @ViewBuilder content: @escaping () -> Content) {
        self.openFrom = openFrom
        self.content = content
    }
    
    var body: some View {
        GeometryReader { geometry in
            content()
                .frame(width: geometry.size.width, height: geometry.size.height)
                .onAppear {
                    Task {
                        // Pastikan menunggu task selesai sebelum memanggil handler
                        do {
                            try await self.trackerVM.send(eventName: .pageViewed(self.openFrom))
                        } catch {
                            // Handle error jika terjadi masalah dengan send()
                            print("Error sending tracker: \(error)")
                        }
                    }
                }
        }
    }
}

struct DestinationView: View {
    let openFrom: OpenFromType
    
    var body: some View {
        PageViewedView(openFrom: self.openFrom) {
            VStack {
                Text("\(self.openFrom)")
            }
        }
    }
}





//cd "$SRCROOT/.."
//./gradlew :shared:embedAndSignAppleFrameworkForXcode
