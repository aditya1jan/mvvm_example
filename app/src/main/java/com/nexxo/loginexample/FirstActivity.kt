package com.nexxo.loginexample

import android.Manifest
import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.nexxo.loginexample.login.view.MainActivity
import com.nexxo.loginexample.util.Util
import java.util.HashMap

class FirstActivity : AppCompatActivity() {
    private val MULTIPLE_PERMISSION_REQUEST_CODE = 100
    private val PERMISSION_REQUEST_CODE = 200
    private lateinit var activity: Activity
    var util = Util()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        activity = this@FirstActivity
        util.checkPermissionList(activity,MainActivity::class.java)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            MULTIPLE_PERMISSION_REQUEST_CODE -> {
                val perms: MutableMap<String, Int> = HashMap()
                perms[Manifest.permission.CAMERA] = PackageManager.PERMISSION_GRANTED
                perms[Manifest.permission.WRITE_EXTERNAL_STORAGE] =
                    PackageManager.PERMISSION_GRANTED
                perms[Manifest.permission.READ_EXTERNAL_STORAGE] = PackageManager.PERMISSION_GRANTED
                perms[Manifest.permission.ACCESS_FINE_LOCATION] = PackageManager.PERMISSION_GRANTED
                // Fill with actual results from user
                if (grantResults.size > 0) {
                    var i = 0
                    while (i < permissions.size) {
                        perms[permissions[i]] = grantResults[i]
                        i++
                    }
                    // Check for both permissions
                    if (perms[Manifest.permission.CAMERA] == PackageManager.PERMISSION_GRANTED
                        && perms[Manifest.permission.WRITE_EXTERNAL_STORAGE] == PackageManager.PERMISSION_GRANTED
                        && perms[Manifest.permission.READ_EXTERNAL_STORAGE] == PackageManager.PERMISSION_GRANTED
                        && perms[Manifest.permission.ACCESS_FINE_LOCATION] == PackageManager.PERMISSION_GRANTED
                    ) {
                        util.goToNextScreen(activity, MainActivity::class.java)
                    } else {
                        if (ActivityCompat.shouldShowRequestPermissionRationale(
                                this,
                                Manifest.permission.CAMERA
                            ) || ActivityCompat.shouldShowRequestPermissionRationale(
                                this,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE
                            ) || ActivityCompat.shouldShowRequestPermissionRationale(
                                this,
                                Manifest.permission.READ_EXTERNAL_STORAGE
                            ) || ActivityCompat.shouldShowRequestPermissionRationale(
                                this,
                                Manifest.permission.ACCESS_FINE_LOCATION
                            )
                        ) {

                            util.showDialog(
                                activity, "Permissions Required\nPlease open settings, go to permissions and allow them.",
                                "Settings", "Cancel", 1
                            )
                        } else {

                            util.showDialog(
                                activity, "Permissions Required\nPlease open settings, go to permissions and allow them.",
                                "Settings", "Cancel", 1
                            )
                        }
                    }
                }
            }
        }
    }

/*    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            MULTIPLE_PERMISSION_REQUEST_CODE -> {
                var isGrant = true
                if (grantResults.isNotEmpty()) {
                    for (i in grantResults.indices) {
                        if (grantResults[i] == PackageManager.PERMISSION_DENIED)
                            isGrant = false
                    }
                }
                if (isGrant) {
                    //next()
                } else {
                    for (permission in permissions) {
                        if (ActivityCompat.shouldShowRequestPermissionRationale(
                                this,
                                permission!!
                            )
                        ) {
                            util.showDialog(
                                activity, "Permissions Required",
                                "Please open settings, go to permissions and allow them.",
                                "Settings", "Cancel", 1
                            )
                        } else {

                            util.showDialog(
                                activity, "Permissions Required",
                                "Please open settings, go to permissions and allow them.",
                                "Settings", "Cancel", 1
                            )
                        }
                    }
                }
            }
        }
    }*/

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Check that it is the SecondActivity with an OK result
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                util.checkPermissionList(activity,MainActivity::class.java)
            } else {
                util.checkPermissionList(activity,MainActivity::class.java)
            }
        } else {
            util.checkPermissionList(activity,MainActivity::class.java)
        }

    }
}