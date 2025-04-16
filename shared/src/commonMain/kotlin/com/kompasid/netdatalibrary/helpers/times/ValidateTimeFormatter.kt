package com.kompasid.netdatalibrary.helpers.times

import com.kompasid.netdatalibrary.helpers.times.model.TimeDifferenceComponentsModel
import kotlinx.datetime.*

class ValidateTimeFormatter {

    fun calculateTimeDifferenceComponents(
        from: LocalDateTime,
        to: LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    ): TimeDifferenceComponentsModel {
        val fromDate = from.date
        val toDate = to.date

        // 1. Hitung years & months
        var yearDiff = toDate.year - fromDate.year
        var monthDiff = toDate.monthNumber - fromDate.monthNumber
        if (monthDiff < 0) {
            yearDiff -= 1
            monthDiff += 12
        }

        // 2. Hitung hari
        val adjustedStartDate = fromDate
            .plus(yearDiff, DateTimeUnit.YEAR)
            .plus(monthDiff, DateTimeUnit.MONTH)
        val dayDiff = adjustedStartDate.daysUntil(toDate)

        // 3. Hitung waktu (jam:menit:detik)
        val fromInstant = from.toInstant(TimeZone.currentSystemDefault())
        val toInstant = to.toInstant(TimeZone.currentSystemDefault())
        val totalSeconds = (toInstant - fromInstant).inWholeSeconds

        val remainingSeconds = totalSeconds % 60
        val totalMinutes = totalSeconds / 60
        val remainingMinutes = totalMinutes % 60

        return TimeDifferenceComponentsModel(
            years = yearDiff,
            months = monthDiff,
            days = dayDiff,
            minutes = remainingMinutes.toInt(),
            seconds = remainingSeconds.toInt()
        )
    }


}

/*
fun main() {
    val from = LocalDateTime(2020, 1, 1, 12, 0)
    val result = calculateTimeDifferenceComponents(from)

    println("Output:")
    println("${result.years} tahun")
    println("${result.months} bulan")
    println("${result.days} hari")
    println("${result.minutes} menit")
    println("${result.seconds} detik")

    // Gabungkan sebagai 1 string
    val finalString = buildString {
        if (result.years > 0) append("${result.years} tahun ")
        if (result.months > 0) append("${result.months} bulan ")
        if (result.days > 0) append("${result.days} hari ")
        if (result.minutes > 0) append("${result.minutes} menit ")
        if (result.seconds > 0) append("${result.seconds} detik")
    }

    println("Final: $finalString")
}


Output:
3 tahun
3 bulan
6 hari
1 menit
59 detik
Final: 3 tahun 3 bulan 6 hari 1 menit 59 detik
* */


// ====================================================================================================


/*
Fungsi berikut:

```kotlin
Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
```

akan mengembalikan **tanggal dan waktu saat ini (sekarang)** dalam format `LocalDateTime`, **disesuaikan dengan timezone lokal perangkat (misalnya: Asia/Jakarta)**.

---

## 🧠 Penjelasan Singkat:

- `Clock.System.now()` → Mengambil waktu sekarang dalam `Instant` (UTC).
- `toLocalDateTime(...)` → Mengubah `Instant` ke waktu lokal (`LocalDateTime`) berdasarkan `TimeZone.currentSystemDefault()`.

---

## 📦 Contoh Output (misalnya kamu berada di Jakarta, GMT+7)

```kotlin
LocalDateTime(
    year = 2025,
    month = 4,
    dayOfMonth = 16,
    hour = 15,
    minute = 53,
    second = 12,
    nanosecond = 123456000
)
```

Kalau kamu `print(...)`, akan seperti:

```
2025-04-16T15:53:12.123456
```

---

## 🔧 Cocok digunakan untuk:

- Membandingkan waktu sekarang dengan waktu sebelumnya.
- Menampilkan timestamp sesuai waktu lokal user.
- Logging, scheduling, countdown.

Kalau kamu mau hanya ambil tanggal saja (`LocalDate`), bisa pakai:

```kotlin
Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
```

Mau aku bantu buat fungsi formatting-nya juga? Misalnya jadi "16 April 2025, 15:53"?
* */