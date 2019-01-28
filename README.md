# CS207 Sem2 Cryptography 
On the Linux lab PCs, Git and Maven are already installed. If you don't have them on your own computer, you'll probably have to fix that ;) If you use Windows...godspeed. idk, you can probably use Git Bash or some GUI tools on Windows.

## Initial Set-Up
- You only need to do the following **once**.
- `cd` to some directory like `~Uni/CS207`
- Run `git clone https://gitlab.cis.strath.ac.uk/gxb16146/cryptogram.git`
- Type in your strath username and password when asked
- If you started in `~Uni/CS207`, your project folder will be in `~Uni/CS207/cryptogram`
- Next step depends on your IDE

### Setup in Intellij
- Open Intellij
- If a previous project opened, go to `File` -> `Close Project`
- On the splash screen, click Import Project
- Navigate to the `cryptogram` folder you cloned and select `pom.xml` (the maven project file)
- On the import menu you get, make sure `Enable auto-import`, or whatever it's called is checked. Makes life easy if we ever change the `pom.xml`
- To force Intellij to let Maven handle compling, building and running code, go to `File` -> `Settings` -> `Build, Execution, Deployment` -> `Build Tools` -> `Maven` -> `Runner` and check `Delegate IDE build actions to maven`

### Setup in Eclipse
- Something similar...probably??

## Using Git
Lots of trial and error :P And google. Lots and lots of google.

- To get info on the branch you're on and which files are different between your repo and the online one, run `git status`
- If you've really messed up on your local repo and want to nuke it all and start again from whatevers online, run `git reset --hard`. You **will** lose changes.

### Committing and Pushing
- To commit changes, run `git commit -m "commit message here"`. Test often, commit often. Check that every small change works then commit it. Big commits are usually bad. Use informative commit messages.
- Good commit example: `git commit -m "Implemented toString method on class Foo"`
- Bad commit example: `git commit -m "more work on the Foo stuff"`
- To push all your recent commits to the GitLab, run `git push`. 
- If you get an error when pushing, it means someone else probably pushed before you and now your local branch is behind what's on GitLab. Run `git pull` to get the changes.
- In general, it's good to `git pull` whenever you load up to check for any new changes.
- If pulling gives you merge confilcts...fun times are ahead

### Branches
It's good to have different branches so we can be working on (and breaking) things in one branch while the others are totally safe. Branches are unique to the repo, so even though there's a `master` branch on GitLab and you work in a `master` branch on your machine, they're essentially separate. Whenever you push up to gitlab (or pull down), you're basically merging the two branches. I've made a `dev` branch on github for us to use. It's a smallish project, 2 should be enough to keep it clean, I think. Anyone can make any amount of them, as needed.

- The following is basically a summary of the official git docs, which you can read here: https://git-scm.com/book/en/v2/Git-Branching-Branches-in-a-Nutshell. Hopefully my version makes a little more sense?
- If you're pulling a branch that you've never checked out before, run `git checkout -b BRANCHNAME origin/BRANCHNAME`, where `BRANCHNAME` is the branch you want to switch to (usually `master` or `dev`). What's actually happening is the first `BRANCHNAME` after the `-b` is a new branch you're creating on your machine, then `origin/BRANCHNAME` is the branch on GitLab that you're copying in. Keeping them both with the exact same name is good for sanity.
- If you've already done `git checkout -b` on that branch before, then you've got it locally, so all you have to do is `git checkout BRANCHNAME` to swap to it, then you can `git pull` any new changes as normal.
- If that worked right, you can commit and push and pull and do all the usual stuff on your new branch.
- If you change some files but don't commit them, then try to swap branches, git will complain. Either commit before swapping, or run `git reset --hard` to nuke your changes and bring up the new branch.
- To make a whole new branch on your machine, run `git branch BRANCHNAME`. The first time you push up, to create a new branch on gitlab with the same name, run `git push origin BRANCHNAME`. Now anyone can `checkout` that branch like any other.
- I guess we'll discuss as a group every so often what's in the different branches and what to merge into `master` and that kind of thing as part of the agile process, I think. I'm kindof realising we(I?) are jumping kindof far ahead. Oh well
- **TL;DR** Run `git checkout -b dev origin/dev` once you clone the repo, and then forget that branches even exist :P

## Using Maven
- Running `mvn package` from the project folder will build a jar file into the `target` folder.
- That's about the only command we'll probably ever use, and we'll probably only use it once or twice. Intellij more or less does all the work for us after it's set up.
- If you actually care, you can read more about Maven here: https://maven.apache.org/guides/getting-started/