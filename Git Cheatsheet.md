# Git Cheatsheet

## Basics
```
# Add files to staging
git add test.md test2.md //adding multiple files
git add . // adding all except the files in .gitignore

# Commit files
git commit -m "add test.md" //commit staged files with message

# check staging status
git status

# check commit history
git log // detailed commit history

git log --graph --oneline // show 1 line graph

# push to remote repo
git push

# pull from remote(fetch and merge)
git pull
```



## Advance
```
# restore a file(remove your uncommited work)
git restore FILE_NAME

# Undo staging
git reset -- test.md // opposite of git add

# Undo commit
git reset --soft HEAD^ // undo commit and keep the files as it is
git reset --hard HEAD~3 // undo 3 commits and delete all changes permanently

# create branch
git branch new_branch
git checkout -b new_branch // create and checkout

# checkout(go to) branch
git checkout newbranch

# delete branch
git branch -d new_branch

```

