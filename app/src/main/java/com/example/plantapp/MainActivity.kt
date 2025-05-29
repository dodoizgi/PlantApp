package com.example.plantapp

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.plantapp.databinding.ActivityMainBinding
import com.example.plantapp.presentation.diagnose.DiagnoseFragment
import com.example.plantapp.presentation.home.HomeFragment
import com.example.plantapp.presentation.mygarden.MyGardenFragment
import com.example.plantapp.presentation.profile.ProfileFragment
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), FragmentNavigationListener {
    private lateinit var binding: ActivityMainBinding

    private var latestTmpUri: Uri? = null

    private val takePicture =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { success: Boolean ->
            if (success) {
                Toast.makeText(this, "Fotoğraf çekildi! Uri: $latestTmpUri", Toast.LENGTH_LONG)
                    .show()
            } else {
                Toast.makeText(this, "Fotoğraf çekilemedi.", Toast.LENGTH_SHORT).show()
            }
        }

    private val requestPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                startCamera()
            } else {
                Toast.makeText(this, "Kamera izni reddedildi.", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView(savedInstanceState)
    }

    private fun initView(savedInstanceState: Bundle?) = with(binding) {
        ViewCompat.setOnApplyWindowInsetsListener(root) { view, insets ->
            val navBarHeight = insets.getInsets(WindowInsetsCompat.Type.navigationBars()).bottom
            view.setPadding(0, 0, 0, navBarHeight)
            insets
        }

        val sharedPreferences = getSharedPreferences("app_preferences", MODE_PRIVATE)
        val isOnboardingCompleted = sharedPreferences.getBoolean("is_onboarding_completed", false)

        if (isOnboardingCompleted) {
            loadFragment(HomeFragment())

            if (!bottomLayout.isVisible) {
                bottomLayout.visibility = View.VISIBLE
                fabScan.visibility = View.VISIBLE
            }

            if (savedInstanceState == null) {
                bottomNavigation.selectedItemId = R.id.nav_home
            }
        }

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> loadFragment(HomeFragment())
                R.id.nav_diagnose -> loadFragment(DiagnoseFragment())
                R.id.nav_garden -> loadFragment(MyGardenFragment())
                R.id.nav_profile -> loadFragment(ProfileFragment())
            }
            true
        }

        fabScan.apply {
            setOnClickListener { checkCameraPermissionAndStartCamera() }
        }
    }

    override fun loadFragment(fragment: Fragment) {
        if (!binding.bottomLayout.isVisible) {
            binding.bottomLayout.visibility = View.VISIBLE
            binding.fabScan.visibility = View.VISIBLE
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .commit()
    }

    private fun checkCameraPermissionAndStartCamera() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                startCamera()
            }

            shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> {
                Toast.makeText(
                    this,
                    "Kamera fonksiyonu için kamera izni gerekiyor.",
                    Toast.LENGTH_LONG
                ).show()
                requestPermission.launch(Manifest.permission.CAMERA)
            }

            else -> {
                requestPermission.launch(Manifest.permission.CAMERA)
            }
        }
    }

    private fun startCamera() {
        getTmpFileUri().let { uri ->
            latestTmpUri = uri
            takePicture.launch(uri)
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        val timeStamp: String =
            SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        val storageDir: File? = this.cacheDir
        val imageFile = File.createTempFile(
            imageFileName, /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        )
        return imageFile
    }

    private fun getTmpFileUri(): Uri {
        val tmpFile = createImageFile()
        tmpFile.createNewFile()
        tmpFile.deleteOnExit()

        val authority = "${this.packageName}.fileprovider"
        return FileProvider.getUriForFile(this, authority, tmpFile)
    }
}

interface FragmentNavigationListener {
    fun loadFragment(fragment: Fragment)
}