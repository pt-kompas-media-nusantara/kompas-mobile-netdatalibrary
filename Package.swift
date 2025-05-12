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
            url: "https://github.com/pt-kompas-media-nusantara/kompas-mobile-netdatalibrary/releases/download/1.0.77/KompasIdLibrary.xcframework.zip",
            checksum: "ff739a7a4c90e2a55a9e178c53796c10fb23e40b275b418b663f5289b1b41a45"
        ),
        .target(
            name: "Dummy",
            path: "Sources/Dummy" // Tentukan lokasi file sumber
        )
    ]
)
