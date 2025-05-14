** INFO **
[URL]
https://api.kompas.id/account/api/v2/users/rubrik/add

[Method]
POST

[Status]
200

[Request date]
2025-03-06 02:37:30 +0000

[Response date]
2025-03-06 02:37:30 +0000

[Time interval]
0.18630505

[Timeout]
60.0

[Cache policy]
UseProtocolCachePolicy



** REQUEST **
-- Headers --
a
[Content-Type]
application/json

[Content-Length]
314

[Cache-Control]
no-cache

[Authorization]
Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjp7ImVtYWlsIjoibnVyaXJwcGFuQGdtYWlsLmNvbSIsImlkIjoiYjI1ODVmMzctOWM4ZS00MmYwLTg2YjAtZDI1MDczZWM2YTQ2IiwicnQiOjE3NzI3MDMxNjQsInNjb3BlIjpbInJlYWQtYXJ0aWNsZSIsInJlYWQtcHJvZmlsZSJdfSwiZXhwIjoxNzQxMjI5NTEyLCJpYXQiOjE3NDEyMjg2MTIsImlzcyI6Imh0dHBzOi8vd3d3LmtvbXBhcy5pZCJ9.UYtmKYWvisTbNnnkcIoz0rcXwhbjVxYPKptlHgsIA__SJ7Rs19LUpehkMxrxrtTsuHJUAY6gLlDN3uQPOAEyGD_KZmxn5Y95ccMg5VMlVcystHJhTbynoU5Kzx__UnpDvU-uO57ehWGRH9d5Zz9LLOsdpZ_Je6IsEZr-Aqupp18


-- Body --

{
"type": "rubrik pilihan",
"userRubriks": [
{
"value": "polhuk",
"isChoosen": true
},
{
"value": "ekonomi",
"isChoosen": true
},
{
"value": "opini",
"isChoosen": true
},
{
"value": "humaniora",
"isChoosen": true
}
]
}


** RESPONSE **
-- Headers --

[Strict-Transport-Security]
max-age=31536000; includeSubDomains

[Content-Type]
application/json; charset=utf-8

[Access-Control-Allow-Origin]
*

[access-control-allow-headers]
Authorization, Content-Type,X-Forwarded-For, X-app123-XPTO

[cf-ray]
91be78b85b1040fc-SIN

[cf-cache-status]
DYNAMIC

[Content-Encoding]
br

[Date]
Thu, 06 Mar 2025 02:37:30 GMT

[Server]
cloudflare

[access-control-allow-methods]
GET, PUT, POST

[access-control-allow-credentials]
true

[access-control-max-age]
1728000


-- Body --

{
"success": true,
"code": 200,
"message": "Berhasil menambahkan Rubrik",
"meta": {
"cache": false,
"time": 1741228650
},
"data": {
"type": "rubrik pilihan",
"userRubriks": [
{
"value": "polhuk",
"isChoosen": true
},
{
"value": "ekonomi",
"isChoosen": true
},
{
"value": "opini",
"isChoosen": true
},
{
"value": "humaniora",
"isChoosen": true
}
]
}
}
