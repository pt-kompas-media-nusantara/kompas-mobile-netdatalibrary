import SwiftUI
import Shared

@main
struct iOSApp: App {
    @UIApplicationDelegateAdaptor(AppDelegate.self) var delegate
    
    init() {
        KoinInitializerKt.doInitKoin()
//         Shared.KoinInitializerKt.doInitKoin()
    }
    
	var body: some Scene {
		WindowGroup {
            AccountView()
		}
	}
}

class AppDelegate: NSObject, UIApplicationDelegate {
    
    func application(_ application: UIApplication, willFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {
        return true
    }
    
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {
        
        Shared.TrackerManager().register { event in
            print("Kelas B menerima: \(event)")
        }
        return true
    }
}
