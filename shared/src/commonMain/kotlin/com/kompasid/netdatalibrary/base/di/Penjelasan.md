Selain menggunakan **`scope`** di Koin, ada beberapa mekanisme lain yang bisa kamu gunakan untuk **state sharing** dan **dependency management** dalam KMP dengan Koin. Berikut beberapa alternatifnya:

---

## 🟡 **1. `single` (Singleton)**
- **Fungsi:** Membuat satu instance yang digunakan bersama di seluruh aplikasi.
- **Sifat:** Instance tetap ada selama aplikasi berjalan.

### ✅ **Contoh:**
```kotlin
// Koin Module
val appModule = module {
    single { ArticlesState() }
}

// ArticlesVM.kt
class ArticlesVM : KoinComponent {
    private val articlesState: ArticlesState by inject()
    
    fun addArticle(article: String) {
        val updated = articlesState.articles.value.toMutableList().apply { add(article) }
        articlesState.updateArticles(updated)
    }
}
```

### 🔹 **Kapan digunakan:**
- Ketika state harus **dibagi** di seluruh aplikasi dan **persisten** selama aplikasi hidup.

---

## 🟠 **2. `factory`**
- **Fungsi:** Membuat **instance baru setiap kali** dependency diminta.
- **Sifat:** Tidak berbagi state antar ViewModel, setiap ViewModel punya **instance sendiri**.

### ✅ **Contoh:**
```kotlin
// Koin Module
val appModule = module {
    factory { ArticlesState() }
}

// ArticlesVM.kt
class ArticlesVM : KoinComponent {
    private val articlesState: ArticlesState by inject()

    fun addArticle(article: String) {
        articlesState.updateArticles(listOf(article))
    }
}
```

### 🔹 **Kapan digunakan:**
- Saat ingin **isolasi** antar ViewModel.
- **Tidak ada state sharing.**

---

## 🟢 **3. `get()` dengan Parameter (`parametersOf`)**
- **Fungsi:** Memberikan dependency dengan parameter secara **manual** saat inject.
- **Sifat:** Bisa berbagi atau tidak tergantung konfigurasi.

### ✅ **Contoh:**
```kotlin
// Koin Module
val appModule = module {
    factory { (initialArticles: List<String>) -> ArticlesState().apply { updateArticles(initialArticles) } }
}

// ArticlesVM.kt
class ArticlesVM : KoinComponent {
    private val articlesState: ArticlesState by inject { parametersOf(listOf("Default Article")) }

    fun printArticles() {
        println(articlesState.articles.value)
    }
}
```

### 🔹 **Kapan digunakan:**
- Jika ingin **membuat state dengan konfigurasi berbeda** di berbagai ViewModel.
- **Tidak otomatis singleton** (tergantung binding).

---

## 🟤 **4. `named()` (Qualifier)**
- **Fungsi:** Menggunakan **beberapa instance berbeda** dari tipe yang sama.
- **Sifat:** Memisahkan instance dengan **identifier (nama).**

### ✅ **Contoh:**
```kotlin
// Koin Module
val appModule = module {
    single(named("Local")) { ArticlesState() }
    single(named("Remote")) { ArticlesState() }
}

// ArticlesVM.kt
class ArticlesVM : KoinComponent {
    private val localArticles: ArticlesState by inject(named("Local"))
    private val remoteArticles: ArticlesState by inject(named("Remote"))

    fun addToLocal(article: String) {
        localArticles.updateArticles(listOf(article))
    }

    fun addToRemote(article: String) {
        remoteArticles.updateArticles(listOf(article))
    }
}
```

### 🔹 **Kapan digunakan:**
- Jika ingin memisahkan **state berdasarkan sumber** (e.g., **Local vs Remote**).
- **Menghindari konflik instance** saat tipe sama.

---

## 🔵 **5. `viewModel()` Scope (khusus Android)**
- **Fungsi:** Membuat ViewModel yang memiliki lifecycle sendiri.
- **Sifat:** Biasanya **berhenti** saat **View dihancurkan**, kecuali di-share dengan **Activity Scope**.

### ✅ **Contoh (Android):**
```kotlin
// Koin Module
val appModule = module {
    viewModel { ArticlesVM(get()) }
}

// Activity
class ArticlesActivity : AppCompatActivity() {
    private val articlesVM: ArticlesVM by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        articlesVM.addArticle("Article from Activity")
    }
}
```

### 🔹 **Kapan digunakan:**
- **Android only**, saat ViewModel harus **terikat lifecycle** Activity atau Fragment.
- **Tidak cocok** untuk **KMP shared module.**

---

## 🟠 **6. Global `object` (Tanpa Koin, Native Kotlin)**
- **Fungsi:** Membuat **singleton state** secara **native** di Kotlin.
- **Sifat:** **Global** selama aplikasi hidup.

### ✅ **Contoh:**
```kotlin
object GlobalArticlesState {
    val articles = mutableListOf<String>()
}

class ArticlesVM {
    fun addArticle(article: String) {
        GlobalArticlesState.articles.add(article)
    }

    fun getArticles(): List<String> {
        return GlobalArticlesState.articles
    }
}
```

### 🔹 **Kapan digunakan:**
- Jika ingin **simpel** dan **tanpa DI framework**.
- **Tidak cocok** untuk aplikasi besar yang butuh **modularitas.**

---

## 🚀 **Rekomendasi untuk KMP dengan Koin:**
| Kebutuhan                               | Gunakan          |
|---------------------------------------|-------------------|
| **State dibagi antar ViewModel (Global)**  | `single`         |
| **State unik tiap ViewModel (Isolasi)** | `factory`        |
| **Beberapa instance tipe sama (multi-source)** | `named()`        |
| **State dengan konfigurasi custom (dynamic)** | `get(parametersOf)` |
| **Native KMP tanpa DI**                | `object`         |

---

Apakah kamu ingin contoh implementasi lengkap dengan `KMP` dan `SharedModule`? 🚀 😊