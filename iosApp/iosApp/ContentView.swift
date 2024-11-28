import SwiftUI
import Shared

struct ContentView: View {
    
//    @StateObject private let vm = AuthVMWrapper(authVM: .init(authUseCase: <#T##AuthUseCase#>, settingsDataSource: <#T##SettingsDataSource#>))

	var body: some View {
		Text("greet")
        Button("Login By Guest") {
            
        }
	}
}

class AuthVMWrapper: ObservableObject {
    let authVM: AuthVM
    
    init(authVM: AuthVM) {
        self.authVM = authVM
    }
    
    func coba() {
        self.authVM.postLoginGuest()
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
