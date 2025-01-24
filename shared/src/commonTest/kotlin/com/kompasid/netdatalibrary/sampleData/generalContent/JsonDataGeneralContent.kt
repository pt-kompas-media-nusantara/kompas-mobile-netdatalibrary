package com.kompasid.netdatalibrary.sampleData.generalContent

object JsonDataGeneralContent {
    val successWithData = """{
            "status": "ok",
            "code": 200,
            "message": "success",
            "result": {
                "mrw_quota_anon": 1,
                "mrw_quota_regon": 3,
                "android": {
                    "logo": [
                        {
                            "key": "logo-main-page",
                            "value": "https://cdn-content.kompas.id/mobile/img/mainpage_light_dark_andro.svg"
                        },
                        {
                            "key": "logo-auth",
                            "value": "https://cdn-content.kompas.id/mobile/img/auth.svg"
                        },
                        {
                            "key": "logo-main-page-dark",
                            "value": "https://cdn-content.kompas.id/mobile/img/mainpage_light_dark_andro.svg"
                        },
                        {
                            "key": "logo-auth-dark",
                            "value": "https://cdn-content.kompas.id/mobile/img/logo-auth-dark.svg"
                        }
                    ]
                },
                "iOS": {
                    "logo": [
                        {
                            "key": "logo-main-page",
                            "value": "https://cdn-content.kompas.id/mobile/img/main-page.png"
                        },
                        {
                            "key": "logo-auth",
                            "value": "https://cdn-content.kompas.id/mobile/img/auth.png"
                        },
                        {
                            "key": "logo-main-page-dark",
                            "value": "https://cdn-content.kompas.id/mobile/img/logo-main-page-dark.png"
                        },
                        {
                            "key": "logo-auth-dark",
                            "value": "https://cdn-content.kompas.id/mobile/img/logo-auth-dark.png"
                        }
                    ]
                }
            }
        }""".trimIndent()

    val successWithoutData = """{}""".trimIndent()
}