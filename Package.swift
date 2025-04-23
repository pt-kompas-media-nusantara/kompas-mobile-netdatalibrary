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
            url: "https://github.com/pt-kompas-media-nusantara/kompas-mobile-netdatalibrary/releases/download/1.0.72/KompasIdLibrary.xcframework.zip",
            checksum: "f726a386b66d0559d642119a3b61bd76c75f1a4adbcb662bf507fb275837cb4d"
        ),
        .target(
            name: "Dummy",
            path: "Sources/Dummy" // Tentukan lokasi file sumber
        )
    ]
)
