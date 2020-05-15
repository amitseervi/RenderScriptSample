package com.alinus.renderscriptsample

import android.graphics.Bitmap
import android.os.AsyncTask
import android.renderscript.Allocation
import android.renderscript.RenderScript
import android.widget.ImageView
import com.example.android.basicrenderscript.ScriptC_sample_renderscript

class ImageInverter(private val input: Bitmap, private val imgView: ImageView) :
    AsyncTask<Void, Void, Bitmap>() {
    private val renderScript = RenderScript.create(imgView.context)
    private val mInAllocation = Allocation.createFromBitmap(renderScript, input);
    private val mOutBitmap = Bitmap.createBitmap(input.width, input.height, input.config)
    private val mOutAllocation = Allocation.createFromBitmap(renderScript, mOutBitmap)
    private val mScript = ScriptC_sample_renderscript(renderScript)

    override fun doInBackground(vararg params: Void?): Bitmap {
        if (!isCancelled) {
            mScript.forEach_invert(mInAllocation, mOutAllocation)
            mOutAllocation.copyTo(mOutBitmap)
            return mOutBitmap
        }
        return input
    }

    override fun onPostExecute(result: Bitmap?) {
        super.onPostExecute(result)
        imgView.setImageBitmap(result)
    }

}