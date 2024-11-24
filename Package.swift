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
            url: "https://github.com/pt-kompas-media-nusantara/kompas-mobile-netdatalibrary/releases/download/{version}/Shared.xcframework.zip",
            checksum: "cb037a2d79ce75e66a5e538f6c0268add0259810f82a7ae04e068e36ac998ebd"
        ),
        // Jika ingin menggunakan branch (misalnya "main" atau "dev")
        .binaryTarget(
            name: "Shared",
            url: "https://github.com/pt-kompas-media-nusantara/kompas-mobile-netdatalibrary/archive/refs/heads/Shared.xcframework.zip",
            checksum: "cb037a2d79ce75e66a5e538f6c0268add0259810f82a7ae04e068e36ac998ebd" // "checksum_for_main_branch" // Ganti dengan checksum yang sesuai
        )
    ]
)
