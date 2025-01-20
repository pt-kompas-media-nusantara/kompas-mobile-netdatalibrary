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




class AuthVMWrapper: ObservableObject {
    private let authUseCase: AuthUseCase
    
    init() {
        self.authUseCase = KoinInjector().authUseCase
    }
    
    // mungkin disini harus pakai skie, OMG semoga bisa pakai async await aja
    func loginGuest() async throws {
        do {
            let result = try await authUseCase.loginAnon()
            switch result {
            case let success as ResultsSuccess<Unit>:
                let interceptor = success.data
                print("Interceptor: \(interceptor)")
                
            case let error as ResultsError<NetworkError>:
                let networkError = error.error
                print("Network Error: \(networkError.description)")
                
            default:
                print("Unhandled case")
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
