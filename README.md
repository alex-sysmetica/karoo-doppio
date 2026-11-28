![Doppio for Karoo 3, a double shot of data: the app icon, a glass of espresso in front of a snowy peak, beside two example Karoo data fields. In the Baseline style the average speed 21.5 sits beside the speed 27.8; in the Header style it sits on top, under the name. Cycling snacks and parts lie around the edges](docs/images/header.webp)

# Karoo Doppio

**Two shots of data in one field.** Doppio will add ten data fields to the [Hammerhead Karoo 3](https://www.hammerhead.io/) that look like the Karoo's own. Your main value stays big. A second one rides along beside it, or on top of it. *Doppio* is Italian for double.

> **Status:** in development. The design is done and the features are being built. Nothing is released yet, so there is nothing to install.

Want to help build it? See [Help build Doppio](#-help-build-doppio).

## ✨ What Doppio does

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

## 🔢 Fields and values

Doppio 1.0.0 will add ten fields, **Blend 1** to **Blend 10**. Each one takes a main value and a second value, picked from 57 values in 11 groups, or a single value on its own. These are the values planned for 1.0.0.

<details>
<summary><b>All 57 values, in 11 groups</b></summary>

| Group | Values |
|---|---|
| 🚴 Speed | Speed, Avg speed, Max speed |
| ⚡ Power | Power, 3s power, 10s power, Avg power, Max power, W/kg, Power zone |
| ❤️ Heart rate | Heart rate, Avg HR, HR zone |
| 🔄 Cadence | Cadence, Avg cadence |
| ⛰️ Climb | Grade, Grade to top, Climb height, Time to top, Elevation, Ascent, Ascent remaining, To top, Height to top, VAM |
| 📏 Distance | Distance |
| ⏱️ Time | Ride time, Total time, Paused time, Time of day, Sunrise, Sunset, Time to sunset |
| 🧭 Navigation | To destination, Time to destination, ETA, To POI, Time to POI, ETA at POI |
| 🔋 Battery | Battery, Battery left, Drain |
| 🏋️ Workout | Target power, Target HR, Interval time left, Interval |
| 🏁 Lap | Lap avg speed, Lap max speed, Last lap speed, Lap avg power, Last lap power, Lap avg HR, Last lap HR, Lap avg cadence, Lap distance, Lap time, Last lap time |

</details>

Wind, AXS gears, tyre pressure and more are still roasting. Look out for them in later updates.

## ☕ House blends

Pour a house blend, or brew your own. 1.0.0 will come with 37 house blends: ready-made pairs in 8 categories. Start from one as it is, or change either value.

<details>
<summary><b>All 37 house blends</b></summary>

**🚴 Speed**

| Main value | Second value | What it shows |
|---|---|---|
| Speed | Avg speed | Now against the whole ride |
| Lap avg speed | Avg speed | This lap against the whole ride |
| Lap avg speed | Last lap speed | This lap against the last one |
| Speed | Max speed | Now, and the ride’s top speed |

**⚡ Power**

| Main value | Second value | What it shows |
|---|---|---|
| 3s power | Avg power | Now against the whole ride |
| 3s power | Power zone | Power with its zone |
| 3s power | W/kg | Power to weight |
| Lap avg power | Last lap power | This lap against the last one |
| 10s power | Lap avg power | A steadier now against the lap |
| Power | Max power | Now, and the ride’s peak |
| 3s power | Target power | On target? In a workout |

**❤️ Heart rate**

| Main value | Second value | What it shows |
|---|---|---|
| Heart rate | Avg HR | Now against the whole ride |
| Heart rate | HR zone | Heart rate with its zone |
| Lap avg HR | Last lap HR | This lap against the last one |
| Heart rate | Target HR | On target? In a workout |

**🔄 Cadence**

| Main value | Second value | What it shows |
|---|---|---|
| Cadence | Avg cadence | Now against the whole ride |

**⛰️ Climb**

| Main value | Second value | What it shows |
|---|---|---|
| Ascent remaining | Ascent | Still to climb, and climbed so far |
| Ascent remaining | To top | The day’s climbing left, and this climb’s top |
| To top | Height to top | What is left of this climb |
| Grade | To top | How steep, and how far to the top |
| Grade | VAM | How steep, and how fast you climb |
| Elevation | Ascent | Where you are, and what you climbed |

**🗺️ Trip**

| Main value | Second value | What it shows |
|---|---|---|
| Distance | To destination | Done, and still to ride |
| To destination | Distance | Still to ride, and done |
| To destination | ETA | Still to ride, and when you get there |
| To POI | ETA at POI | The next stop, and when you reach it |
| To POI | To destination | The next stop, and the finish |

**⏱️ Time**

| Main value | Second value | What it shows |
|---|---|---|
| Lap time | Ride time | This lap and the whole ride |
| Lap time | Last lap time | This lap against the last one |
| Total time | Time to destination | Out so far, and still to ride |
| Total time | Paused time | Out so far, and how long stopped |
| Sunset | ETA | Home before dark? |
| Time to sunset | Time to destination | Daylight left against riding left |
| Interval time left | Interval | In a workout: this rep, and which one |

**🔋 Battery**

| Main value | Second value | What it shows |
|---|---|---|
| Battery | Battery left | How full, and how many hours it lasts |
| Battery | Drain | How full, and how fast it empties |
| Time to destination | Battery left | Will the Karoo last to the finish? |

</details>

## 📲 Install

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

## 🔒 Privacy

No analytics. The only thing Doppio will send is an update check to GitHub, and you can switch it off in Settings.

## 🆕 What's new

### 1.0.0: The first pour

> 🚧 Not released yet.

- Ten fields, all yours to set up
- House blends to start from
- Baseline and Header styles

[![All releases](https://img.shields.io/badge/%F0%9F%93%9C_All_releases-%E2%86%92-8B5A2B?style=for-the-badge)](CHANGELOG.md)

---

## 🧰 Help build Doppio

- 🐞 **Found a bug?** [Open an issue](../../issues/new). The guide says [what to put in it](CONTRIBUTING.md#-report-an-issue).
- 💡 **Have an idea?** [Open an issue](../../issues/new) first, so we agree on it before anyone writes code.
- 🔀 **Want to help build it?** Open a pull request into `main` that links its issue.

📖 Read the [full guide](CONTRIBUTING.md) before you start: how to build Doppio, the rules for branches, commits and pull requests, and what gets closed without review.

## 🙏 Credits

- Built on Hammerhead's [karoo-ext](https://github.com/hammerheadnav/karoo-ext) SDK.
- The native look of [karoo-mountaineer](https://github.com/Dubadai/karoo-mountaineer) and [karoo-betterintervals](https://github.com/Dubadai/karoo-betterintervals) set the bar.

## 🏷️ Not affiliated

Doppio is an independent project. It is not affiliated with Hammerhead or SRAM. Karoo, Hammerhead and AXS are trademarks of SRAM.

## 📄 Licence

Doppio's own code is under the [MIT licence](LICENSE). Hammerhead's karoo-ext SDK, which Doppio carries as the `:karoo-ext` module, keeps its own Apache License 2.0. [THIRD_PARTY_NOTICES.md](THIRD_PARTY_NOTICES.md) lists every third-party part and its licence.
