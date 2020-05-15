package com.alinus.renderscriptsample

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.renderscript.Allocation
import android.renderscript.RenderScript
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadButton.setOnClickListener {
            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.coffee)
            val asyncTask = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                ImageConvolution(bitmap, imageView)
            } else {
                TODO("VERSION.SDK_INT < JELLY_BEAN_MR1")
            }
            asyncTask.executeOnExecutor(Executors.newSingleThreadExecutor())
        }
    }
}
