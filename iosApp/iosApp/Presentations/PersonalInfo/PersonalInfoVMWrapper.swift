//
//  PersonalInfoVMWrapper.swift
//  iosApp
//
//  Created by Kompas Digital on 13/02/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import KompasIdLibrary
import Combine


@MainActor
class PersonalInfoVMWrapper: ObservableObject {
    private let personalInfoUseCase: PersonalInfoUseCase
    private let personalInfoState: PersonalInfoState
    
    @Published var kmp: PersonalInfoInterceptor
    
    private var task: Task<Void, Never>?
    
    init() {
        self.personalInfoUseCase = KoinInjector().personalInfoUseCase
        self.personalInfoState = KoinInjector().personalInfoState
        
        self.kmp = personalInfoState.state.value // Set nilai awal
        
        observePersonalInfo()
    }
    
    private func observePersonalInfo() {
        task = Task {
            let iterator = personalInfoState.streamPersonalInfo().makeAsyncIterator()
            
            while let newInfo = await iterator.next() {
                DispatchQueue.main.async {
                    self.kmp = newInfo
                    print("Updated Personal Info: \(newInfo)")
                }
            }
        }
    }
    
    deinit {
        task?.cancel()
    }
    
    func getPersonalInfoState() async throws {
        do {
            let result = try await self.personalInfoState.getPersonalInfo()
            print("getPersonalInfoState \(result)")
            
        } catch {
            print("getPersonalInfoState \(error.localizedDescription)")
        }
    }
    
    func getUserDetailsAndMembership() async throws {
        do {
            let result = try await self.personalInfoUseCase.getUserDetailsAndMembership()
            print("Interceptor getUserDetailsAndMembership \(result)")
            
        } catch {
            print("Interceptor getUserDetailsAndMembership \(error.localizedDescription)")
        }
    }
    
}


/**
 data class ArticlesState(
     val articlesExample: List<ExampleArticleResInterceptor> = listOf(),
     val loading: Boolean = false,
     val error: String? = null
 )

 private val _articleState: MutableStateFlow<ArticlesState> =
     MutableStateFlow(ArticlesState(loading = true))
 val articlesState: StateFlow<ArticlesState> get() = _articleState.asStateFlow()

 // Ekstensikan StateFlow agar bisa digunakan di Swift
 fun StateFlow<ArticlesState>.asCommonFlow() = CommonFlow(this)

 class CommonFlow<T>(private val flow: StateFlow<T>) {
     fun collect(collector: (T) -> Unit) {
         flow.map { it }.collect { collector(it) }
     }
 }
 
 import shared
 import Combine

 class ArticlesStateWrapper: ObservableObject {
     @Published var articlesState: ArticlesState = ArticlesState(articlesExample: [], loading: true, error: nil)

     private var cancellable: AnyCancellable?

     init() {
         observeStateFlow()
     }

     private func observeStateFlow() {
         let flow = ArticlesRepository().articlesState.asCommonFlow()
         
         flow.collect { [weak self] newState in
             DispatchQueue.main.async {
                 self?.articlesState = newState
             }
         }
     }
 }
 
 
 import shared
 import Combine

 @MainActor
 class PersonalInfoVMWrapper: ObservableObject {
     
     @Published var personalInfo: PersonalInfoInterceptor = PersonalInfoInterceptor()
     
     private var cancellable: AnyCancellable?
     
     init() {
         let flow = KoinInjector().personalInfoUseCase.personalInfoState.asCommonFlow()

         flow.collect { [weak self] newInfo in
             DispatchQueue.main.async {
                 self?.personalInfo = newInfo
             }
         }
     }
 }


 */
