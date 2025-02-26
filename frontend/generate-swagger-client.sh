#!/bin/bash

DIRECTORY="./src/libs/generated/typescript-angular"  # Use a relative path to your local directory
DIST_DIRECTORY="./dist"  # Directory to store the YAML file
YAML_FILE="$DIST_DIRECTORY/api-docs.yaml"  # The file name for the YAML

# Default URL if no argument is provided
DEFAULT_URL="http://localhost:8080/v3/api-docs.yaml"

# Use the provided argument or fallback to the default URL
INPUT_URL="${1:-$DEFAULT_URL}"

# Ensure the dist directory is in your local file system
if [ ! -d "$DIST_DIRECTORY" ]; then
  echo "Creating dist directory: $DIST_DIRECTORY"
  mkdir -p "$DIST_DIRECTORY"
fi

echo "Fetching the OpenAPI YAML from $INPUT_URL"
curl -o "$YAML_FILE" "$INPUT_URL"  # Fetch the YAML and save it in the dist directory

# Ensure the directory is in your local file system
if [ -d "$DIRECTORY" ]; then
  echo "Deleting directory: $DIRECTORY"
  rm -rf "$DIRECTORY"
fi

echo "Making directory: $DIRECTORY"
mkdir -p "$DIRECTORY"

echo "Using the YAML file from $YAML_FILE"

npm uninstall @openapitools/openapi-generator-cli
npm install @openapitools/openapi-generator-cli

echo "Start of run openapi generator cli command"
# Run the Swagger Codegen to generate the library using the YAML file from the dist folder
./node_modules/.bin/openapi-generator-cli generate \
  -i "$YAML_FILE" \
  -g typescript-angular \
  -o "$DIRECTORY" \
  --additional-properties ngVersion=17.3.0 \
  --enable-post-process-file

echo "End of run openapi generator cli command"
