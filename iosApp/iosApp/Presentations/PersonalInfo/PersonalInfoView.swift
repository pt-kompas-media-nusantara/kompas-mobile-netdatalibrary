//
//  PersonalInfoView.swift
//  iosApp
//
//  Created by Kompas Digital on 04/03/25.
//  Copyright © 2025 orgName. All rights reserved.
//
import SwiftUI

struct PersonalInfoView: View {
        @StateObject private var personalInfoVMWrapper = PersonalInfoVMWrapper()
    
    var body: some View {
        ScrollView {
            VStack(spacing: 16) {
                Button("getPersonalInfo") {
                    Task {
                        try await self.personalInfoVMWrapper.getUserDetailsAndMembership()
                    }
                }
                .buttonStyle(.borderedProminent)
                
                Button("getPersonalInfoState") {
                    Task {
                        try await self.personalInfoVMWrapper.getPersonalInfoState()
                    }
                }
                .buttonStyle(.borderedProminent)
            }
        }
    }
}


