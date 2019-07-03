package com.example.easyedu.chamada.geolocalizacao

import androidx.appcompat.app.AppCompatActivity
import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import com.example.easyedu.R
import com.example.easyedu.chamada.QRCodeScan
import com.example.easyedu.chamada.finalChamada
import com.example.easyedu.database.RoomDB
import com.example.easyedu.perfil.PerfilActivity
import kotlinx.android.synthetic.main.activity_local.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


private const val PERMISSION_REQUEST = 10

class LocalActivity : AppCompatActivity() {


    lateinit var locationManager: LocationManager
    private var hasGps = false
    private var hasNetwork = false
    private var locationGps: Location? = null
    private var locationNetwork: Location? = null

    private var permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local)
        disableView()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkPermission(permissions)) {
                enableView()
            } else {
                requestPermissions(permissions, PERMISSION_REQUEST)
            }
        } else {
            enableView()
        }

    }

    private fun disableView() {
        btn_get_location.isEnabled = false
        btn_get_location.alpha = 0.5F
    }

    private fun enableView() {
        btn_get_location.isEnabled = true
        btn_get_location.alpha = 1F
        btn_get_location.setOnClickListener { getLocation()
//        val intent = Intent(this@LocalActivity, MostrarPresencas::class.java)
//            startActivity(intent)

        }
        Toast.makeText(this, "Você respondeu a chamada! Agora confirme sua localização", Toast.LENGTH_SHORT).show()
    }







    @SuppressLint("MissingPermission")
    private fun getLocation() {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        hasGps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
//        hasNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
//        if (hasGps || hasNetwork) {
        if (hasGps) {
            if (hasGps) {
                Log.d("CodeAndroidLocation", "hasGps")
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 200000, 0F, object : LocationListener {
                    override fun onLocationChanged(location: Location?) {
                        doAsync {
                            val db = RoomDB.getDatabase(applicationContext)
                            val idAluno = db.roomDAO().saberPerfilLogado()
                            val prof = db.roomDAO().todosProf()
                            if (location != null) {
                                locationGps = location
                                for (aluno in idAluno) {
                                    val local = Presenca(
                                        idAluno = aluno.id,
                                        presenca = "P"
//                                        latitude = locationGps!!.latitude,
//                                        longitude = locationGps!!.longitude
                                    )

                                    for (p in prof) {
                                        var form = Math.PI / 180
                                        var R = 6378137 // Earth’s mean radius in meter
                                        var dLat = (p.latitude.toFloat() - locationGps!!.latitude) * form
                                        var dLong = (p.longitude.toFloat() - locationGps!!.longitude) * form
                                        var a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                                                Math.cos(form * (locationGps!!.latitude)) * Math.cos(form * (p.latitude.toFloat())) *
                                                Math.sin(dLong / 2) * Math.sin(dLong / 2)
                                        var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
                                        var d = R * c;
                                        Log.d("logd", d.toString())
                                        Log.d("ped", " GPS Longitude : " + locationGps!!.longitude)
                                        Log.d("ped", " GPS LATITUDE : " + locationGps!!.latitude)
                                        if (d > 30){

                                            val intent = Intent(this@LocalActivity, finalChamada::class.java)
                                            startActivity(intent)
                                            val intent1 = Intent("android.location.GPS_ENABLED_CHANGE")
                                            intent1.putExtra("enabled", false)
                                            sendBroadcast(intent1)

                                            finish()
                                        }else{
                                            db.roomDAO().inserirPresenca(local)
                                            val intent = Intent(this@LocalActivity, PerfilActivity::class.java)
                                            startActivity(intent)
                                            val intent1 = Intent("android.location.GPS_ENABLED_CHANGE")
                                            intent1.putExtra("enabled", false)
                                            sendBroadcast(intent1)
                                            finish()
                                        }

                                    }
                                }
                            }
                        }
                    }

                    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

                    }

                    override fun onProviderEnabled(provider: String?) {

                    }

                    override fun onProviderDisabled(provider: String?) {

                    }

                })

                val localGpsLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                if (localGpsLocation != null)
                    locationGps = localGpsLocation
            }
        } else {
            startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
        }
    }

    private fun checkPermission(permissionArray: Array<String>): Boolean {
        var allSuccess = true
        for (i in permissionArray.indices) {
            if (checkCallingOrSelfPermission(permissionArray[i]) == PackageManager.PERMISSION_DENIED)
                allSuccess = false
        }
        return allSuccess
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST) {
            var allSuccess = true
            for (i in permissions.indices) {
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    allSuccess = false
                    val requestAgain = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && shouldShowRequestPermissionRationale(permissions[i])
                    if (requestAgain) {
                        Toast.makeText(this, "Permissão Negada!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Por favor vá até as permissões e aceite!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            if (allSuccess)
                enableView()

        }
    }
}




