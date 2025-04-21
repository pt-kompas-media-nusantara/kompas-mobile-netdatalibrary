package com.kompasid.netdatalibrary.helper.persistentStorage

enum class KeySettingsType(val key: String) {
    // for api
    ACCESS_TOKEN("ACCESS_TOKEN"), // string |
    REFRESH_TOKEN("REFRESH_TOKEN"), // string |

    // personal information user
    IS_VERIFIED("IS_VERIFIED"), // boolean |
    SSO("SSO"), // object Sso
    ID_GENDER("ID_GENDER"), // int |
    GENDER("GENDER"), // string |
    DATE_BIRTH("DATE_BIRTH"), // string |
    USER_ID("USER_ID"), // string |
    FIRST_NAME("FIRST_NAME"), // string |
    LAST_NAME("LAST_NAME"), // string |
    EMAIL("EMAIL"), // string |
    LOGIN_TYPE("LOGIN_TYPE"), // XXX | belum di pakai
    USER_GUID("USER_GUID"), // string |
    PHONE_NUMBER("PHONE_NUMBER"), // string |
    COUNTRY_CODE("COUNTRY_CODE"),
    COUNTRY("COUNTRY"), // string |
    PROVINCE("PROVINCE"), // string |
    CITY("CITY"), // string |
    USERNAME("USERNAME"), // string |

    // device information
    DEVICE_KEY_ID("DEVICE_KEY_ID"), // string |

    // for validation
    IS_PASS_EMPTY("IS_PASS_EMPTY"), // boolean |
    IS_SOCIAL("IS_SOCIAL"), // boolean |
    PHONE_VERIFIED("PHONE_VERIFIED"), // boolean |
    IS_REGISTERED("IS_REGISTERED"), // boolean |
    REGISTERED_BY("REGISTERED_BY"), // string |
    REGISTERED_ON("REGISTERED_ON"), // List<String> |

    // subscription data
    EXPIRED_MEMBERSHIP("EXPIRED_MEMBERSHIP"), // string |
    ACTIVE_MEMBERSHIP("ACTIVE_MEMBERSHIP"), // string | kayanya harus di bedain IS_ACTIVE & ACTIVE_MEMBERSHIP
    START_DATE_MEMBERSHIP("START_DATE_MEMBERSHIP"), // string |
    END_DATE_MEMBERSHIP("END_DATE_MEMBERSHIP"), // string |
    TOTAL_GRACE_PERIOD_MEMBERSHIP("TOTAL_GRACE_PERIOD_MEMBERSHIP"), // int |
    GRACE_PERIOD_MEMBERSHIP("GRACE_PERIOD_MEMBERSHIP"), // boolean |
    IS_ACTIVE("IS_ACTIVE"), // boolean | kayanya harus di bedain IS_ACTIVE & ACTIVE_MEMBERSHIP
    EXPIRED_MEMBERSHIPS("EXPIRED_MEMBERSHIPS"), // XXX | belum di pakai
    ACTIVE_MEMBERSHIPS("ACTIVE_MEMBERSHIPS"), // XXX | belum di pakai


    // article
    RUBRIK_PILIHANKU("RUBRIK_PILIHANKU"), // List<MyRubriksResInterceptor>
    DOC_REFERRER("DOC_REFERRER"), // string |

    // system information
    ORIGINAL_TRANSACTION_ID("ORIGINAL_TRANSACTION_ID"), // List<String> |
    TRANSACTION_ID("TRANSACTION_ID"), // List<String> |
    HISTORY_TRANSACTION("HISTORY_TRANSACTION"), // String |

    IOS_UI_DEVICE_SYSTEM_NAME("IOS_UI_DEVICE_SYSTEM_NAME"), // string | iOS | Synonym for model. Prior to iOS 16, user-assigned device name (e.g. @"My iPhone").
    IOS_UI_DEVICE_NAME("IOS_UI_DEVICE_NAME"), // string | John's iPhone
    IOS_UI_DEVICE_MODEL("IOS_UI_DEVICE_MODEL"), // string | iPhone
    IOS_UI_DEVICE_SERIES("IOS_UI_DEVICE_SERIES"), // string | iPhone 6 Plus
    DEVICE("DEVICE"), // string | (iOS) Native | iPhone | iPhone | 16.7.8 | iPhone 6 Plus
    DEVICE_TYPE("DEVICE_TYPE"), // string | smartphone, tablet, phablet, desktop
    OS_VERSION("OS_VERSION"), // string | 17.4

