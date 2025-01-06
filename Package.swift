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
            url: "https://github.com/pt-kompas-media-nusantara/kompas-mobile-netdatalibrary/releases/download/1.0.27/Shared.xcframework.zip",
            checksum: "db42d651a191ef3ab83c11efdb83b1b50d336cda0340f4b0cad96a0e4ee9b248"
        ),
        .target(
            name: "Dummy",
            path: "Sources/Dummy" // Tentukan lokasi file sumber
        )
    ]
)
