#!/bin/bash

DIRECTORY="./src/app/shared/generated"  # Use a relative path to your local directory

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
  swaggerapi/swagger-codegen-cli-v3 generate \
  -i http://host.docker.internal:8080/v3/api-docs \
  -l typescript-axios \
  -o /local/src/app/shared/generated \