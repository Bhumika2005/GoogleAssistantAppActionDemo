package com.example.fitnessdemo.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.net.toUri
import com.example.fitnessdemo.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val shortcut = ShortcutInfoCompat.Builder(this, "complete_tasks")
            .setShortLabel("complete task")
            .setIntent(
                Intent(Intent.ACTION_VIEW).apply {
                    setPackage("com.example.fitnessdemo")
                    setClassName("com.example.fitnessdemo", "com.example.fitnessdemo.ui.MainActivity")
                    data = "fitnessdemo://task".toUri()
                    addCategory(Intent.CATEGORY_DEFAULT)
                    addCategory(Intent.CATEGORY_BROWSABLE)
                }
            )
            .addCapabilityBinding("actions.intent.OPEN_APP_FEATURE", "feature", this.resources.getStringArray(R.array.completed_tasks_synonyms).toList())
            .build()

        ShortcutManagerCompat.pushDynamicShortcut(this, shortcut)
    }
}
