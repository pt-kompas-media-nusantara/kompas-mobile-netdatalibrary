import SwiftUI
import Shared

@main
struct iOSApp: App {
    
    init() {
        KoinInitializerKt.doInitKoin()
//         Shared.KoinInitializerKt.doInitKoin()
    }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
