# Clean docs/
rm -rf docs/*
# Copy documents of master to docs/
mkdir docs/en/
mkdir docs/en/InProgress/
mkdir docs/zh/
mkdir docs/zh/InProgress/
cp ../../README.md docs/en/InProgress/
cp -r ../../docs/en/* docs/en/InProgress/
mv docs/en/InProgress/Download.md docs/en/Download.md
cp -r ../../docs/zh/* docs/zh/InProgress/
mv docs/zh/InProgress/Download.md docs/zh/Download.md
# Copy documents of V1.0.x to docs/
cd ..
cd ..
git checkout v1.0.x
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
# Commit the cleaning
git add -A
git commit -m "Clean Environment"