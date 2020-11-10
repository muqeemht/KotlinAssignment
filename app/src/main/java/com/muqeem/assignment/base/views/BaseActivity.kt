package com.example.kotlinrnd.base.views

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import com.example.kotlinrnd.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView

open class BaseActivity : AppCompatActivity(){
//    var navigationView: NavigationView? = null
//    var mDrawer: DrawerLayout? = null
    lateinit var fab: FloatingActionButton
    lateinit var navController: NavController
    lateinit var bottomNav: BottomNavigationView
//    var toolbar: Toolbar? = null;
//    lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, R.color.teal_700)
    }

//    fun setPageTitle(mTitle: String?) {
//        supportActionBar!!.title = mTitle
//    }

    fun setProfileImage() {
//        val user: User = AppPrefManager.getInstance(this).getLoggedUserInstance()
//        val header = navigationView!!.getHeaderView(0)
//        if (user != null) {
//            if (AppPrefManager.getInstance().getSelectedEntitiy()
//                    .equalsIgnoreCase(Constants.CARRIER) && user.getCompanyName() != null
//            ) {
//                (header.findViewById<View>(R.id.tvUserName) as TextView).setText(user.getCompanyName())
//            } else {
//                (header.findViewById<View>(R.id.tvUserName) as TextView).setText(
//                    user.getFirstName().toString() + " " + user.getLastName()
//                )
//            }
//            if (user.getEmailAddress() != null) {
//                (header.findViewById<View>(R.id.emailTv) as TextView).setText(user.getEmailAddress())
//            } else if (user.getPhoneNumber() != null) {
//                (header.findViewById<View>(R.id.emailTv) as TextView).setText(user.getPhoneNumber())
//            }
//            if (user != null && !TextUtils.isEmpty(user.getLogoURL())) {
//                Picasso.with(this).load(RestClient.BASE_URL_IMAGE + user.getLogoURL())
//                    .memoryPolicy(MemoryPolicy.NO_CACHE)
//                    .networkPolicy(NetworkPolicy.NO_CACHE)
//                    .into(header.findViewById<View>(R.id.ivProfile) as ImageView)
//            }
//        }
//        header.findViewById<View>(R.id.profile_view).setOnClickListener(this)

//        GlideApp.with(this)
//                .load(URL)
//                .into(image)


    }
}