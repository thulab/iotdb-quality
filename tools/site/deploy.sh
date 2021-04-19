# Clean docs/
rm -rf docs/*
# Copy documents of master to docs/
cp ../../README.md docs/
cp -r ../../docs/* docs/
# Copy documents and downloadable files of V1.0.x to docs/
cd ..
cd ..
git checkout v1.0.x
mkdir ../tmp
mkdir ../tmp/download
cp -r docs/* ../tmp
cp README.md ../tmp/en
cp -r download/* ../tmp/download
git checkout master
mkdir tools/site/docs/V1.0.x
mkdir tools/site/docs/zh/V1.0.x
mkdir download/V1.0.x
cp -r ../tmp/en/* tools/site/docs/V1.0.x
cp -r ../tmp/zh/* tools/site/docs/zh/V1.0.x
# cp -r ../tmp/download/* download/V1.0.x
rm -rf ../tmp
cd tools/site
# Build the web pages
npm run docs:build
# Remove the copied documents
rm -rf docs/*
# Copy the web pages to the root
cd ..
cd ..
cp -r tools/site/docs/.vuepress/dist/* ./
# Upload to GitHub
git add -A
git commit -m "Update GitHub Pages"
git push origin master:GitHubPages
# Clean the root 
rm *.html
rm -rf assets/
rm -rf zh/
rm -rf en/
rm -rf V1.0.x/
# Commit the cleaning
git add -A
git commit -m "Clean Environment"