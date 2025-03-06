import SwiftUI
import KompasIdLibrary

@main
struct iOSApp: App {
    @UIApplicationDelegateAdaptor(AppDelegate.self) var delegate
    
    init() {
        KoinInitializerKt.doInitKoin()
//         KompasIdLibrary.KoinInitializerKt.doInitKoin()
    }
    
	var body: some Scene {
		WindowGroup {
            RootView()
		}
	}
}

class AppDelegate: NSObject, UIApplicationDelegate {
    
    func application(_ application: UIApplication, willFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {
        return true
    }
    
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {
        LoggerWrapper().doInitLogger()
        
        TrackerManager().register { eventName, eventProperty in
            print("Kelas B (eventName): \(eventName)")
            print("Kelas B eventProperty: \(eventProperty)")
        }
        return true
    }
}


//EDIT 2025, easier way:
//
//Open Applications folder > right click on the Xcode you want to open > Show Package Contents > Contents > MacOS > Double tap on Xcode.
//
//Previous solution:
//
//The solution is very simple. If you have the older version downloaded in your Applications folder for example, lets say 12.5.1 version, you just need to:
//
//Open Terminal
//Open Applications folder in Finder
//Drag the Xcode app into Terminal so it gets its path
//Then add this next to it: /Contents/MacOS/Xcode, so the full command will be something like /Applications/Xcode-12.5.1.app/Contents/MacOS/Xcode
//Press enter to run the command
//Now you should be able to run it. You will note that when you open this version of Xcode, the Terminal will open too, but don't close Terminal because it will close the Xcode too.
