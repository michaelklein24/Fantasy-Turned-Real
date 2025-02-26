#!/bin/bash

DIRECTORY="./src/libs/generated/typescript-angular"  # Use a relative path to your local directory

# Default URL if no argument is provided
DEFAULT_URL="http://localhost:8080/v3/api-docs.yaml"

# Use the provided argument or fallback to the default URL
INPUT_URL="${1:-$DEFAULT_URL}"

# Ensure the directory is in your local file system
if [ -d "$DIRECTORY" ]; then
  echo "Deleting directory: $DIRECTORY"
  rm -rf "$DIRECTORY"
fi
echo "Making directory: $DIRECTORY"
mkdir -p "$DIRECTORY"

# Run the Swagger Codegen to generate the library
./node_modules/.bin/openapi-generator-cli generate \
  -i "$INPUT_URL" \
  -g typescript-angular \
  -o ./src/libs/generated/typescript-angular \
  --additional-properties ngVersion=17.3.0 \
  --enable-post-process-file