package com.example.myapplication

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

import android.util.Log
import android.view.LayoutInflater
import android.view.View

import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import android.widget.Button
import android.widget.TextView

import com.google.android.material.bottomsheet.BottomSheetBehavior as BottomSheetBehavior1
import kotlinx.android.synthetic.main.bottom_sheet.*
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T




class MainActivity : AppCompatActivity(), View.OnClickListener {


    // BottomSheetBehavior variable
    private lateinit var bottomSheetBehavior: BottomSheetBehavior1<*>
    private lateinit var bottomSheet : View

    // TextView variable
    private var bottomSheetHeading: TextView? = null

    // Button variables
    private var expandBottomSheetButton: Button? = null
    private var collapseBottomSheetButton: Button? = null
    private var hideBottomSheetButton: Button? = null
    private var showBottomSheetDialogButton: Button? = null




    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView = findViewById<BottomNavigationView>(R.id.nav_view)
//        navView.setLabelVisibilityMode( LABEL_VISIBILITY_SELECTED)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications,
                R.id.navigation_search
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView?.setupWithNavController(navController) //Nav Controller handle navigation and handle back stack management

        initViews()
        initListeners()


    }

    override fun onResume() {
        super.onResume()
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

//        val badge : BadgeDrawable = navView.getOrCreateBadge(R.id.navigation_notifications)
//
//        badge.number = 4
//        badge.badgeGravity = TOP_END

//        bottomNavigation.setBehaviorTranslationEnabled(true);

        val bottomNavigationMenuView = navView.getChildAt(0) as BottomNavigationMenuView
        val v = bottomNavigationMenuView.getChildAt(2)
        val itemView = v as BottomNavigationItemView

        val badge = LayoutInflater.from(this)
            .inflate(R.layout.notification_badge, itemView, true)
    }


    /**
     * method to initialize the views
     */
    private fun initViews() {

        bottomSheet = findViewById(R.id.bottom_sheet)

        bottomSheetBehavior = BottomSheetBehavior1.from(bottomSheet)


        expandBottomSheetButton = findViewById(R.id.expand_bottom_sheet_button)
        collapseBottomSheetButton = findViewById(R.id.collapse_bottom_sheet_button)
        hideBottomSheetButton = findViewById(R.id.hide_bottom_sheet_button)
        showBottomSheetDialogButton = findViewById(R.id.show_bottom_sheet_dialog_button)


    }


    /**
     * method to initialize the listeners
     */
    private fun initListeners()
    {
        // register the listener for button click
        expandBottomSheetButton?.setOnClickListener(this)
        collapseBottomSheetButton?.setOnClickListener(this)
        hideBottomSheetButton?.setOnClickListener(this)
        showBottomSheetDialogButton?.setOnClickListener(this)

        // Capturing the callbacks for bottom sheet
        bottomSheetBehavior.addBottomSheetCallback(object : BottomSheetBehavior1.BottomSheetCallback()
                                                            {
                                                                override fun onStateChanged(bottomSheet: View, newState: Int)
                                                                {

                                                                    if (newState == BottomSheetBehavior1.STATE_EXPANDED)
                                                                    {
                                                                        bottomSheetHeading?.text =
                                                                            getString(R.string.text_collapse_me)
                                                                    } else {
                                                                        bottomSheetHeading?.text =getString(R.string.text_expand_me)
                                                                    }

                                                                    // Check Logs to see how bottom sheets behaves
                                                                    when (newState)
                                                                    {
                                                                        BottomSheetBehavior1.STATE_COLLAPSED -> Log.e(
                                                                            "Bottom Sheet Behaviour",
                                                                            "STATE_COLLAPSED"
                                                                        )
                                                                        BottomSheetBehavior1.STATE_DRAGGING -> Log.e(
                                                                            "Bottom Sheet Behaviour",
                                                                            "STATE_DRAGGING"
                                                                        )
                                                                        BottomSheetBehavior1.STATE_EXPANDED -> Log.e(
                                                                            "Bottom Sheet Behaviour",
                                                                            "STATE_EXPANDED"
                                                                        )
                                                                        BottomSheetBehavior1.STATE_HIDDEN -> Log.e(
                                                                            "Bottom Sheet Behaviour",
                                                                            "STATE_HIDDEN"
                                                                        )
                                                                        BottomSheetBehavior1.STATE_SETTLING -> Log.e(
                                                                            "Bottom Sheet Behaviour",
                                                                            "STATE_SETTLING"
                                                                        )
                                                                        BottomSheetBehavior1.STATE_HALF_EXPANDED -> Log.e(
                                                                            "Bottom Sheet Behaviour",
                                                                            "STATE_HALF_EXPANDED"
                                                                        )
                                                                    }
                                                                }


                                                                override fun onSlide(bottomSheet: View, slideOffset: Float) {

                                                                }
                                                            })


    }

    /**
     * onClick Listener to capture button click
     *
     * @param v
     */
    override fun onClick(v: View) {
        when (v.id) {
            R.id.collapse_bottom_sheet_button ->
                // Collapsing the bottom sheet
                bottomSheetBehavior.setState(BottomSheetBehavior1.STATE_COLLAPSED)
            R.id.expand_bottom_sheet_button ->
                // Expanding the bottom sheet
                bottomSheetBehavior.setState(BottomSheetBehavior1.STATE_EXPANDED)
            R.id.hide_bottom_sheet_button ->
                // Hiding the bottom sheet
                bottomSheetBehavior.setState(BottomSheetBehavior1.STATE_HIDDEN)
            R.id.show_bottom_sheet_dialog_button -> {
            }
        }
    }
}
