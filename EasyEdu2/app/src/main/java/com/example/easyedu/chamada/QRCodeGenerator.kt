package com.example.easyedu.chamada

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.media.MediaScannerConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Environment
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.easyedu.R
import com.example.easyedu.chamada.geolocalizacao.Presenca
import com.example.easyedu.database.RoomDB
import com.example.easyedu.perfil.PerfilActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import org.jetbrains.anko.doAsync
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.Calendar

private const val PERMISSION_REQUEST = 10
class QRCodeGenerator : AppCompatActivity() {
    internal var bitmap: Bitmap? = null
    private var etqr: EditText? = null
    private var iv: ImageView? = null
    private var btn: Button? = null
    lateinit var locationManager: LocationManager
    private var hasGps = false
    private var hasNetwork = false
    private var locationGps: Location? = null
    private var locationNetwork: Location? = null

    private var permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrcode_generator)

        iv = findViewById(R.id.iv) as ImageView
        etqr = findViewById(R.id.etqr) as EditText
        btn = findViewById(R.id.btn) as Button

        btn!!.setOnClickListener {
            getLocation()
            if (etqr!!.text.toString().trim { it <= ' ' }.length == 0) {
                Toast.makeText(this@QRCodeGenerator, "Digite um código para a chamada!", Toast.LENGTH_SHORT).show()
            } else {
                try {

                    bitmap = TextToImageEncode(etqr!!.text.toString())
                    iv!!.setImageBitmap(bitmap)
                    val path = saveImage(bitmap)  //give read write permission
                    //getLocation()
                    Toast.makeText(this@QRCodeGenerator, "QRCode Gerado! $path", Toast.LENGTH_SHORT).show()
                } catch (e: WriterException) {
                    e.printStackTrace()
                }

            }
        }
    }

    fun saveImage(myBitmap: Bitmap?): String {
        val bytes = ByteArrayOutputStream()
        myBitmap!!.compress(Bitmap.CompressFormat.JPEG, 90, bytes)
        val wallpaperDirectory = File(
            Environment.getExternalStorageDirectory().toString() + IMAGE_DIRECTORY
        )
        // have the object build the directory structure, if needed.

        if (!wallpaperDirectory.exists()) {
            Log.d("dirrrrrr", "" + wallpaperDirectory.mkdirs())
            wallpaperDirectory.mkdirs()
        }

        try {
            val f = File(wallpaperDirectory, Calendar.getInstance()
                .timeInMillis.toString() + ".jpg")
            f.createNewFile()   //give read write permission
            val fo = FileOutputStream(f)
            fo.write(bytes.toByteArray())
            MediaScannerConnection.scanFile(this,
                arrayOf(f.path),
                arrayOf("image/jpeg"), null)
            fo.close()

            Log.d("TAG", "Arquivo salvo em::--->" + f.absolutePath)

            return f.absolutePath
        } catch (e1: IOException) {
            e1.printStackTrace()
        }

        return ""

    }

    @Throws(WriterException::class)
    private fun TextToImageEncode(Value: String): Bitmap? {
        val bitMatrix: BitMatrix
        try {
            bitMatrix = MultiFormatWriter().encode(
                Value,
                BarcodeFormat.QR_CODE,
                QRcodeWidth,
                QRcodeWidth, null
            )

        } catch (Illegalargumentexception: IllegalArgumentException) {

            return null
        }

        val bitMatrixWidth = bitMatrix.getWidth()

        val bitMatrixHeight = bitMatrix.getHeight()

        val pixels = IntArray(bitMatrixWidth * bitMatrixHeight)

        for (y in 0 until bitMatrixHeight) {
            val offset = y * bitMatrixWidth

            for (x in 0 until bitMatrixWidth) {

                pixels[offset + x] = if (bitMatrix.get(x, y))
                    resources.getColor(R.color.black)
                else
                    resources.getColor(R.color.white)
            }
        }
        val bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444)

        bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight)
        return bitmap
    }

    companion object {

        val QRcodeWidth = 500
        private val IMAGE_DIRECTORY = "/QRcodeDemonuts"
    }


    @SuppressLint("MissingPermission")
    private fun getLocation() {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        hasGps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
//        hasNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
//        if (hasGps || hasNetwork) {
        if (hasGps) {
            if (hasGps) {
                Log.d("CodeAndroadaidLocation", "hasGps")
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 200000, 0F, object :
                    LocationListener {
                    override fun onLocationChanged(location: Location?) {

                        doAsync {
                            val db = RoomDB.getDatabase(applicationContext)
                            val idProf = db.roomDAO().saberPerfilLogado()
                            if (location != null) {
                                locationGps = location
                                for (professor in idProf) {
                                    val local = Professor(
                                        userID = professor.id,
                                        latitude = locationGps!!.latitude.toString(),
                                        longitude = locationGps!!.longitude.toString()
                                    )
 //                                   Log.d("CodeAndroidLocation", " GPS Latitude : " + locationGps!!.latitude)
//                            Log.d("CodeAndroidLocation", " GPS Longitude : " + locationGps!!.longitude)
                                    val isso = db.roomDAO().todosProf()

                                    if(isso.size >= 1){
                                        db.roomDAO().atualizarProf(local)
                                        Log.d("pedin", isso.size.toString())
                                    }else {
                                        db.roomDAO().inserirProfessor(local)
                                    }
                                }

                                doAsync {
                                    val a = RoomDB.getDatabase(applicationContext)
                                    val isso = a.roomDAO().todosProf()

                                    for (x in isso){
                                        Log.d("pedin", " GPS Longitude : " + x.longitude)
                                        Log.d("pedin", " GPS LATITUDE : " + x.latitude)
                                    }
                                }
//                            tv_result.append("\nGPS ")
//                            tv_result.append("\nLatitude : " + locationGps!!.latitude)
//                            tv_result.append("\nLongitude : " + locationGps!!.longitude)
//                            Log.d("CodeAndroidLocation", " GPS Latitude : " + locationGps!!.latitude)
//                            Log.d("CodeAndroidLocation", " GPS Longitude : " + locationGps!!.longitude)
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

               //    locationManager.removeUpdates(object : LocationListener{})

                val localGpsLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                if (localGpsLocation != null)
                    locationGps = localGpsLocation
            }
        } else {
            startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
        }
    }

}