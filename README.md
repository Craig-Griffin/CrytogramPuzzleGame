# CS207 Sem2 Cryptography 

## Git Cheat Sheet
Lots of trial and error :P And google. Lots and lots of google.
I'm 90% sure that 90% of this is correct....

- To get info on the branch you're on and which files are different between your repo and the online one, run `git status`
- If you've really messed up on your local repo and want to nuke it all and start again from whatevers online, run `git reset --hard`. You **will** lose changes.
- 
### Things to never do
- Never use `git clone` again on a repo you have locally. If you really broke something and want to start totally fresh, then sure, delete your whole local repo and then clone again. But don't do it to a working repository.
- Similarly, never use `git init` on a local repo. Things will break. `git init` is only for creating a new repo from source files you already have, but that aren't part of a git repository.
- This is more of a style thing, but don't push broken code to `master`. 
- On the flipside, please do push as freqently as you can to dev or test branches so the rest of us can see what you're working on.

### Branches
It's good to have different branches so we can be working on (and breaking) things in one branch while the others are totally safe. Branches are unique to the repo, so even though there's a `master` branch on GitLab and you work in a `master` branch on your machine, they're totally separate. Whenever you push up to gitlab (or pull down), you're basically merging the two branches. 

The following is basically a summary of the official git docs, which you can read here: https://git-scm.com/book/en/v2/Git-Branching-Branches-in-a-Nutshell. Hopefully my version makes a little more sense?

**I want to checkout a branch from GitLab that I don't have locally yet**
- If you're pulling a branch that you've never checked out before, run `git checkout -b BRANCHNAME origin/BRANCHNAME`, where `BRANCHNAME` is the branch you want to switch to (usually `master` or `dev`). What's actually happening is the `-b BRANCHNAME` flag is telling git to make a new branch called `BRANCHNAME` locally, then switch to that new branch, then `pull` from the branch at `origin/BRANCHNAME` (ie, the branch BRANCHNAME on GitLab). Using the same name locally as the remote branch you're checking out is good for sanity.
- If you've already done `git checkout -b` on that branch before, then you've got it locally so all you have to do is `git checkout BRANCHNAME` to swap to it, and it's all set up to track the remote, so you can `git pull` any new changes as normal.

**I want to switch between branches that I have locally**
- Use `git checkout BRANCHNAME` to switch to a branch called `BRANCHNAME` that you have locally.
- You can `add`, `commit`, `push` and `pull` normally as if you were on `master`.
- Yes, it really is that simple. If you have a local branch which is tracking a remote branch on GitLab, then it all should just work.
- 
**I want to make a new local branch to work on and then push that branch up to GitLab (but as its own branch, not to merge into an existing branch)**
- To make a whole new branch on your machine, run `git branch BRANCHNAME`.
- To swap to your new branch, run `git checkout BRANCHNAME`
- Write code, ???, Profit?
- The first time you push, to create a new branch on gitlab with the same name, run `git push origin BRANCHNAME`. Now anyone can `checkout` that branch like any other.

**I want to work on something dangerous that may break everything**
AKA **I want to make a new local branch for testing a feature, then merge that branch into a different one before pushing**
- Preamble: it's good to have a few different branches on gitlab. We have `master` for rock-solid code, it's common to have `dev` for in-development (but working) code, and maybe more branches for features. Maybe you're working on a particular feature. Maybe you want it to give out **cookies**.
- First make a new local branch locally called "cookies-experimental", or something, with `git branch cookies-experimental` then swap to it with `git checkout cookies-experimental`.
- So far it's just the same as above. Do your work on your new branch, maybe push it up to gitlab with `git push origin cookies-experimental` if you're taking a while or want help.
- Eventually, you'll be happy with your feature and want to merge it into `dev`. Or maybe as a group we decide that dev is ready to merge into master. How to?
- **To merge two branches:** `checkout` the branch that you want to merge into (in this case, `dev`). Do a quick `git pull` to make sure you have the newest code. To merge in your commits from your test branch, run `git merge cookies-experimental`. Now you're on `dev`, and all your commits from the other branch have been imported. `push` as normal to update `dev` on GitLab
- Note: a merge is a special kind of commit

**What is origin? What is master?**
- `origin` is the identifier for the remote repository, ie GitLab. You'll only ever see it in relation to something to do with a data transfer to or from gitlab, like a `push` or a `pull`
- `master` is the default branch in any new repo. There's a master branch on GitLab, there's a master branch on my machine, and yours. Generally, code on `master` is treated like gold and should always compile and be correct.
- Therefore, `origin/master` refers to the master branch on GitLab

**Possible caveats**
- If you change some files but don't commit them, then try to swap branches, git will complain. You have 2 options:
  - If you broke something and just want a clean copy of whatever's on GitLab, run `git reset --hard` to nuke your changes. This will reset your local branch to the last commit, and discard any changes since then. Then, you can `pull` or `checkout` or whatever you were doing as normal. 
  - Or, you can `commit` your changes. You can switch branches merrily, and all you need to do is `checkout` that original branch and your committed changes will be back again. Remember though, `commit` and `checkout` only affect your local repository. Nothing will change on GitLab until you `push`.

### Adding and Committing
- To "stage" files for commit, you want to run `git add` on them. This tells git "hey, I want my changes to these files to be included in the next commit"
- You can do it file-by-file, ie `git add file1.c file2.c file3.c`, or the sledgehammer approach: `git add .`, which will add every file in the working directory, and any directories below.
- To commit changes, run `git commit -m "commit message here"`. Test often, commit often. Check that every small change works then commit it. Big commits are usually bad. Use informative commit messages. Generally don't commit broken code unless you're in your own test branch. Please please don't push broken code to `master`
- Good commit message example: `git commit -m "Implemented toString method on class Foo"`
- Bad commit message example: `git commit -m "more work on the Foo stuff"`

### Pushing and Pulling
- To push all your recent commits to the GitLab, run `git push`. Don't worry about `origin` or `master`, because git knows which remote branches your local ones are tracking. So if you're on `master` locally, then git will know to push to `master` on remote (ie, GitLab), so you can just use `git push`. the only time you really need to specify the remote and remote branch is when you're pushing a new branch, as explained above.
- If you get an error when pushing, it means someone else probably pushed before you and now your local branch is behind what's on GitLab. Run `git pull` to get the changes.
- In general, it's good to `git pull` whenever you load up to check for any new changes.
- If pulling gives you merge confilcts...fun times are ahead

### Merging
- Git is usually quite good at merging files together and keeping all the data. If someone adds a function and someone else refactors a different one in the same file, git will normally cope with that pretty well. But, if two people change the same line or thereabouts, it can get confused and will throw a Merge Conflict at us. If this happens, you need to communicate to find out which edit (if any) is the one to keep.