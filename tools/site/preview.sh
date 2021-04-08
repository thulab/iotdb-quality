# Clean docs/
rm -rf docs/*
# Copy documents of master to docs/
cp ../../README.md docs/
cp -r ../../docs/* docs/
# Copy documents of V1.0.x to docs/
cd ..
cd ..
git checkout v1.0.x
mkdir ../tmp
cp -r docs/* ../tmp
cp README.md ../tmp/en
git checkout master
mkdir tools/site/docs/V1.0.x
mkdir tools/site/docs/zh/V1.0.x
cp -r ../tmp/en/* tools/site/docs/V1.0.x
cp -r ../tmp/zh/* tools/site/docs/zh/V1.0.x
rm -rf ../tmp
cd tools/site
# Build the web pages
npm run docs:dev