    STATE_INSTALL("STATE_INSTALL"), // int | 0 = kosong, 1 = first install, 2 = update | stateInstallType()
    APP_VERSION_KOMPAS_ID_CURRENT("APP_VERSION_KOMPAS_ID"), // string | 3.50.0
    APP_VERSION_KOMPAS_ID_LATEST("APP_VERSION_KOMPAS_ID_LATEST"), // string | 3.50.0

    FORCE_UPDATE_MIN_VERSION("FORCE_UPDATE_MIN_VERSION"), // string |
    FORCE_UPDATE_MAX_VERSION("FORCE_UPDATE_MAX_VERSION"), // string |
    LAST_FORCE_UPDATE_SHOWN_DATE("LAST_FORCE_UPDATE_SHOWN_DATE"), // string |

    RECOMENDATION_OS_VERSION("RECOMENDATION_OS_VERSION"), // string |
    RECOMENDATION_MINIMUM_OS_VERSION("RECOMENDATION_MINIMUM_OS_VERSION"), // string |
    LAST_INFORMATION_SHOWN_DATE("LAST_INFORMATION_SHOWN_DATE"), // string |
    LAST_RECOMMENDATION_SHOWN_DATE("LAST_RECOMMENDATION_SHOWN_DATE"), // string |


    ENDPOINT_TYPE("ENDPOINT_TYPE"), // XXX | belum di pakai

    FLAVORS("FLAVORS"), // string | DEBUG, ALLCLOUD, DKID_ID, DKID_CLOUD, DQA_ID, DQA_CLOUD, RQA_ID, RKID_ID
    IS_DEBUG("IS_DEBUG"), // boolean | true or false
    IS_LOG_ACTIVED("IS_LOG_ACTIVED"), // boolean | true or false
}

// CATATAN :

/*
nurirppan__ reza__ :
*/

/*
nurirppan__ reza__ :
IS_LOG_ACTIVED
ini untuk menampilkan log. jadi walaupun running versi release tapi IS_LOG_ACTIVED nya true maka log nya akan ke cetak
*/


/*
nurirppan__ reza__ :
FLAVORS, kirim string langsung aja, cuman sebagai info aja untuk internal untuk di tampilkan di tentang aplikasi
ini yang menentukan endpoint yang akan digunakan
ios : RELEASE, DEBUG, ALL_CLOUD, DQA_ID, n
kalau RELEASE makan endpointnya semuanya release,
kalau ALL_CLOUD makan endpointnya semuanya all cloud,
selain itu mixed dan pakai apiary
*/

/*
ORIGINAL_TRANSACTION_ID
nurirppan__ reza__ :

ini hanya mengirimkan list ORIGINAL_TRANSACTION_ID, nanti di mapping di KMP
OUTPUT:
2000000836928164

func getLocalSubscriptionHistory() async -> [HistorySubcriptionData] {
    var histories: [HistorySubcriptionData] = []
    var storeKitData: [String] = []

    for await transaction in StoreKit.Transaction.all {
        if let data = try? transaction.payloadValue {
            histories.append(.init(productId: data.productID, transactionId: "\(data.id)", originalTransactionId: "\(data.originalID)", purchaseDate: "\(data.purchaseDate)"))
        }
        storeKitData.append("\(transaction)")
    }
    SaveStorekitData.shared.saveTransaction(params: "\(storeKitData)")
    return histories
}

func getOriginalTransactionId() async -> String? {
    // Ambil semua riwayat langganan lokal
    let histories = await getLocalSubscriptionHistory()
    guard let firstOriginalTransactionId = histories.first?.originalTransactionId else {
        return nil // Jika tidak ada data, kembalikan nil
    }

    // Validasi apakah semua originalTransactionId sama dengan yang pertama
    let isValid = histories.allSatisfy { $0.originalTransactionId == firstOriginalTransactionId }

    if isValid {
        // Jika semua valid, kembalikan originalTransactionId pertama
        return firstOriginalTransactionId
    } else {
        // Jika ada perbedaan, kembalikan semua originalTransactionId dalam format tertentu
        let combinedIds = histories.map { $0.originalTransactionId }.joined(separator: ", ")
        return "\(combinedIds)"
    }
}
*/

