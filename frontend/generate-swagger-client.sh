#!/bin/bash

DIRECTORY=""

# Check if the directory exists
if [ -d "$DIRECTORY" ]; then
  echo "Deleting directory: $DIRECTORY"
  rm -rf "$DIRECTORY"
fi
echo "Making directory: "$DIRECTORY""
mkdir -p "$DIRECTORY"

docker run --rm \
  -v $(pwd):/local \
  swaggerapi/swagger-codegen-cli-v3 generate \
  -i http://host.docker.internal:8080/v3/api-docs \
  -l typescript-axios \
  -o /local/src/app/shared/api