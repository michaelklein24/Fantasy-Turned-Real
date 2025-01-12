#!/bin/bash

DIRECTORY="./src/libs/generated/typescript-angular"  # Use a relative path to your local directory

# Ensure the directory is in your local file system
if [ -d "$DIRECTORY" ]; then
  echo "Deleting directory: $DIRECTORY"
  rm -rf "$DIRECTORY"
fi
echo "Making directory: $DIRECTORY"
mkdir -p "$DIRECTORY"

# Run the Swagger Codegen to generate the library
docker run --rm \
  -v $(pwd):/local \
  openapitools/openapi-generator:cli-6.0.x generate \
  -i http://host.docker.internal:8080/v3/api-docs.yaml \
  -g typescript-angular \
  -o /local/src/libs/generated/typescript-angular \