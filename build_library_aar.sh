#!/bin/bash

# 1. Minta input versi untuk URL / un komen jika di jalankan di local
# read -p "Masukkan versi untuk URL (misalnya, 1.0.0): " version
# if [ -z "$version" ]; then
#     echo "Versi tidak boleh kosong!"
#     exit 1
# fi

# 1. Minta input versi untuk URL / un komen jika di jalankan di github
if [ -z "$version" ]; then
    echo "Error: variabel 'version' tidak ditemukan. Pastikan variabel diteruskan dari GitHub Actions."
    exit 1
fi

# 2. Jalankan assemble task
./gradlew :shared:assembleRelease || { echo "Gagal membuat AAR"; exit 1; }

# 4. Path ke aar
AAR_PATH="shared/build/outputs/aar"
TARGET_PATH="."
ZIP_NAME="Shared.xcframework.zip"

# 5. Pindahkan AAR ke root proyek
if [ -d "$AAR_PATH" ]; then
    mv "$AAR_PATH" "$TARGET_PATH"
    echo "XCFramework berhasil dipindahkan ke root: $TARGET_PATH"
else
    echo "XCFramework tidak ditemukan di $AAR_PATH"
    exit 1
fi

# 6. Buat ZIP dari file XCFramework
SOURCE_ZIP="XCFrameworks/debug/Shared.xcframework"
ZIP_NAME="Shared.xcframework.zip"
if [ -d "$SOURCE_ZIP" ]; then
    zip -r "$ZIP_NAME" "$SOURCE_ZIP"
    echo "File ZIP berhasil dibuat: $ZIP_NAME"
else
    echo "Folder XCFramework tidak ditemukan: $SOURCE_ZIP"
    exit 1
fi

# 7. Hitung checksum
if [ -f "$ZIP_NAME" ]; then
    checksum=$(swift package compute-checksum "$ZIP_NAME")
    echo "Checksum berhasil dihitung: $checksum"
else
    echo "File ZIP tidak ditemukan: $ZIP_NAME"
    exit 1
fi

# 8. Perbarui checksum di Package.swift
if [ -f "Package.swift" ]; then
    sed -i '' "s/checksum: \".*\"/checksum: \"$checksum\"/" Package.swift
    echo "Checksum berhasil diperbarui di Package.swift: $checksum"
else
    echo "Package.swift tidak ditemukan"
    exit 1
fi

echo "Proses selesai."


# BERI IZIN
# chmod +x build_library_xcframework.sh

# JALANKAN 
# ./build_library_xcframework.sh
