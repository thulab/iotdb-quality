cp ../README.md docs/
cp ../README_zh.md docs/
cp -r ../docs/ docs/
npm run docs:build
rm -rf !(.vuepress/)
cd ..
git add -A
git commit -m "Update GitHub Pages"
git push master:gh-pages

