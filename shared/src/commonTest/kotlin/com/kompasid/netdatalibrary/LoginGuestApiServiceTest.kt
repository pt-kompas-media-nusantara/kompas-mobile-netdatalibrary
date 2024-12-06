package com.kompasid.netdatalibrary


import com.kompasid.netdatalibrary.base.di.authModule
import com.kompasid.netdatalibrary.base.di.networkModule
import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.netData.data.loginGuestData.LoginGuestApiService
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.get
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

//class LoginGuestApiServiceTest : KoinTest {
//
//    @Test
//    fun `should inject my components`() {
//        startKoin {
//            modules(networkModule, authModule) // Gunakan modul yang didefinisikan
//        }
//        val loginGuestApiService = get<LoginGuestApiService>()
//
//        loginGuestApiService.postLoginGuest()
//
//        assertNotNull(loginGuestApiService)
//    }
//}


//class AnotherApiServiceTest : BaseApiServiceTest() {
//
//    @Test
//    fun `should successfully handle another API response`() = runBlocking {
//        val mockResponse = """{ ... }""" // JSON respons lain
//        val httpClient = createMockHttpClient(mockResponse)
//
//        val anotherApiService = AnotherApiService(httpClient)
//        val result = anotherApiService.callApi()
//
//        assertNotNull(result)
//        // Tambahkan assertions
//    }
//}
