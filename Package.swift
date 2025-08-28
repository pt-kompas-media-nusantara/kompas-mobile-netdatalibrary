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
            url: "https://github.com/pt-kompas-media-nusantara/kompas-mobile-netdatalibrary/releases/download/1.0.82/KompasIdLibrary.xcframework.zip",
            checksum: "8aa299eda3fab10bfde52e76881ee4aba6149af37cde0d5bf86f86851583d4fb"
        ),
        .target(
            name: "Dummy",
            path: "Sources/Dummy" // Tentukan lokasi file sumber
        )
    ]
)
