package com.alinus.renderscriptsample

import android.graphics.Bitmap
import android.os.AsyncTask
import android.widget.ImageView
import androidx.renderscript.RenderScript
import androidx.renderscript.ScriptC

class ImageInverter(private val input: Bitmap, private val imgView: ImageView) :
    AsyncTask<Void, Void, Bitmap>() {
    private val renderScript = RenderScript.create(imgView.context)

    override fun onPreExecute() {

        super.onPreExecute()
    }

    override fun doInBackground(vararg params: Void?): Bitmap {
        return input
    }

    override fun onPostExecute(result: Bitmap?) {
        super.onPostExecute(result)
        imgView.setImageBitmap(result)
    }

}