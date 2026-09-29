package com.sgu.kampusgo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sgu.kampusgo.ui.theme.KampusGoTheme
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.unit.dp
import android.content.ActivityNotFoundException
import androidx.compose.material3.Button
import android.content.Intent
import android.net.Uri
import android.widget.Toast

class ProfileActivity : ComponentActivity() {

    //3.3
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // First callback. The screen is being created. Write a line to Logcat.
        Log.d("KampusGo", "onCreate")
        // Keep the Activity for the button. Inside setContent, "this" is not the Activity.
        val activity = this
        // Read the extra named "name". If screen 1 sent nothing, use Guest. No !!.
        val name = intent.getStringExtra("name") ?: "Guest"
        val npm = intent.getStringExtra("npm") ?: "-"
        setContent {
            KampusGoTheme {
                Column(modifier = Modifier.padding(24.dp)) {
                    // Show the name that traveled inside the Intent.
                    Text(text = "Hello, $name")
                    Text(text = "Your NPM-NR:, $npm")
                    Button(onClick = {
                        // Implicit Intent: do not name an Activity. Ask the phone for a dialer.
                        val dial = Intent(Intent.ACTION_DIAL, Uri.parse("tel:0211234567"))
                        try {
                            activity.startActivity(dial)
                        } catch (e: ActivityNotFoundException) {
                            Toast.makeText(activity, "No dialer on this device", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }) {
                        Text("Call campus")
                    }
                    //open SGU Page
                    Button(onClick = {
                        val sgupage = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.sgu.ac.id"))
                        try {
                            activity.startActivity(sgupage)
                        } catch (e: ActivityNotFoundException) {
                            Toast.makeText(activity, "Page coulndt be reached", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }) {
                        Text("WebPage")
                    }
                }
            }
        }
    }

    //3.4
    // Visible, but not yet the screen in front.
    override fun onStart() {
        super.onStart()
        Log.d("KampusGo", "onStart")
    }
    // In front. The user can tap the button.
    override fun onResume() {
        super.onResume()
        Log.d("KampusGo", "onResume")
    }
    // Leaving, or the phone is rotating.
    override fun onPause() {
        super.onPause()
        Log.d("KampusGo", "onPause")
    }
    // No longer visible.
    override fun onStop() {
        super.onStop()
        Log.d("KampusGo", "onStop")
    }

}