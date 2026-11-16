# karoo-double-fields

Public repository. Everything committed here is visible to everyone and stays in the history.

## Never commit

- Signing keys, passwords, tokens, `.env` files, `local.properties`, `keystore.properties`.
- Personal data: e-mail addresses, device serial numbers, account names, absolute paths from a developer's machine.
- Links to private boards, private repositories or internal documents.
- Research notes, plans and design explorations. Only code and user-facing docs belong here.

The hooks in `.githooks/` catch the common cases. They are a safety net, not a substitute for reading the diff before committing.

## Setup

```bash
git config core.hooksPath .githooks
```

Commit with a GitHub noreply address (`<id>+<user>@users.noreply.github.com`). The pre-commit hook refuses any other author e-mail.

## Commits

- Subject: `[KE-<n>]: <Summary>`, for example `[KE-21]: Render the secondary value in grey`.
  - Starts with a verb in the imperative: Add, Fix, Remove, Rename, Update.
  - At most 72 characters, no full stop at the end.
  - Several tickets: `[KE-21][KE-22]: …`.
- Blank line, then a body that says why the change was needed and anything a reader of the diff cannot see. Wrap at 72 characters. Skip the body only when the subject says it all.
- One logical change per commit. Formatting-only changes go in their own commit.
- No private details in the message either: the history is as public as the code.

## Branches

`<type>/KE-<n>-<slug>`, where type is `feature`, `bugfix` or `task`, and the slug is two to four lower-case words: `feature/KE-21-secondary-value`. Branch from `main`, open the pull request into `main`.

## Code

- Kotlin, 4-space indent, 100-character lines (`.editorconfig`).
- Keep logic that needs no Android classes in plain Kotlin so it can be unit-tested on the JVM.
- Fields must look native in light and dark, at every cell size the Karoo offers.
