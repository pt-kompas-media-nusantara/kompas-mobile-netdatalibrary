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
            targets: ["Shared"]
        )
    ],
    targets: [
        .binaryTarget(
            name: "Shared",
            url: "https://github.com/pt-kompas-media-nusantara/kompas-mobile-netdatalibrary/releases/download/1.0.49/Shared.xcframework.zip",
            checksum: "552c0d57d07475b614089a7bde11e0e236faaaa52595b80eb1d5fa2e91eb9ad5"
        ),
        .target(
            name: "Dummy",
            path: "Sources/Dummy" // Tentukan lokasi file sumber
        )
    ]
)
