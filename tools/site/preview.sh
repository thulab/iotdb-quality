# Clean docs/
rm -rf docs/*
# Copy documents to docs/
cp ../../README.md docs/
cp -r ../../docs/* docs/
mkdir docs/V1.0.x
mkdir docs/zh/V1.0.x
cp -r V1.0.x/en/* docs/V1.0.x
cp -r V1.0.x/zh/* docs/zh/V1.0.x
# Build the web pages
npm run docs:dev