@file:Suppress("DEPRECATION")

package com.example.capstone

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException


class MlActivity : AppCompatActivity() {
    private var imageView: ImageView? = null
    private var imageuri: Uri? = null
    private var buclassify: Button? = null
    private var classitext: TextView? = null
    private var tfLiteHelper: TFLiteHelper? = null
    private var bitmap: Bitmap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ml)
        imageView = findViewById<View>(R.id.image) as ImageView
        buclassify = findViewById<View>(R.id.classify) as Button
        classitext = findViewById<View>(R.id.classifytext) as TextView
        tfLiteHelper = TFLiteHelper(this)
        tfLiteHelper!!.init()
        imageView!!.setOnClickListener(selectImageListener)
        buclassify!!.setOnClickListener(classifyImageListener)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 12 && resultCode == RESULT_OK && data != null) {
            imageuri = data.data
            try {
                bitmap = MediaStore.Images.Media.getBitmap(contentResolver, imageuri)
                imageView!!.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private var selectImageListener = View.OnClickListener {
        val SELECT_TYPE = "image/*"
        val SELECT_PICTURE = "Select Picture"
        val intent = Intent()
        intent.type = SELECT_TYPE
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, SELECT_PICTURE), 12)
    }
    private var classifyImageListener = View.OnClickListener {
        if (bitmap != null) {
            tfLiteHelper!!.classifyImage(bitmap!!)
            setLabel(tfLiteHelper!!.showresult())
            Log.d("Main Activity", tfLiteHelper!!.showresult().toString())
            cocokLabel()
        }
    }

    private fun cocokLabel() {
        // tfLiteHelper.showresult() == kangkung
    }

    private fun setLabel(entries: List<String?>?) {
        classitext!!.text = ""
        for (entry in entries!!) {
            classitext!!.append(entry)
        }
    }
}