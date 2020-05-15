package com.alinus.renderscriptsample

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Build
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicConvolve3x3
import android.widget.ImageView
import androidx.annotation.RequiresApi
import com.example.android.basicrenderscript.ScriptC_sample_renderscript

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
class ImageConvolution(private val input: Bitmap, private val imgView: ImageView) :
    AsyncTask<Void, Void, Bitmap>() {
    private val renderScript = RenderScript.create(imgView.context)
    private val mInAllocation = Allocation.createFromBitmap(renderScript, input);
    private val mOutBitmap = Bitmap.createBitmap(input.width, input.height, input.config)
    private val mOutAllocation = Allocation.createFromBitmap(renderScript, mOutBitmap)
    private val scriptIntrinsicConvolve3x3: ScriptIntrinsicConvolve3x3 =
        ScriptIntrinsicConvolve3x3.create(
            renderScript,
            Element.U8_4(renderScript)
        )

    private val matrix = arrayOf(
        0f, -1f, -2f,
        -3f, 4f, 3f,
        2f, 1f, 0f
    ).toFloatArray()

    override fun onPreExecute() {
        super.onPreExecute()
        scriptIntrinsicConvolve3x3.setInput(mInAllocation)
        scriptIntrinsicConvolve3x3.setCoefficients(matrix)
    }

    override fun doInBackground(vararg params: Void?): Bitmap {
        if (!isCancelled) {
            scriptIntrinsicConvolve3x3.forEach(mOutAllocation)
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