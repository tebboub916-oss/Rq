# Resident Evil–inspired Survival Theme (Android starter)

A small native Android app for Samsung Galaxy A56 5G. It can set the included wallpaper and provides an animated visual-effects toggle and a simple local tone toggle.

## Important limitations
- Android does not allow an ordinary app to silently replace all system icons or widgets. Use Samsung Good Lock / Theme Park (availability varies by region/device) or a compatible launcher/icon pack for deeper customization.
- The visual animation runs only inside this app, not over the whole home screen.
- The wallpaper button currently applies the image to the Home screen. The second button is a placeholder label and also applies Home wallpaper because lock-screen behavior varies by Android version/OEM.
- This is a fan-made, unofficial theme. No official Resident Evil music or game assets are bundled.

## Build with Android Studio
1. Install Android Studio on a computer.
2. Extract this ZIP.
3. In Android Studio choose **Open** and select the `ResidentEvilTheme` folder.
4. Allow Gradle sync. Use JDK 17 if Android Studio asks.
5. Connect your Galaxy A56 5G with USB debugging enabled (Settings > About phone > tap Build number 7 times; then Developer options > USB debugging).
6. Press **Run** in Android Studio and select your phone. Alternatively build an APK via **Build > Build Bundle(s) / APK(s) > Build APK(s)**.
7. If you build an APK, transfer it to your phone and open it. Allow installation from that source if Android prompts you.

## Project details
- Minimum Android: 8.0 (API 26)
- Compile/target SDK: 35
- Package: `com.example.survivaltheme`
- Wallpaper: `app/src/main/res/drawable-nodpi/survival_wallpaper.png`

## Next improvements
A production-ready version could add proper edge-to-edge layouts, custom audio assets you own, lock-screen wallpaper support where permitted, and a companion icon pack / launcher integration.
