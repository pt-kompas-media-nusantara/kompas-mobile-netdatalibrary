import SwiftUI
import Shared

@main
struct iOSApp: App {
    
    init() {
        Shared.KoinInitializerKt.doInitKoin()
    }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
