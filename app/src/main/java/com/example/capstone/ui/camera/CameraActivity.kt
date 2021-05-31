package com.example.capstone.ui.camera

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.capstone.R
import com.example.capstone.TFLiteHelper
import com.example.capstone.databinding.ActivityCameraBinding
import com.example.capstone.ui.detail.DetailActivity
import com.example.capstone.ui.progress.ProgressFragment
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class CameraActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCameraBinding

    private var imageCapture: ImageCapture? = null
    private var tfLiteHelper: TFLiteHelper? = null
    private lateinit var outputDirectory: File
    private lateinit var cameraExecutor: ExecutorService

    companion object {
        private const val TAG = "CameraX"
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        private const val REQUEST_CODE_PERMISSIONS = 200
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCameraBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val progressFragment = ProgressFragment()

        //ask camera permissions
        if (allPermissionsGranted()) {
            startCamera()
        } else {
            ActivityCompat.requestPermissions(
                this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS
            )
        }

        //set up listener to take photo
        binding.btnTakePhoto.setOnClickListener {
            takePhoto() //Taking photo and saving picture funcion. Temorary disabled. Remember to uncomment it later
            Toast.makeText(this@CameraActivity,"Photo is taken",Toast.LENGTH_SHORT).show()
            tfLiteHelper?.classifyImage(takePhoto())
            Log.d("Main Activity", tfLiteHelper!!.showresult().toString())
            cocokLabel()
            supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.anim_slide_in_up,R.anim.anim_slide_out_down)
                .replace(R.id.fragment_container,progressFragment)
                .commit()
        }

        outputDirectory = getOutputDirectory()
        cameraExecutor = Executors.newSingleThreadExecutor()
    }

    private fun getOutputDirectory(): File {
        val mediaDirectory = externalMediaDirs.firstOrNull()?.let {
            File(it, resources.getString(R.string.app_name)).apply {
                mkdirs()
            }
        }
        return if (mediaDirectory != null && mediaDirectory.exists())
            mediaDirectory else filesDir
    }

    @RequiresApi(Build.VERSION_CODES.P)
    private fun takePhoto():Bitmap {
        // get stable reference of modifiable image capture use case
        val imageCapture = imageCapture

        //create time-stamped output file to hold image
        val photoFile = File(
            outputDirectory,
            SimpleDateFormat(FILENAME_FORMAT, Locale.US)
                .format(System.currentTimeMillis())+".jpg")

        // Create output options object which contains file + metadata
        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

        // Set up image capture listener, which is triggered after photo has
        // been taken

        imageCapture?.takePicture(
            outputOptions, ContextCompat.getMainExecutor(this),object:ImageCapture.OnImageSavedCallback{
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val uri = Uri.fromFile(photoFile)
                    val msg = "Photo capture succeeded: $uri"
                    Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
                    Log.d(TAG, msg)

                }

                override fun onError(exception: ImageCaptureException) {
                    Log.e(TAG, "Photo capture failed: ${exception.message}", exception)
                }
            }
        )
//        val uri = Uri.fromFile(photoFile)
//        val file = File(photoFile.toString())
        val bmOptions = BitmapFactory.Options()
        val filePath = photoFile.path
        val photo = BitmapFactory.decodeFile(filePath,bmOptions)
//        photo = MediaStore.Images.Media.getBitmap(this.contentResolver, auxFile)
//        val source = ImageDecoder.createSource(contentResolver, file)
//        photo =ImageDecoder.decodeBitmap(source)
        return photo
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener(Runnable {
            // Used to bind the lifecycle of cameras to the lifecycle owner
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            // Preview
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.viewFinder.surfaceProvider)
                }

            imageCapture = ImageCapture.Builder()
                .build()

            // Select back camera as a default
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()

                // Bind use cases to camera
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview,imageCapture
                )

            } catch (exc: Exception) {
                Log.e(TAG, "Use case binding failed", exc)
            }
        }, ContextCompat.getMainExecutor(this))
    }

    private fun allPermissionsGranted() = Companion.REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            baseContext, it
        ) == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                startCamera()
            } else {
                Toast.makeText(this, "Permissions is not granted", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun cocokLabel() {
        var idResult = 0
        var listResult:List<String>? = tfLiteHelper?.showresult()
        if (listResult != null) {
            when{
                listResult.contains("Amaranthus Viridis (Arive-Dantu)") -> idResult=1
                listResult.contains("Basella Alba (Basale)") -> idResult=2
                listResult.contains("Ficus Auriculata (Roxburgh)") -> idResult=3
                listResult.contains("Moringa Oleifera (Drumstick)") -> idResult=4
                listResult.contains("Muntingia Calabura (Jamaica Cherry-Gasagase)") -> idResult=5
                listResult.contains("Murraya Koenigii (Curry)") -> idResult=6
                listResult.contains("Nyctanthes Arbor-tristis (Parijata)") -> idResult=7
                listResult.contains("Ocimum Tenuiflorum (Tulsi)") -> idResult=8
                listResult.contains("Piper Betle (Betel)") -> idResult=9
                listResult.contains("Psidium Guajava (Guava)") -> idResult=10
            }
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("EXTRA_ID",idResult.toString())
            startActivity(intent)
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }
}