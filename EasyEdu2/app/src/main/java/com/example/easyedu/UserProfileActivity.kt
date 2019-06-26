package com.example.easyedu

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_user_profile.view.*
import android.view.View
import java.util.*

class UserProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainLayout = layoutInflater.inflate(R.layout.activity_user_profile, null)
        setContentView(mainLayout)

        title = "Perfil"

        setupProfile(getJaneOrJohn(), mainLayout)

        startAnimation(mainLayout)

    }

    private fun setupProfile(profileId: Int, mainLayout: View) {

        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(300))

        Glide.with(this).load(if (profileId === 1) R.drawable.face_1 else R.drawable.face_2).apply(requestOptions)
            .into(mainLayout.profilePic)

        mainLayout.profileName.text = if (profileId === 1) "Jane Doe" else "Jhon Doe"

        mainLayout.postCount.text = GetRandomPosts()
        mainLayout.fanCount.text = GetRandomFans()
        mainLayout.rateCount.text = GetRandomRating()

        mainLayout.profileDsgn.text = if (profileId === 1) "Student" else "Teacher"
        mainLayout.profileEmail.text = if (profileId === 1) "janedoe@helloworld.com" else "jhondoe@helloworld.com"
        mainLayout.profileFullName.text = if (profileId === 1) "Jane Doe" else "Jhon Doe"

    }

    private fun startAnimation(mainLayout: View) {
        mainLayout.profileFrame.setBackgroundResource(R.drawable.profileanimbg)
        val animationDrawable = mainLayout.profileFrame.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(3000)
        animationDrawable.setExitFadeDuration(3000)
        animationDrawable.start()
    }

    fun getJaneOrJohn(): Int {
        return (1..2).shuffled().first()
    }

    fun GetRandomPosts(): String {
        return (1000..5000).shuffled().first().toString()
    }

    fun GetRandomFans(): String {
        return (10..50).shuffled().first().toString() + "K"
    }

    fun GetRandomRating(): String {
        return ((1..4).shuffled().first().toString() + "." +  Random().nextFloat().toString().subSequence(2,4))
    }

}