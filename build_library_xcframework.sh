#!/bin/bash

# 1. Jalankan assemble task
./gradlew :shared:assembleSharedXCFramework || { echo "Gagal membuat XCFramework"; exit 1; }

# 2. Path ke XCFramework
XCFRAMEWORK_PATH="shared/build/XCFrameworks"
TARGET_PATH="."
ZIP_NAME="Shared.xcframework.zip"

# 3. Pindahkan XCFramework ke root proyek
if [ -d "$XCFRAMEWORK_PATH" ]; then
    mv "$XCFRAMEWORK_PATH" "$TARGET_PATH"
    echo "XCFramework berhasil dipindahkan ke root: $TARGET_PATH"
else
    echo "XCFramework tidak ditemukan di $XCFRAMEWORK_PATH"
    exit 1
fi

# 4. Buat ZIP dari file XCFramework
SOURCE_ZIP="XCFrameworks/debug/Shared.xcframework"
ZIP_NAME="Shared.xcframework.zip"
if [ -d "$SOURCE_ZIP" ]; then
    zip -r "$ZIP_NAME" "$SOURCE_ZIP"
    echo "File ZIP berhasil dibuat: $ZIP_NAME"
else
    echo "Folder XCFramework tidak ditemukan: $SOURCE_ZIP"
    exit 1
fi

# 5. Hitung checksum
if [ -f "$ZIP_NAME" ]; then
    checksum=$(swift package compute-checksum "$ZIP_NAME")
    echo "Checksum berhasil dihitung: $checksum"
else
    echo "File ZIP tidak ditemukan: $ZIP_NAME"
    exit 1
fi

# 6. Perbarui checksum di Package.swift
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