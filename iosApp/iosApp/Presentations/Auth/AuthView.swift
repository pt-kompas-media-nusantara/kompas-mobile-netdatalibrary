//
//  AuthView.swift
//  iosApp
//
//  Created by Kompas Digital on 04/03/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct AuthView: View {
    @StateObject private var authVM = AuthVMWrapper()
    
    var body: some View {
        ScrollView {
            VStack(spacing: 16) {
                Button("Login Suber by Email") {
                    Task {
                        try await authVM.loginByEmail(type: .suberByEmail)
                    }
                }
                .buttonStyle(.borderedProminent)
                
                Button("Login Regon by Email") {
                    Task {
                        try await authVM.loginByEmail(type: .regonByEmail)
                    }
                }
                .buttonStyle(.borderedProminent)
                
                Button("Logout") {
                    Task {
                        try await authVM.logout()
                    }
                }
                .buttonStyle(.bordered)
            }
            .padding()
        }
        .navigationTitle("AuthView")
    }
}
