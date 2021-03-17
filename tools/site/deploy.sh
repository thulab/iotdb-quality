# Clean docs/
rm -rf docs/*
# Copy documents to docs/
cp ../../README.md docs/
cp -r ../../docs/* docs/
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