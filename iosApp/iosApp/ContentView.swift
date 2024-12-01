import SwiftUI
import Shared

struct ContentView: View {
    
    @StateObject private var vm = AuthVMWrapper()

	var body: some View {
		Text("greet")
        Button("Login By Guest") {
            Task {
                await vm.loginGuest()
            }
        }
	}
}

class AuthVMWrapper: ObservableObject {
    let authUseCase: AuthUseCase
    
    init() {
        self.authUseCase = KoinInjector().authUseCase
    }
    
    // mungkin disini harus pakai skie, OMG semoga bisa pakai async await aja
    func loginGuest() async throws {
        do {
            let result = try await authUseCase.loginAnon()
            
            switch result {
            case .success:
                // Handle success
                print("Login successful")
            case .failure(let error):
                // Handle failure
                print("Error: \(error.localizedDescription)")
            }
        } catch {
            // Handle unexpected errors
            print("Unexpected error: \(error.localizedDescription)")
        }
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