/*
TRANSACTION_ID
nurirppan__ reza__ :

ini hanya mengirimkan list ORIGINAL_TRANSACTION_ID, nanti di mapping di KMP
nanti di cut dari belakang sama nur irfan, hanya dikirim 3 saja

OUTPUT:
2000000892775097, 2000000892774264, 2000000892773339, 2000000892771126,
*/

/*
HISTORY_TRANSACTION
nurirppan__ reza__ :
karna tidak bisa langsung seperti ini print("\(StoreKit.Transaction.all)"), maka reza harus membuat model sendiri
atau bisa pakai seperti ini dari deeplink
if let jsonData = try? transaction.jsonRepresentation,
   let jsonString = String(data: jsonData, encoding: .utf8) {
    print("JSON Data: \(jsonString)")
}

contoh kode
    func getLocalSubscriptionHistory() async -> [HistorySubcriptionData] {
        var histories: [HistorySubcriptionData] = []
        var storeKitData: [String] = []

        for await transaction in StoreKit.Transaction.all {
            if let data = try? transaction.payloadValue {
                histories.append(.init(productId: data.productID, transactionId: "\(data.id)", originalTransactionId: "\(data.originalID)", purchaseDate: "\(data.purchaseDate)"))
            }
            storeKitData.append("\(transaction)")
        }
        SaveStorekitData.shared.saveTransaction(params: "\(storeKitData)")
        return histories
    }
*/

/*
NEW_VERSION_APP
nurirppan__ reza__ :
ini di ambil dari api, namun dengan validasi.
jika versi api lebih rendah dari versi aplikasi (CURRENT_VERSION_APP), maka yang di ambil adalah CURRENT_VERSION_APP. namun jika versi api lebih tinggi maka ambil versi api
*/

/*
CURRENT_VERSION_APP
nurirppan__ reza__ : ini di ambil dari versi aplikasi kompas.id (release) | ambil dari api apiary (debug)

*/

/*
RECOMENDATION_OS_VERSION
nurirppan__ reza__ : ini ambil dari api, digunakan untuk menjalankan os recomendation dan validasi pemunculannya. ini bisa pakai enum case untuk return nya
*/

/*
OS_VERSION
nurirppan__ reza__ : ini di pakai untuk menjalankan os recomendation
*/

/*
DOC_REFERRER
nurirppan__ reza__ : pakai library yang terakhir
kalau login biasa pakai "iOS", tapi kalau login dari detail artikel pakai  "iOS|\(name)"
var asUtmSource: String {
    switch self {
    case .articleDetail(let name):
        return "iOS|\(name)"
    default:
        return "iOS"
    }
}
*/

/*
nurirppan__ reza__ : pakai library yang terakhir
DEVICE
// "(\(UIDevice.current.systemName)) Native |  \(UIDevice.current.name) | \(UIDevice.current.model) | \(UIDevice.current.systemVersion) | Get From Library"
// (iOS) Native | iPhone | iPhone | 16.7.8 | iPhone 6 Plus

REFERENCE
https://stackoverflow.com/a/26962452
Library : https://github.com/devicekit/DeviceKit
*/

/*
nurirppan__ : PR ini buat fungsi balikan enum string
DEVICE_TYPE("DEVICE_TYPE"),
IOS
let deviceIdiom = UIScreen.main.traitCollection.userInterfaceIdiom
switch deviceIdiom {
    case .phone:
    return "smartphone"
    case .pad:
    return "tablet"
    case .carPlay:
    return "phablet"
    case .tv:
    return "desktop"
    case .mac:
    return "desktop"
    case .unspecified:
    return "phablet"
    default:
    return "phablet"
}
*/