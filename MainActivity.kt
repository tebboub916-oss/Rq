package com.example.survivaltheme

import android.app.Activity
import android.app.WallpaperManager
import android.media.MediaPlayer
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable

class MainActivity : Activity() {
    private var player: MediaPlayer? = null
    private var soundOn = false
    private var rainOn = true
    private lateinit var status: TextView

    private fun dp(v: Int) = (v * resources.displayMetrics.density).toInt()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = Color.rgb(8, 11, 16)
        window.navigationBarColor = Color.rgb(8, 11, 16)

        val scroll = ScrollView(this)
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dp(20), dp(26), dp(20), dp(24))
            background = GradientDrawable(
                GradientDrawable.Orientation.TL_BR,
                intArrayOf(Color.rgb(8, 11, 16), Color.rgb(20, 28, 38), Color.rgb(7, 9, 13))
            )
        }
        scroll.addView(root)
        setContentView(scroll)

        val eyebrow = label("UMBRELLA // PERSONAL DEVICE INTERFACE", 11, Color.rgb(211, 48, 55))
        root.addView(eyebrow)
        val title = label("SURVIVAL\nTHEME", 34, Color.WHITE)
        title.typeface = Typeface.create("sans-serif-condensed", Typeface.BOLD)
        title.setPadding(0, dp(8), 0, dp(4))
        root.addView(title)
        root.addView(label("RESIDENT EVIL–INSPIRED ANDROID THEME", 12, Color.LTGRAY))

        status = label("SYSTEM READY  •  THEME MODULE ONLINE", 12, Color.rgb(210, 210, 210))
        status.setPadding(0, dp(22), 0, dp(10))
        root.addView(status)

        val apply = actionButton("APPLY WALLPAPER  //  HOME SCREEN")
        root.addView(apply)
        apply.setOnClickListener { applyWallpaper() }

        val both = actionButton("APPLY WALLPAPER  //  HOME + LOCK SCREEN")
        root.addView(both)
        both.setOnClickListener { applyWallpaper() }

        val rain = actionButton("RAIN / SCANLINE EFFECTS: ON")
        root.addView(rain)
        rain.setOnClickListener {
            rainOn = !rainOn
            rain.text = "RAIN / SCANLINE EFFECTS: " + if (rainOn) "ON" else "OFF"
            status.text = if (rainOn) "VISUAL EFFECTS ENABLED" else "VISUAL EFFECTS PAUSED"
            val anim = if (rainOn) AlphaAnimation(0.72f, 1f) else AlphaAnimation(1f, 1f)
            anim.duration = 1300
            anim.repeatCount = if (rainOn) Animation.INFINITE else 0
            root.startAnimation(anim)
        }

        val sound = actionButton("SOUND EFFECTS: OFF")
        root.addView(sound)
        sound.setOnClickListener {
            soundOn = !soundOn
            sound.text = "SOUND EFFECTS: " + if (soundOn) "ON" else "OFF"
            // No copyrighted game audio is bundled. Use a short system click as a local effect.
            if (soundOn) {
                try {
                    val tone = android.media.ToneGenerator(android.media.AudioManager.STREAM_MUSIC, 45)
                    tone.startTone(android.media.ToneGenerator.TONE_PROP_BEEP, 120)
                    tone.release()
                    status.text = "AUDIO MODULE ENABLED"
                } catch (_: Exception) {
                    status.text = "AUDIO UNAVAILABLE ON THIS DEVICE"
                }
            } else {
                status.text = "AUDIO MODULE MUTED"
            }
        }

        root.addView(label("ICON CUSTOMIZATION", 18, Color.WHITE).apply {
            setPadding(0, dp(22), 0, dp(6))
            typeface = Typeface.DEFAULT_BOLD
        })
        root.addView(label(
            "Android does not let a normal app replace every app icon automatically. " +
            "For the closest look, apply this wallpaper, then use Samsung Good Lock / Theme Park " +
            "or a compatible icon pack to customize icons and widgets.",
            14, Color.LTGRAY
        ))
        root.addView(label("FIELD NOTES", 18, Color.WHITE).apply {
            setPadding(0, dp(20), 0, dp(6))
            typeface = Typeface.DEFAULT_BOLD
        })
        root.addView(label(
            "• Wallpaper can be applied from this app.\n" +
            "• Effects animate this app screen only; Android restricts background effects.\n" +
            "• Sound uses a simple built-in tone; no game soundtrack is included.\n" +
            "• This is a starter project you can build in Android Studio.",
            14, Color.LTGRAY
        ))
    }

    private fun label(text: String, size: Int, color: Int) = TextView(this).apply {
        this.text = text
        textSize = size.toFloat()
        setTextColor(color)
        gravity = Gravity.START
    }

    private fun actionButton(textValue: String) = Button(this).apply {
        text = textValue
        textSize = 12f
        setTextColor(Color.WHITE)
        isAllCaps = false
        background = GradientDrawable().apply {
            setColor(Color.rgb(27, 34, 43))
            setStroke(dp(1), Color.rgb(185, 42, 49))
            cornerRadius = dp(14).toFloat()
        }
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, dp(58)
        )
        params.setMargins(0, dp(8), 0, dp(5))
        layoutParams = params
    }

    private fun applyWallpaper() {
        try {
            val manager = WallpaperManager.getInstance(this)
            val stream = resources.openRawResource(R.drawable.survival_wallpaper)
            manager.setStream(stream, null, true, WallpaperManager.FLAG_SYSTEM)
            stream.close()
            status.text = "WALLPAPER APPLIED TO HOME SCREEN"
            Toast.makeText(this, "Wallpaper applied to Home screen", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            status.text = "COULD NOT APPLY WALLPAPER — TRY AGAIN"
            Toast.makeText(this, "Could not apply wallpaper: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroy() {
        player?.release()
        player = null
        super.onDestroy()
    }
}
