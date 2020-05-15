package com.alinus.renderscriptsample

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadButton.setOnClickListener {
            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.coffee)
            val asyncTask = ImageInverter(bitmap, imageView)
            asyncTask.executeOnExecutor(Executors.newSingleThreadExecutor())
        }
    }
}