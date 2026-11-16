# Karoo Double Fields

Data fields for the [Hammerhead Karoo](https://www.hammerhead.io/) that show two values in one cell and look like the Karoo's own.

> **Status:** early design. There is nothing to install yet.

## What it will do

- **Double fields.** A main value in the native style with a smaller grey companion beside it, for example lap average speed next to ride average speed.
- **Tap actions.** Choose what a tap on a field does: nothing, show the map, zoom, and more.
- **On-device app.** Set everything up on the Karoo itself, with a short introduction that shows how it works.

## Development

After cloning, turn on the repository's git hooks once:

```bash
git config core.hooksPath .githooks
```

They check the commit message format and stop keys, tokens and personal data from being committed. See [CLAUDE.md](CLAUDE.md) for the conventions.

## Credits

- Built on Hammerhead's [karoo-ext](https://github.com/hammerheadnav/karoo-ext) SDK.
- The native look of [karoo-mountaineer](https://github.com/Dubadai/karoo-mountaineer) and [karoo-betterintervals](https://github.com/Dubadai/karoo-betterintervals) set the bar.

## Licence

[MIT](LICENSE)
