package com.kompasid.netdatalibrary.core.data.repository.general_content

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.onError
import com.kompasid.netdatalibrary.base.network.onSuccess
import com.kompasid.netdatalibrary.core.data.generalContent.mappers.toInterceptor
import com.kompasid.netdatalibrary.core.data.generalContent.model.dto.GeneralContentResponse
import com.kompasid.netdatalibrary.core.data.generalContent.network.IGeneralContentApiService
import com.kompasid.netdatalibrary.core.data.generalContent.repository.GeneralContentRepository
import com.kompasid.netdatalibrary.sampleData.generalContent.JsonDataGeneralContent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.get
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class GeneralContentRepositoryTest : KoinTest {
    private lateinit var repository: GeneralContentRepository

    private val testModule = module {
        single<IGeneralContentApiService> { FakeIGeneralContentApiService() }
        single { GeneralContentRepository(get()) }
    }

    @BeforeTest
    fun setUp() {
        // Start Koin
        startKoin {
            modules(testModule)
        }

        repository = get()

        // Set the main dispatcher for testing
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @Test
    fun test_generalContent_dataFilled_withOnlineAndResponseIs200_returnSuccess() = runTest {
        val expected =
            Json.decodeFromString<GeneralContentResponse>(JsonDataGeneralContent.successWithData)
                .toInterceptor()

        repository.getGeneralData().apply {
            runCurrent()
        }.onSuccess { actual ->
            assertEquals(
                expected = expected.mrwQuotaAnon,
                actual = actual.mrwQuotaAnon,
                message = "Expected MRW Quota Anon"
            )
            assertEquals(
                expected = expected.mrwQuotaRegon,
                actual = actual.mrwQuotaRegon,
                message = "Expected MRW Quota Regon"
            )
            assertEquals(
                expected = expected.iOS,
                actual = actual.iOS,
                message = "Expected iOS platform"
            )
            assertEquals(
                expected = expected.android,
                actual = actual.android,
                message = "Expected android platform"
            )
        }
    }

    @Test
    fun test_generalContent_dataEmpty_withOnlineAndResponseIs200_returnSuccess() = runTest {
        val expected =
            Json.decodeFromString<GeneralContentResponse>(JsonDataGeneralContent.successWithoutData)
                .toInterceptor()

        (get<IGeneralContentApiService>() as FakeIGeneralContentApiService).setShouldReturnEmpty(true)

        repository.getGeneralData().apply {
            runCurrent()
        }.onSuccess { actual ->
            assertEquals(expected = expected, actual = actual, message = "Expected MRW Quota Anon")
        }
    }

    @Test
    fun test_generalContent_returnFailed() = runTest {

        (get<IGeneralContentApiService>() as FakeIGeneralContentApiService).setShouldReturnError(true)

        repository.getGeneralData().apply {
            runCurrent()
        }.onError { actual ->
            assertEquals(
                expected = NetworkError.Technical(
                    500,
                    "Unknown error"
                ),
                actual = actual,
                message = "Expected Network Error Technical With Code 500"
            )
        }
    }

    class FakeIGeneralContentApiService : IGeneralContentApiService {
        private var shouldReturnError = false
        private var shouldReturnEmpty = false

        fun setShouldReturnError(value: Boolean) {
            shouldReturnError = value
        }

        fun setShouldReturnEmpty(value: Boolean) {
            shouldReturnEmpty = value
        }

        override suspend fun getGeneralContent(): ApiResults<GeneralContentResponse, NetworkError> {
            return if (shouldReturnError) {
                ApiResults.Error(
                    NetworkError.Technical(
                        500,
                        "Unknown error"
                    )
                )
            } else {
                val data = if(shouldReturnEmpty) {
                    Json.decodeFromString<GeneralContentResponse>(JsonDataGeneralContent.successWithoutData)
                } else {
                    Json.decodeFromString<GeneralContentResponse>(JsonDataGeneralContent.successWithData)
                }

                ApiResults.Success(data)
            }
        }
    }


    @AfterTest
    fun tearDown() {
        // Stop Koin
        stopKoin()
        Dispatchers.resetMain()
    }
}