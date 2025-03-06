//
//  SettingsObserver.swift
//  iosApp
//
//  Created by Kompas Digital on 06/03/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation
import KompasIdLibrary

class SettingsObserver {
    private let settingsState: SettingsState
    private var tasks: [KeySettingsType: Task<Void, Never>] = [:]
    
    init() {
        self.settingsState = KoinInjector().settingsState
    }
    
    func observeStringSetting(for key: KeySettingsType, onUpdate: @escaping (String) -> Void) {
        // Batalkan task lama jika ada
        tasks[key]?.cancel()
        
        // Buat task baru untuk key tertentu
        let task = Task.detached(priority: .background) { [weak self] in
            guard let self = self else { return }
            
            do {
                for try await newValue in await self.settingsState.streamStringSetting(key: key) {
                    DispatchQueue.main.async {
                        onUpdate(newValue)
                    }
                }
            } catch {
                print("Error observing setting [\(key)]: \(error)")
            }
            
            print("Stream ended for [\(key)]. Removing task.")
            DispatchQueue.main.async {
                self.tasks[key] = nil // Hapus task setelah stream berakhir
            }
        }
        
        // Simpan task di dictionary
        tasks[key] = task
    }
    
    func observeBooleanSetting(for key: KeySettingsType, onUpdate: @escaping (Bool) -> Void) {
        // Batalkan task lama jika ada
        tasks[key]?.cancel()
        
        // Buat task baru untuk key tertentu
        let task = Task.detached(priority: .background) { [weak self] in
            guard let self = self else { return }
            
            do {
                for try await newValue in await self.settingsState.streamBooleanSetting(key: key) {
                    DispatchQueue.main.async {
                        onUpdate(newValue.boolValue)
                    }
                }
            } catch {
                print("Error observing setting [\(key)]: \(error)")
            }
            
            print("Stream ended for [\(key)]. Removing task.")
            DispatchQueue.main.async {
                self.tasks[key] = nil // Hapus task setelah stream berakhir
            }
        }
        
        // Simpan task di dictionary
        tasks[key] = task
    }
    
    func stopObserving(for key: KeySettingsType) {
        tasks[key]?.cancel()
        tasks[key] = nil
    }
    
    func stopAllObserving() {
        tasks.values.forEach { $0.cancel() }
        tasks.removeAll()
    }
    
    deinit {
        stopAllObserving()
    }
}
