
    
//    
//    func loginAnonTwo() async throws {
//        do {
//            let result = try await authUseCase.loginAnonTwo()
//            switch result {
//            case let success as ResultsSuccess<KotlinPair<LoginAnonResInterceptor, LoginAnonResInterceptor>>:
//                let firstInterceptor = success.data?.first
//                let secondInterceptor = success.data?.second
//                
//                print("loginGuest : Success")
//                print("First Interceptor: \(firstInterceptor)")
//                print("Second Interceptor: \(secondInterceptor)")
//                
//            case let error as ResultsError<NetworkError>:
//                let networkError = error.error
//                print("loginGuest : Network Error: \(networkError.description)")
//                
//            default:
//                print("loginGuest : Unhandled case")
//            }
//        } catch {
//            // Handle unexpected errors
//            print("loginGuest : Unexpected error: \(error.localizedDescription)")
//        }
//    }
//    
//    func loginGuestOne() async throws {
//        do {
//            let result = try await authUseCase.loginAnonOne()
//            switch result {
//            case let success as ResultsSuccess<LoginAnonResInterceptor>:
//                let interceptor = success.data
//                print("loginGuest : \(interceptor)")
//                
//            case let error as ResultsError<NetworkError>:
//                let networkError = error.error
//                print("loginGuest : Network Error: \(networkError.description)")
//                
//            default:
//                print("loginGuest : Unhandled case")
//            }
//        } catch {
//            // Handle unexpected errors
//            print("loginGuest : Unexpected error: \(error.localizedDescription)")
//        }
//    }
//    
//    func loginGuest() async throws {
//        do {
//            let result = try await authUseCase.loginAnon()
//            switch result {
//            case let success as ResultsSuccess<Unit>:
//                let interceptor = success.data
//                print("loginGuest : \(interceptor)")
//                
//            case let error as ResultsError<NetworkError>:
//                let networkError = error.error
//                print("loginGuest : Network Error: \(networkError.description)")
//                
//            default:
//                print("loginGuest : Unhandled case")
//            }
//        } catch {
//            // Handle unexpected errors
//            print("loginGuest : Unexpected error: \(error.localizedDescription)")
//        }
//    }
//    
