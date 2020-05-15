package com.alinus.renderscriptsample

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Build
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import android.widget.ImageView
import androidx.annotation.RequiresApi
import com.example.android.basicrenderscript.ScriptC_sample_renderscript

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
class ImageBlur(private val input: Bitmap, private val imgView: ImageView) :
    AsyncTask<Void, Void, Bitmap>() {
    private val renderScript = RenderScript.create(imgView.context)
    private val mInAllocation = Allocation.createFromBitmap(renderScript, input);
    private val mOutBitmap = Bitmap.createBitmap(input.width, input.height, input.config)
    private val mOutAllocation = Allocation.createFromBitmap(renderScript, mOutBitmap)
    private val mScript = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript))
    override fun onPreExecute() {
        super.onPreExecute()
        mScript.setInput(mInAllocation)
        mScript.setRadius(4f)
    }

    override fun doInBackground(vararg params: Void?): Bitmap {
        if (!isCancelled) {
            mScript.forEach(mOutAllocation)
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