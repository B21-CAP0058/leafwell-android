package com.example.capstone.ui.home

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.capstone.URIPathHelper
import com.example.capstone.databinding.FragmentHomeBinding
import com.example.capstone.ui.searchmanual.SearchManualActivity

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    companion object{
        const val REQUEST_CODE = 200
        const val TAG="tag"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.cvMenu1.setOnClickListener {
            val intent = Intent(context, SearchManualActivity::class.java)
            startActivity(intent)
        }
        fun isPermissionsAllowed(): Boolean {
            return context?.let {
                checkSelfPermission(
                    it,
                    Manifest.permission.CAMERA
                )
            } == PackageManager.PERMISSION_GRANTED
        }

        fun askForPermission(): Boolean {
            if (!isPermissionsAllowed()) {
                requestPermissions(arrayOf(Manifest.permission.CAMERA), REQUEST_CODE)
            } else {
                return false
            }
            return true
        }

        binding.cvMenu2.setOnClickListener {
            if (!askForPermission()) {
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, REQUEST_CODE)
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE && data != null) {
//            binding.ivImage.setImageBitmap(data.extras.get("data") as Bitmap)
            Toast.makeText(context,"Picture has been taken",Toast.LENGTH_SHORT).show()

//            val uriPathHelper = URIPathHelper()
//            val filePath = uriPathHelper.getPath(requireContext(),data.data!!)
//            if (filePath != null) {
//                Log.d(TAG,filePath)
//            }

        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission is granted
                    Toast.makeText(context,"Camera is Allowed",Toast.LENGTH_SHORT).show()
                    val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(cameraIntent,REQUEST_CODE)
                } else {
                    // permission is denied
                    Toast.makeText(context,"Camera isn't Allowed. Check Your Setting Permission",Toast.LENGTH_LONG).show()
                }
                return
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }



}