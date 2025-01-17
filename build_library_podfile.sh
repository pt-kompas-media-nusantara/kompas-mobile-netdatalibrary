#!/bin/bash

set -x

echo "Using JAVA_HOME: $JAVA_HOME"
echo "Java version: $(java --version)"

# 1. Minta input versi untuk URL / un komen jika di jalankan di github
if [ -z "$version" ]; then
    echo "Error: variabel 'version' tidak ditemukan. Pastikan variabel diteruskan dari GitHub Actions."
    exit 1
fi

# 2. Perbarui spec.version di dalam file NetDataLibrary.podspec
echo "Updating spec.version to $version in NetDataLibrary.podspec"

# Gunakan sed untuk mengganti nilai spec.version
sed -i '' "s/^  spec.version      = .*/  spec.version      = \"$version\"/" NetDataLibrary.podspec

echo "Proses selesai."


# BERI IZIN
# chmod +x build_library_xcframework.sh

# JALANKAN 
# ./build_library_xcframework.sh
