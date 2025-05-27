Jika **Kotlin Multiplatform (KMP)** ini dijadikan **library**, maka sebaiknya kamu menginisialisasi **Napier** di dalam **expect/actual function** supaya bisa berjalan di **Android dan iOS**.

---

### 📌 **Solusi Terbaik: Buat `Logger` dengan `expect/actual`**
Karena KMP digunakan di **Android dan iOS**, kita harus memastikan **Napier** diinisialisasi di platform masing-masing.

#### 1️⃣ **Buat `Logger` di `shared/src/commonMain/kotlin`**
Buat file `Logger.kt` di `commonMain/kotlin`
```kotlin
package com.yourpackage.shared

import io.github.aakira.napier.Napier

object Logger {
    fun init() {
        initLogger()
    }

    fun debug(message: () -> String) {
        Napier.d(message = message)
    }

    fun info(message: () -> String) {
        Napier.i(message = message)
    }

    fun error(message: () -> String) {
        Napier.e(message = message)
    }
}

// Ini akan diimplementasikan di Android dan iOS
expect fun initLogger()
```

---

#### 2️⃣ **Implementasi di `shared/src/androidMain/kotlin/Logger.kt`**
Buat `Logger.kt` khusus Android:
```kotlin
package com.yourpackage.shared

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

actual fun initLogger() {
    Napier.base(DebugAntilog())
}
```

---

#### 3️⃣ **Implementasi di `shared/src/iosMain/kotlin/Logger.kt`**
Buat `Logger.kt` khusus iOS:
```kotlin
package com.yourpackage.shared

import io.github.aakira.napier.Napier
import platform.Foundation.NSLog

actual fun initLogger() {
    Napier.base(AppleDebugAntilog())
}

class AppleDebugAntilog : io.github.aakira.napier.Antilog() {
    override fun performLog(priority: io.github.aakira.napier.Napier.Level, tag: String?, message: String, throwable: Throwable?) {
        NSLog("${priority.name}: [$tag] $message")
        throwable?.let { NSLog(it.toString()) }
    }
}
```

---

### **💡 Cara Pakai**
Di dalam aplikasi Android atau iOS, panggil `Logger.init()` **sebelum menggunakan log**, misalnya di:
- **Android:** `Application.onCreate()`
- **iOS:** `SwiftUIApp.init()`

```kotlin
fun main() {
    Logger.init() // 🔥 Inisialisasi logging
    Logger.debug { "Hello from KMP!" }
}
```

---

### **🚀 Keuntungan Pendekatan Ini**
✅ **Konsisten di Android dan iOS** → Napier tetap bisa dipakai di kedua platform  
✅ **Tidak perlu inisialisasi manual di aplikasi utama** → Library otomatis menyiapkan logger  
✅ **Bisa dikonfigurasi lebih lanjut per-platform** (misalnya di `iosMain`, log bisa dikirim ke `NSLog`)

🔥 **Coba implementasi ini dan log akan muncul di Android/iOS dengan benar!** 🚀