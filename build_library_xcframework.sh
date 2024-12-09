#!/bin/bash

# Jalankan assemble task
./gradlew :shared:assembleSharedXCFramework

# Path ke XCFramework
SOURCE_PATH="shared/build/XCFrameworks"
TARGET_PATH="."

# Pindahkan file ke root
mv $SOURCE_PATH $TARGET_PATH

# Buat ZIP dari file XCFramework
SOURCE_ZIP="XCFrameworks/debug/Shared.xcframework"
NAME_OF_ZIP="Shared.xcframework.zip"
zip -r "${NAME_OF_ZIP}" $SOURCE_ZIP

# Checksum
swift package compute-checksum $SOURCE_ZIP

echo "File XCFramework telah dipindahkan dan di-zip."


# BERI IZIN
# chmod +x build_library_xcframework.sh

# JALANKAN 
# ./build_library_xcframework.sh