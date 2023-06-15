package it.unibo.demo.progetto

import android.Manifest
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class PermissionActivity : AppCompatActivity() {

        private val TAG = "SamplePermissionActivity"
        private val PERMISSION_REQUEST_CODE = 102

        private lateinit var btnCheckPermission : Button
        private lateinit var txtResultPermission: TextView

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_permission)

            btnCheckPermission = findViewById(R.id.btnCheckPermissionWrite)
            txtResultPermission = findViewById(R.id.txtCheckPermissionResult)

            btnCheckPermission.setOnClickListener {
                checkPermission()
            }
        }

        fun checkPermission() {
            val result = ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
            if (result == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "Permission Already Granted, Now you can use local drive.")
                txtResultPermission.text = "Permission Already Granted"
            } else {
                requestPermission()
            }
        }

        private fun requestPermission() {
            val listPermission = arrayOf(Manifest.permission.POST_NOTIFICATIONS)
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.POST_NOTIFICATIONS)) {
                Log.d(TAG,"Autorizzazione negata in precedenza")
                txtResultPermission.text = "Autorizzazione negata in precedenza"
                showMessage("Write External Storage permission allows us to do store images. Please allow this permission in App Settings.")
            } else {
                ActivityCompat.requestPermissions(this, listPermission, PERMISSION_REQUEST_CODE)
            }
        }

        override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
        ) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            if(requestCode == PERMISSION_REQUEST_CODE )  {
                val granted = grantResults[0] == PackageManager.PERMISSION_GRANTED
                Log.d(TAG,"Autorizzazione POST_NOTIFICATIONS result: $granted")
                txtResultPermission.text = "Autorizzazione POST_NOTIFICATIONS result: $granted"
            }
        }

        fun showMessage(message: String ) {
            val mAlertPopup: AlertDialog = AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, DialogInterface.OnClickListener { dialog, which ->
                    dialog.cancel()

                    //val listPermission = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    //ActivityCompat.requestPermissions(this, listPermission, PERMISSION_REQUEST_CODE)
                }).create()

            mAlertPopup.show()
        }
}