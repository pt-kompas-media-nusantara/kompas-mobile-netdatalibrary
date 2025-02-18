//
//  TrackerVMWrapper.swift
//  iosApp
//
//  Created by Kompas Digital on 30/01/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation
import KompasIdLibrary
// import NetDataLibrary
import SwiftUI

@MainActor
class TrackerVMWrapper: ObservableObject {
    
    
    private let trackerUseCase: TrackerUseCase
    
    init() {
        self.trackerUseCase = KoinInjector().trackerUseCase
        
    }

    func send(eventName: EventNameType) async throws {
        do {
            switch eventName {
            case .pageViewed(let openFrom):
                switch openFrom {
                case .Empty:
                    try await self.trackerUseCase.pageViewed(openFromEntryPoint: OpenFromEntryPoint.Empty.shared)
                case .HotTopicSection:
                    try await self.trackerUseCase.pageViewed(openFromEntryPoint: OpenFromEntryPoint.HotTopicSection.shared)
                case .UtamaSection:
                    try await self.trackerUseCase.pageViewed(openFromEntryPoint: OpenFromEntryPoint.UtamaSection.shared)
                case .TerbaruSection:
                    try await self.trackerUseCase.pageViewed(openFromEntryPoint: OpenFromEntryPoint.TerbaruSection.shared)
                case .TerpopulerSection:
                    try await self.trackerUseCase.pageViewed(openFromEntryPoint: OpenFromEntryPoint.TerpopulerSection.shared)
                case .FavpemSection:
                    try await self.trackerUseCase.pageViewed(openFromEntryPoint: OpenFromEntryPoint.FavpemSection.shared)
                case .PilihankuSection:
                    try await self.trackerUseCase.pageViewed(openFromEntryPoint: OpenFromEntryPoint.PilihankuSection.shared)
                case .RelatedArticleSection:
                    try await self.trackerUseCase.pageViewed(openFromEntryPoint: OpenFromEntryPoint.RelatedArticleSection.shared)
                case .OtherArticleSection:
                    try await self.trackerUseCase.pageViewed(openFromEntryPoint: OpenFromEntryPoint.OtherArticleSection.shared)
                case .ReadLaterSection:
                    try await self.trackerUseCase.pageViewed(openFromEntryPoint: OpenFromEntryPoint.ReadLaterSection.shared)
                case .LandingSearch:
                    try await self.trackerUseCase.pageViewed(openFromEntryPoint: OpenFromEntryPoint.LandingSearch.shared)
                case .SearchResult:
                    try await self.trackerUseCase.pageViewed(openFromEntryPoint: OpenFromEntryPoint.SearchResult.shared)
                case .MyAccount:
                    try await self.trackerUseCase.pageViewed(openFromEntryPoint: OpenFromEntryPoint.MyAccount.shared)
                case .SubscriptionStatus:
                    try await self.trackerUseCase.pageViewed(openFromEntryPoint: OpenFromEntryPoint.SubscriptionStatus.shared)
                case .PromoPage:
                    try await self.trackerUseCase.pageViewed(openFromEntryPoint: OpenFromEntryPoint.PromoPage.shared)
                case .SettingPage:
                    try await self.trackerUseCase.pageViewed(openFromEntryPoint: OpenFromEntryPoint.SettingPage.shared)
                case .HelpPage:
                    try await self.trackerUseCase.pageViewed(openFromEntryPoint: OpenFromEntryPoint.HelpPage.shared)
                case .AboutAppPage:
                    try await self.trackerUseCase.pageViewed(openFromEntryPoint: OpenFromEntryPoint.AboutAppPage.shared)
                case .AllEditionEpaperPage:
                    try await self.trackerUseCase.pageViewed(openFromEntryPoint: OpenFromEntryPoint.AllEditionEpaperPage.shared)
                case .MyEpaperPage:
                    try await self.trackerUseCase.pageViewed(openFromEntryPoint: OpenFromEntryPoint.MyEpaperPage.shared)
                case .DetailEpaperPage:
                    try await self.trackerUseCase.pageViewed(openFromEntryPoint: OpenFromEntryPoint.DetailEpaperPage.shared)
                case .SettingEpaperPage:
                    try await self.trackerUseCase.pageViewed(openFromEntryPoint: OpenFromEntryPoint.SettingEpaperPage.shared)
                case .AllBooksPage:
                    try await self.trackerUseCase.pageViewed(openFromEntryPoint: OpenFromEntryPoint.AllBooksPage.shared)
                case .MyBooksPage:
                    try await self.trackerUseCase.pageViewed(openFromEntryPoint: OpenFromEntryPoint.MyBooksPage.shared)
                case .DetailBookPage:
                    try await self.trackerUseCase.pageViewed(openFromEntryPoint: OpenFromEntryPoint.DetailBookPage.shared)
                case .SettingBookPage:
                    try await self.trackerUseCase.pageViewed(openFromEntryPoint: OpenFromEntryPoint.SettingBookPage.shared)
                case .ExternalLink:
                    try await self.trackerUseCase.pageViewed(openFromEntryPoint: OpenFromEntryPoint.ExternalLink.shared)
                case .AudioArticlePage:
                    try await self.trackerUseCase.pageViewed(openFromEntryPoint: OpenFromEntryPoint.AudioArticlePage.shared)
                case .ContactUsPage:
                    try await self.trackerUseCase.pageViewed(openFromEntryPoint: OpenFromEntryPoint.ContactUsPage.shared)
                case .QuestionAnswerPage:
                    try await self.trackerUseCase.pageViewed(openFromEntryPoint: OpenFromEntryPoint.QuestionAnswerPage.shared)
                case .AboutKompasDailyPage:
                    try await self.trackerUseCase.pageViewed(openFromEntryPoint: OpenFromEntryPoint.AboutKompasDailyPage.shared)
                case .FullPlayerAudioArticlePage:
                    try await self.trackerUseCase.pageViewed(openFromEntryPoint: OpenFromEntryPoint.FullPlayerAudioArticlePage.shared)
                case .Onboarding:
                    try await self.trackerUseCase.pageViewed(openFromEntryPoint: OpenFromEntryPoint.Onboarding.shared)
                case .ReadAlso:
                    try await self.trackerUseCase.pageViewed(openFromEntryPoint: OpenFromEntryPoint.ReadAlso.shared)
                case .TranslatorMark:
                    try await self.trackerUseCase.pageViewed(openFromEntryPoint: OpenFromEntryPoint.TranslatorMark.shared)
                case .NotificationPage:
                    try await self.trackerUseCase.pageViewed(openFromEntryPoint: OpenFromEntryPoint.NotificationPage.shared)
                case .NotificationInfo:
                    try await self.trackerUseCase.pageViewed(openFromEntryPoint: OpenFromEntryPoint.NotificationInfo.shared)
                case .NotificationContent:
                    try await self.trackerUseCase.pageViewed(openFromEntryPoint: OpenFromEntryPoint.NotificationContent.shared)
                case .KompasBrief:
                    try await self.trackerUseCase.pageViewed(openFromEntryPoint: OpenFromEntryPoint.KompasBrief.shared)
                case .BookmarkPage:
                    try await self.trackerUseCase.pageViewed(openFromEntryPoint: OpenFromEntryPoint.BookmarkPage.shared)
                }
            }
            
        } catch {
            print("Unexpected error: \(error.localizedDescription)")
        }
    }
}

