![Doppio for Karoo 3, a double shot of data: the app icon, a glass of espresso in front of a snowy peak, beside two example Karoo data fields. In the Baseline style the average speed 21.5 sits beside the speed 27.8; in the Header style it sits on top, under the name. Cycling snacks and parts lie around the edges](docs/images/header.webp)

# Karoo Doppio

**Two shots of data in one field.** Doppio will add ten data fields to the [Hammerhead Karoo 3](https://www.hammerhead.io/) that look like the Karoo's own. Your main value stays big. A second one rides along beside it, or on top of it. *Doppio* is Italian for double.

> **Status:** in development. The design is done and the features are being built. Nothing is released yet, so there is nothing to install.

Want to help build it? See [Help build Doppio](#help-build-doppio).

## What Doppio does

1.0.0, the first release, will bring:

- **Ten fields, all yours to set up.** Blend 1 to Blend 10. Each shows a main value and a second value in one cell.
- **Two styles.** *Baseline* puts the second value beside the main value, on its baseline. *Header* puts it on top, under the field's name.
- **Single shots.** Just one value? Pour a single shot. It wears the same style, font and colour as your doubles.
- **House blends to start from.** 37 ready-made pairs. Pour a house blend, or brew your own.
- **Your style.** Twelve colours, font settings (how long names fit, one main size for all fields or one per field) and zone colours: heart-rate and power zones can tint the values.
- **The Karoo's own look.** Fields look native in the light and dark ride themes.
- **Set up on the Karoo.** Everything is set up in an app on the Karoo itself, with a short introduction.
- **Five languages.** English, Deutsch (German), Polski (Polish), Українська (Ukrainian) and 日本語 (Japanese).

![Doppio's menu, like a café board. House styles: six example fields showing lap time 12:34 with ride time 45:07, each in another style and colour (Baseline; Baseline, second in colour; Baseline, second first; Header, left; Header, right; Header, above the name). Colours: twelve to match your kit, from Karoo green to Doppio brown. House blends: ready-made pairs such as Sunset with ETA, "Home before dark?", or brew your own from any two values](docs/images/promo-styles.webp)

## Fields and values

Doppio 1.0.0 will add ten fields, **Blend 1** to **Blend 10**. Each one takes a main value and a second value, picked from 57 values in 11 groups, or a single value on its own. These are the values planned for 1.0.0.

<details>
<summary><b>All 57 values, in 11 groups</b></summary>

| Group | Values |
|---|---|
| Speed | Speed, Avg speed, Max speed |
| Power | Power, 3s power, 10s power, Avg power, Max power, W/kg, Power zone |
| Heart rate | Heart rate, Avg HR, HR zone |
| Cadence | Cadence, Avg cadence |
| Climb | Grade, Grade to top, Climb height, Time to top, Elevation, Ascent, Ascent remaining, To top, Height to top, VAM |
| Distance | Distance |
| Time | Ride time, Total time, Paused time, Time of day, Sunrise, Sunset, Time to sunset |
| Navigation | To destination, Time to destination, ETA, To POI, Time to POI, ETA at POI |
| Battery | Battery, Battery left, Drain |
| Workout | Target power, Target HR, Interval time left, Interval |
| Lap | Lap avg speed, Lap max speed, Last lap speed, Lap avg power, Last lap power, Lap avg HR, Last lap HR, Lap avg cadence, Lap distance, Lap time, Last lap time |

</details>

Wind, AXS gears, tyre pressure and more are still roasting. Look out for them in later updates.

## House blends

Pour a house blend, or brew your own. 1.0.0 will come with 37 house blends: ready-made pairs in 8 categories. Start from one as it is, or change either value.

<details>
<summary><b>All 37 house blends</b></summary>

**Speed**

| Main value | Second value | What it shows |
|---|---|---|
| Speed | Avg speed | Now against the whole ride |
| Lap avg speed | Avg speed | This lap against the whole ride |
| Lap avg speed | Last lap speed | This lap against the last one |
| Speed | Max speed | Now, and the ride’s top speed |

**Power**

| Main value | Second value | What it shows |
|---|---|---|
| 3s power | Avg power | Now against the whole ride |
| 3s power | Power zone | Power with its zone |
| 3s power | W/kg | Power to weight |
| Lap avg power | Last lap power | This lap against the last one |
| 10s power | Lap avg power | A steadier now against the lap |
| Power | Max power | Now, and the ride’s peak |
| 3s power | Target power | On target? In a workout |

**Heart rate**

| Main value | Second value | What it shows |
|---|---|---|
| Heart rate | Avg HR | Now against the whole ride |
| Heart rate | HR zone | Heart rate with its zone |
| Lap avg HR | Last lap HR | This lap against the last one |
| Heart rate | Target HR | On target? In a workout |

**Cadence**

| Main value | Second value | What it shows |
|---|---|---|
| Cadence | Avg cadence | Now against the whole ride |

**Climb**

| Main value | Second value | What it shows |
|---|---|---|
| Ascent remaining | Ascent | Still to climb, and climbed so far |
| Ascent remaining | To top | The day’s climbing left, and this climb’s top |
| To top | Height to top | What is left of this climb |
| Grade | To top | How steep, and how far to the top |
| Grade | VAM | How steep, and how fast you climb |
| Elevation | Ascent | Where you are, and what you climbed |

**Trip**

| Main value | Second value | What it shows |
|---|---|---|
| Distance | To destination | Done, and still to ride |
| To destination | Distance | Still to ride, and done |
| To destination | ETA | Still to ride, and when you get there |
| To POI | ETA at POI | The next stop, and when you reach it |
| To POI | To destination | The next stop, and the finish |

**Time**

| Main value | Second value | What it shows |
|---|---|---|
| Lap time | Ride time | This lap and the whole ride |
| Lap time | Last lap time | This lap against the last one |
| Total time | Time to destination | Out so far, and still to ride |
| Total time | Paused time | Out so far, and how long stopped |
| Sunset | ETA | Home before dark? |
| Time to sunset | Time to destination | Daylight left against riding left |
| Interval time left | Interval | In a workout: this rep, and which one |

**Battery**

| Main value | Second value | What it shows |
|---|---|---|
| Battery | Battery left | How full, and how many hours it lasts |
| Battery | Drain | How full, and how fast it empties |
| Time to destination | Battery left | Will the Karoo last to the finish? |

</details>

## Install

> 🚧 Under development. Nothing is released yet. The steps below are the plan for 1.0.0.

**Compatibility:** Karoo 3 only.

Install or update only while no ride is recording.

### From your phone

1. On your phone, open the [latest release](../../releases/latest) on GitHub.
2. Share the `doppio.apk` link to the Hammerhead Companion app.
3. The Companion app installs Doppio on your paired Karoo.

### With adb

For developers: download `doppio.apk` from the [latest release](../../releases/latest), connect the Karoo with adb, then run:

```bash
adb install doppio.apk
```

### Add a field to a ride page

On the Karoo:

1. Open Profiles, then a profile and a data page.
2. Tap the field you want to swap.
3. Choose Doppio, then one of its blends.

### Updates

When a new version is out, the Karoo shows an update card. Tap it, then **UPDATE** on the Karoo's app page. A switch in Doppio's Settings turns update notices off.

## Privacy

No analytics. The only thing Doppio will send is an update check to GitHub, and you can switch it off in Settings.

## What's new

### 1.0.0: The first pour

> 🚧 Not released yet.

- Ten fields, all yours to set up
- House blends to start from
- Baseline and Header styles

---

## Help build Doppio

Found a bug? [Open an issue](../../issues/new). Have a fix? Open a pull request into `main` that links its issue. Have an idea for a new feature? Open an issue first, so we agree on it before you write code.

### Architecture

> 🚧 Under development. The project skeleton is in place; the features are being built. A fuller description of each module will follow.

Eight Gradle modules:

- `:karoo-ext`: Hammerhead's karoo-ext SDK, vendored: a copy of its source lives in this repository, so no package token is needed.
- `:karoo-doppio-kit`, `:karoo-doppio-ui`, `:karoo-doppio-ui-android`: reusable kits.
- `:app-core`, `:app-shell`, `:app-field`, `:app`: Doppio's own app.

### Build it yourself

> 🚧 Under development. The JDK version, the build commands and a local check script that runs every test will be listed here.

After cloning, turn on the repository's git hooks once:

```bash
git config core.hooksPath .githooks
```

They check the commit message format and stop keys, tokens and personal data from being committed.

Commit with a GitHub noreply address (`<id>+<user>@users.noreply.github.com`). The pre-commit hook refuses any other author e-mail.

There is no CI service.

### Report an issue

Search the [open issues](../../issues) first. If yours is there, add to it. Issue forms are coming. Until then, please include:

- Your Karoo model. Doppio is for the Karoo 3 only.
- The Karoo software version.
- The Doppio version, shown on Doppio's Home page and on every page of the first-run introduction.
- What happened.
- What you expected to happen.
- The steps that make it happen.
- For anything visual: a screenshot or a photo of the Karoo, and whether the ride theme was light or dark.

Leave out personal data: no ride files, and crop maps and places out of your pictures.

### Open a pull request

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

### What gets closed without review

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

### Code conventions

- Kotlin, 4-space indent, 100-character lines (`.editorconfig`).
- Keep logic that needs no Android classes in plain Kotlin, so it can be unit-tested on the JVM.
- Fields must look native in light and dark, at every cell size the Karoo offers.

The full list is in [CLAUDE.md](CLAUDE.md).

## Credits

- Built on Hammerhead's [karoo-ext](https://github.com/hammerheadnav/karoo-ext) SDK.
- The native look of [karoo-mountaineer](https://github.com/Dubadai/karoo-mountaineer) and [karoo-betterintervals](https://github.com/Dubadai/karoo-betterintervals) set the bar.

## Not affiliated

Doppio is an independent project. It is not affiliated with Hammerhead or SRAM. Karoo, Hammerhead and AXS are trademarks of SRAM.

## Licence

Doppio's own code is under the [MIT licence](LICENSE). Hammerhead's karoo-ext SDK, which Doppio carries as the `:karoo-ext` module, keeps its own Apache License 2.0. [THIRD_PARTY_NOTICES.md](THIRD_PARTY_NOTICES.md) lists every third-party part and its licence.
