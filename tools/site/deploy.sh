# Clean docs/
rm -rf docs/*
# Copy documents of master to docs/
git checkout v2.0
mkdir docs/en/
mkdir docs/en/V2.0.x/
mkdir docs/zh/
mkdir docs/zh/V2.0.x/
cp ../../README.md docs/en/V2.0.x/
cp -r ../../docs/en/* docs/en/V2.0.x/
mv docs/en/V2.0.x/Download.md docs/en/Download.md
cp -r ../../docs/zh/* docs/zh/V2.0.x/
mv docs/zh/V2.0.x/Download.md docs/zh/Download.md
# Copy documents of V1.0.x to docs/
cd ..
cd ..
git checkout master
mkdir ../tmp
cp -r docs/* ../tmp
cp README.md ../tmp/en
git checkout master
mkdir tools/site/docs/en/V1.0.x
mkdir tools/site/docs/zh/V1.0.x
cp -r ../tmp/en/* tools/site/docs/en/V1.0.x
cp -r ../tmp/zh/* tools/site/docs/zh/V1.0.x
rm -rf ../tmp

cd tools/site
# Copy the index web page
cp index.txt docs/
mv docs/index.txt docs/README.md
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
# Commit and upload the cleaning
git add -A
git commit -m "Clean Environment"
git push origin master