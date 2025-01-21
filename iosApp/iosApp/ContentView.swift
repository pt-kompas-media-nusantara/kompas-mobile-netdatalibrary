import SwiftUI
import Shared

struct ContentView: View {
    
    @StateObject private var vm = AuthVMWrapper()
    

    var body: some View {
        ScrollView {
            VStack {
                Text("greet")
                Button("Login By Guest") {
                    Task {
                        try await vm.loginGuest()
                    }
                }
            }
        }
	}
}





