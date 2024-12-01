package com.kompasid.netdatalibrary.netData.data.appIconData

/*
Untuk menggunakan file `app-icon.json` sebagai mock response dalam unit test, kita bisa memanfaatkan library seperti **Mockk** untuk memmock response dari `fetchDataFromApi`. File JSON tersebut akan dibaca ke dalam sebuah string dan di-parse ke dalam model `AppIconResponse` untuk memverifikasi logika layanan API.

Berikut adalah langkah-langkah dan kode lengkap:

---

### **Persiapan**

1. **Simpan File JSON**
   - Letakkan file `app-icon.json` di dalam direktori `src/test/resources` agar dapat diakses dalam unit test.

2. **Fungsi untuk Membaca File JSON**
   ```kotlin
   fun getJsonFromResource(fileName: String): String {
       val classLoader = this::class.java.classLoader
       return classLoader.getResource(fileName)?.readText()
           ?: throw IllegalArgumentException("File $fileName not found in resources")
   }
   ```

3. **Model untuk JSON**
   - Pastikan model `AppIconResponse` sudah sesuai dengan struktur JSON tersebut. Contoh model:
     ```kotlin
     data class AppIconResponse(
         val status: String,
         val code: Int,
         val message: String,
         val result: ResultData
     )

     data class ResultData(
         val mrw_quota_anon: Int,
         val mrw_quota_regon: Int,
         val android: PlatformLogo,
         val iOS: PlatformLogo
     )

     data class PlatformLogo(
         val logo: List<LogoData>
     )

     data class LogoData(
         val key: String,
         val value: String
     )
     ```

---

### **Unit Test dengan Mock Response**

#### **Kode Unit Test**
```kotlin
import io.mockk.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class AppIconApiServiceTest {

    private val mockNetworkApiService: INetworkApiService = mockk()
    private lateinit var appIconApiService: AppIconApiService

    @Before
    fun setUp() {
        appIconApiService = AppIconApiService(mockNetworkApiService)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `getAppIcon returns Success when response matches app-icon json`() = runBlocking {
        // Arrange
        val jsonResponse = getJsonFromResource("app-icon.json")
        val mockResponse: AppIconResponse = Json.decodeFromString(jsonResponse)
        coEvery {
            mockNetworkApiService.fetchDataFromApi(any())
        } returns Pair(mockResponse, HttpStatusCode.OK)

        // Act
        val result = appIconApiService.getAppIcon()

        // Assert
        assertEquals(ApiResults.Success(mockResponse), result)
        coVerify { mockNetworkApiService.fetchDataFromApi(any()) }
    }

    @Test
    fun `getAppIcon returns Error for invalid JSON`() = runBlocking {
        // Arrange
        val invalidJson = """{ "invalid": "data" }"""
        coEvery { mockNetworkApiService.fetchDataFromApi(any()) } throws Exception("Invalid Data")
        every { mockNetworkApiService.mapExceptionToError(any()) } returns NetworkError.Error(Exception("Invalid Data"))

        // Act
        val result = appIconApiService.getAppIcon()

        // Assert
        assertEquals(ApiResults.Error(NetworkError.Error(Exception("Invalid Data"))), result)
        coVerify { mockNetworkApiService.fetchDataFromApi(any()) }
        verify { mockNetworkApiService.mapExceptionToError(any()) }
    }
}
```

---

### **Penjelasan**

1. **Fungsi `getJsonFromResource`**:
   - Membaca file `app-icon.json` dari `resources` dan mengembalikannya sebagai string.

2. **Deserialization JSON ke Model**:
   - Menggunakan **Kotlin Serialization** (`kotlinx.serialization`) untuk memparse JSON ke objek `AppIconResponse`.

3. **Unit Test**:
   - Test pertama memastikan bahwa JSON yang valid menghasilkan `ApiResults.Success`.
   - Test kedua memastikan kesalahan parsing JSON memunculkan `ApiResults.Error`.

4. **Mocking**:
   - `fetchDataFromApi` dimock agar tidak benar-benar memanggil API.
   - Gunakan pasangan (mock response dan `HttpStatusCode`) sesuai kebutuhan.

5. **Assertions**:
   - Bandingkan hasil dari `AppIconApiService.getAppIcon` dengan hasil yang diharapkan (`ApiResults.Success` atau `ApiResults.Error`).

---

### **Keuntungan**

1. **Mock JSON**:
   - File JSON memastikan unit test mendekati kasus nyata tanpa bergantung pada server eksternal.

2. **Keterbacaan**:
   - JSON dan model yang mendetail membuat alur pengujian lebih jelas.

3. **Modular**:
   - Desain berbasis antarmuka dan mocking menjadikan kode mudah di-maintain dan diuji.
* */