# Clean docs/
rm -rf docs/*
# Copy documents to docs/
cp ../README.md docs/
cp -r ../docs/* docs/
# Build the web pages
npm run docs:dev