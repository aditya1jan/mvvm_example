package com.nexxo.loginexample.util

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.gson.JsonObject
import com.nexxo.loginexample.R
import com.nexxo.loginexample.login.view.MainActivity
import org.json.JSONObject

// functions to show show
public class Util{
    private val MULTIPLE_PERMISSION_REQUEST_CODE = 100
    private val PERMISSION_REQUEST_CODE = 200

    //Method for showing toast message for short duration
    fun myToast(context: Context,message: String)
    {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    //Method for showing toast message for long duration
    fun myToastLong(context: Context,message: String)
    {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    fun deviceDetails(): HashMap<String,String> {
        val jsonObject = HashMap<String,String>()
        jsonObject.put("macAddress","A4:D4:B2:E2:37:AB")
        jsonObject.put("imeiNumber","fe8a3b3ee4184576")
        jsonObject.put("manufacturer","i80")
        jsonObject.put("model","unknown")
        jsonObject.put("platform","android")
        jsonObject.put("latitude","00.000")
        jsonObject.put("longitude","00.000")
        jsonObject.put("version","1 - 7.1.2")
        return jsonObject
    }


    fun checkPermissionList(activity: Activity,aClass: Class<*>?)
    {
        if(checkPermissions(activity)) {
            goToNextScreen(activity,aClass)
        }
    }
    fun checkPermissions(activity: Activity): Boolean {
        val permissionCAMERA = ContextCompat.checkSelfPermission(
            activity,
            Manifest.permission.CAMERA
        )
        val permissionWRITE_EXTERNAL_STORAGE = ContextCompat.checkSelfPermission(
            activity,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        val permissionREAD_EXTERNAL_STORAGE = ContextCompat.checkSelfPermission(
            activity,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        val locationPermission =
            ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)
        val listPermissionsNeeded: MutableList<String> = java.util.ArrayList()
        if (locationPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION)
        }
        if (permissionCAMERA != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA)
        }
        if (permissionWRITE_EXTERNAL_STORAGE != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if (permissionREAD_EXTERNAL_STORAGE != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        if (listPermissionsNeeded.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                activity,
                listPermissionsNeeded.toTypedArray(),
                MULTIPLE_PERMISSION_REQUEST_CODE
            )
            return false
        }
        return true
    }


    var alertDialogRatinal: android.app.AlertDialog? = null
    fun checkRationalePermission(activity: Activity,permissions: Array<out String>)
    {
        val alertDialogBuilder =
            android.app.AlertDialog.Builder(activity)
        alertDialogRatinal = alertDialogBuilder.setTitle("Permissions Required")
            .setMessage(
                "Please open settings, go to permissions and allow them."
            )
            .setPositiveButton(
                "Settings"
            ) { dialog, _ ->
                dialog.dismiss()

                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri: Uri = Uri.fromParts("package", activity.packageName, null)
                intent.data = uri
                activity.startActivityForResult(intent, PERMISSION_REQUEST_CODE)
            }
            .setNegativeButton(
                "Cancel"
            ) { dialog, _ ->
                dialog.dismiss()
                activity.finish()
            }
            .setCancelable(false)
            .create()
        alertDialogRatinal!!.show()
    }

    //Method for showing 2 button custom dialog
    @SuppressLint("Range")
    fun showDialog(activity: Activity,message: String,okText:String,cancelText:String,fromFlag:Int) {
        val dialog = Dialog(activity,R.style.CustomDialog)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.two_button_dialog)
        val body = dialog.findViewById(R.id.tv_permission_text) as TextView
        body.text = message
        val settingBtn = dialog.findViewById(R.id.tv_setting) as TextView
        val cancelBtn = dialog.findViewById(R.id.tv_cancel) as TextView
        settingBtn.text = okText
        cancelBtn.text = cancelText
        settingBtn.setOnClickListener {
            dialog.dismiss()
            if(fromFlag==1) {
                goToDeviceSetting(activity)
            }
        }
        cancelBtn.setOnClickListener {
            dialog.dismiss()
            activity.finish()}
        dialog.show()

    }

    //Method for going app setting from current activity
    fun goToDeviceSetting(activity: Activity)
    {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri: Uri = Uri.fromParts("package", activity.packageName, null)
        intent.data = uri
        activity.startActivityForResult(intent, PERMISSION_REQUEST_CODE)
    }

    //Method for going to new activity
    fun goToNextScreen(activity: Activity,aClass: Class<*>?)
    {
        val i = Intent(activity, aClass)
        i.flags =
            Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        activity.startActivity(i)
        activity.finish()
    }
}
