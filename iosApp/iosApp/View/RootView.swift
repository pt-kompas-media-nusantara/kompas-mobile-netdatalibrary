//
//  RootView.swift
//  iosApp
//
//  Created by Kompas Digital on 03/03/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct RootView: View {
    
    @State private var navigationTo = false
    @State private var destination: DestinationViewType = .none
    
    var body: some View {
        NavigationStack {
            ScrollView {
                Button("Settings") {
                    
                }
                
                Button("Auth") {
                    
                }
                
                Button("Account") {
                    self.destination = .Account
                    self.navigationTo = true
                }
            }
            .navigationDestination(isPresented: $navigationTo) {
                switch self.destination {
                case .none:
                    AccountView()
                case .Auth:
                    AccountView()
                case .Account:
                    AccountView()

                }
            }
        }
    }
    
}

enum DestinationViewType {
    case none
    case Auth
    case Account
}
