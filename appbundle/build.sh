#! /bin/sh

version=$1
artifact_id=$2
target_folder=$3

echo "version: $version"
echo "artifact_id: $artifact_id"
echo "target_folder: $target_folder"

cp -r ${artifact_id} ${target_folder}
mkdir -p $target_folder/$artifact_id/$artifact_id.app/Contents/Resources/Java/
cp ${target_folder}/${artifact_id}-${version}.jar $target_folder/$artifact_id/$artifact_id.app/Contents/Resources/Java/
