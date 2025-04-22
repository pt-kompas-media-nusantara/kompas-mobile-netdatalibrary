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
            url: "https://github.com/pt-kompas-media-nusantara/kompas-mobile-netdatalibrary/releases/download/1.0.68/KompasIdLibrary.xcframework.zip",
            checksum: "2481e6ec71947999c58143c634dc644104206a9b350d84ba84ce52d41359fe88"
        ),
        .target(
            name: "Dummy",
            path: "Sources/Dummy" // Tentukan lokasi file sumber
        )
    ]
)
