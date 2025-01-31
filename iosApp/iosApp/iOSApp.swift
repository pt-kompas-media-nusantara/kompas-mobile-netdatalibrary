import SwiftUI
import Shared
// import NetDataLibrary

@main
struct iOSApp: App {
    @UIApplicationDelegateAdaptor(AppDelegate.self) var delegate
    
    init() {
//        KoinInitializerKt.doInitKoin()
         Shared.KoinInitializerKt.doInitKoin()
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
        
        Shared.TrackerManager().register { eventName, eventProperty in
            print("Kelas B (eventName): \(eventName)")
            print("Kelas B eventProperty: \(eventProperty)")
        }
        return true
    }
}
