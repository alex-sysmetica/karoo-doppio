# Help build Doppio

The full guide for anyone who wants to help: what to put in an issue, how to build Doppio, and the rules for branches, commits and pull requests. Back to the [README](README.md).

- 🐞 **Found a bug?** [Report an issue](#-report-an-issue).
- 💡 **Have an idea for a new feature?** Open an issue first, so we agree on it before you write code.
- 🔀 **Have a fix?** [Open a pull request](#-open-a-pull-request) into `main` that links its issue.

## 🐞 Report an issue

Search the [open issues](../../issues) first. If yours is there, add to it. Issue forms are coming. Until then, please include:

- Your Karoo model. Doppio is for the Karoo 3 only.
- The Karoo software version.
- The Doppio version, shown on Doppio's Home page and on every page of the first-run introduction.
- What happened.
- What you expected to happen.
- The steps that make it happen.
- For anything visual: a screenshot or a photo of the Karoo, and whether the ride theme was light or dark.

Leave out personal data: no ride files, and crop maps and places out of your pictures.

## 🔀 Open a pull request

1. Find or open the issue for your change. For a new feature, wait for the maintainer's go-ahead on it.
2. Fork the repository and branch from `main`.
3. Make your change, in commits that follow the rules below.
4. Run the local check script (coming), which runs every test.
5. Open the pull request into `main` and fill in the description.
6. The maintainer reviews it and squash-merges it. Only the maintainer merges.

**Branches.** Nobody pushes to `main` directly: every change comes through a pull request. Branches on this repository must start with `feature/`, `bugfix/`, `refactor/` or `task/`. `release/` branches and tags are the maintainer's.

Name a branch `<type>/<n>-<slug>`, where `<n>` is the issue number and the slug is two to four lower-case words, for example `feature/42-wind-field`. Use the same names in a fork.

**Commits.**

- Subject: `[#<n>]: <Summary>`, for example `[#42]: Add the wind field`. Several issues: `[#42][#43]: …`. The maintainer's own work uses `KE-<n>` ids instead, as in `[KE-21]: Render the secondary value in grey`.
  - Starts with a verb in the imperative: Add, Fix, Remove, Rename, Update.
  - At most 72 characters, no full stop at the end.
- Blank line, then a body that says why the change was needed and anything a reader of the diff cannot see. Wrap it at 72 characters. Skip the body only when the subject says it all.
- One logical change per commit. Formatting-only changes go in their own commit.

**Title.** A squash merge turns the whole pull request into one commit on `main`. Its title becomes that commit's subject, and GitHub adds the pull request's number, as in `[#42]: Add the wind field (#57)`. Write the title in the commit format and keep it to about 64 characters, so the number still fits in 72.

**Description.** A pull request template is coming. Until then, the description must hold:

- What changed and why.
- The linked issue, for example `Closes #42`.
- For a visual change: before and after pictures, in the light and the dark ride theme.
- How you tested it: the tests you ran, and the Karoo and software version you tried it on.

## 🚫 What gets closed without review

We close these without review. You are welcome to open a new one once the gap is fixed.

**Issues**

- No description, or a title only.
- Anything visual without a screenshot or a photo of the Karoo.
- About the Karoo 2 or any device other than the Karoo 3.
- A duplicate of an open issue. We link the open one.
- A support question that is not about Doppio: the Karoo itself, your Hammerhead account or another extension. Ask Hammerhead's support about the Karoo and your account, and the other extension's authors about theirs.

**Pull requests**

- No description, or one that does not say what changed and why.
- No linked issue.
- A visual change without before and after pictures, in the light and dark ride themes.
- A new feature the maintainer has not agreed to in its issue.
- Several unrelated changes in one pull request.
- Private data: keys, tokens, passwords, e-mail addresses, absolute paths from your machine, device serial numbers or ride files.
- Generated code, from an AI assistant or any other tool, that the author has not read, built and tested.
- Code that does not build, or whose tests fail.
- A change that breaks the native look, in the light or dark ride theme, or at any cell size the Karoo offers.
- Support for the Karoo 2 or other devices.
- Changes to version numbers, releases, signing or tags. These are the maintainer's.
- Third-party code, fonts or pictures without a licence that allows them here.
- A duplicate of an open pull request.

## 🔧 Build it yourself

> 🚧 Under development. The JDK version, the build commands and a local check script that runs every test will be listed here.

After cloning, turn on the repository's git hooks once:

```bash
git config core.hooksPath .githooks
```

They check the commit message format and stop keys, tokens and personal data from being committed.

Commit with a GitHub noreply address (`<id>+<user>@users.noreply.github.com`). The pre-commit hook refuses any other author e-mail.

There is no CI service.

## 🏗️ Architecture

> 🚧 Under development. The project skeleton is in place; the features are being built. A fuller description of each module will follow.

Eight Gradle modules:

- `:karoo-ext`: Hammerhead's karoo-ext SDK, vendored: a copy of its source lives in this repository, so no package token is needed.
- `:karoo-doppio-kit`, `:karoo-doppio-ui`, `:karoo-doppio-ui-android`: reusable kits.
- `:app-core`, `:app-shell`, `:app-field`, `:app`: Doppio's own app.

## 📐 Code conventions

- Kotlin, 4-space indent, 100-character lines (`.editorconfig`).
- Keep logic that needs no Android classes in plain Kotlin, so it can be unit-tested on the JVM.
- Fields must look native in light and dark, at every cell size the Karoo offers.

The full list is in [CLAUDE.md](CLAUDE.md).
