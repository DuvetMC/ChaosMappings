#!/usr/bin/env sh
mkdir -p build
rm -rf build/META-INF
mkdir build/META-INF
echo "Manifest-Version: 1.0" > build/META-INF/MANIFEST.INF
rm -rf build/mappings
mkdir build/mappings
if [ $1 == "client" ]
then
    cp ./client.tiny build/mappings/
    cd build && zip -5 client.jar ./META-INF/MANIFEST.INF ./mappings/client.tiny
elif [ $1 == "server" ]
then
    cp ./server.tiny build/mappings
    cd build && zip -5 server.jar ./META-INF/MANIFEST.INF ./mappings/server.tiny
else
    echo "Please provide what platform you want to export! Valid options are 'client' and 'server'."
    exit 1
fi