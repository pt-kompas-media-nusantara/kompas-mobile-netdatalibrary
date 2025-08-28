//
//  RootView.swift
//  iosApp
//
//  Created by Kompas Digital on 03/03/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI


struct RootView: View {
    var body: some View {
        NavigationStack {
            ScrollView {
                VStack(spacing: 16) {
                    NavigationLink("Settings", destination: AuthView())

                    NavigationLink("Auth", destination: AuthView())
                    
                    NavigationLink("Personal Info", destination: PersonalInfoView())

                    NavigationLink("Account", destination: AccountView())
                }
                .padding()
            }
            .navigationTitle("RootView")
        }
    }
}
