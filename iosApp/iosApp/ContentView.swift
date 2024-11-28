import SwiftUI
import Shared

struct ContentView: View {
    
    @StateObject private var vm = AuthVMWrapper()

	var body: some View {
		Text("greet")
        Button("Login By Guest") {
            vm.loginGuest()
        }
	}
}

class AuthVMWrapper: ObservableObject {
    let authVM: AuthVM
    
    init() {
        self.authVM = KoinInjector().authVM
    }
    
    func loginGuest() {
        self.authVM.postLoginGuest()
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
