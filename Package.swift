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
            targets: ["KompasIdLibary"]
        )
    ],
    targets: [
        .binaryTarget(
            name: "KompasIdLibary",
            url: "https://github.com/pt-kompas-media-nusantara/kompas-mobile-netdatalibrary/releases/download/1.0.54/KompasIdLibary.xcframework.zip",
            checksum: "c5ac62de568de87452b1950037bd4dcd8f6a4e062dee3e3744092f8bbcc592e1"
        ),
        .target(
            name: "Dummy",
            path: "Sources/Dummy" // Tentukan lokasi file sumber
        )
    ]
)
