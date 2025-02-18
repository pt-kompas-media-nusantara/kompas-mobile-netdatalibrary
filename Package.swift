// swift-tools-version:5.9
import PackageDescription

let package = Package(
    name: "NetDataLibrary",
    platforms: [
        .iOS(.v16)
    ],
    products: [
        .library(
            name: "NetDataLibrary",
            targets: ["KompasIdLibrary"]
        )
    ],
    targets: [
        .binaryTarget(
            name: "KompasIdLibrary",
            url: "https://github.com/pt-kompas-media-nusantara/kompas-mobile-netdatalibrary/releases/download/1.0.53/KompasIdLibrary.xcframework.zip",
            checksum: "8dd06b1a9ae992ee2c91959484527d01ea95309617747b0eae8f50952c72fbe5"
        ),
        .target(
            name: "Dummy",
            path: "Sources/Dummy" // Tentukan lokasi file sumber
        )
    ]
)
