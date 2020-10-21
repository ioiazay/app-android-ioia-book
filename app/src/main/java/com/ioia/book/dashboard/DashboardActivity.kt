package com.ioia.book.dashboard

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageItemInfo
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.ioia.book.R
import com.ioia.book.account.Account
import com.ioia.book.business.add_business.AddBusinessActivity
import com.ioia.book.business.BusinessRVAdapter
import com.ioia.book.profile.ProfileFragment
import com.ioia.book.transaction.TransactionFragment
import com.ioia.book.utang.HutangFragment
import com.ioia.book.utils.InfoUtils
import com.ioia.book.utils.support.FragmentAdapter
import kotlinx.android.synthetic.main.dashboard_act.*
import kotlinx.android.synthetic.main.dashboard_act.view.*
import kotlinx.android.synthetic.main.navigation_header.view.*
import kotlinx.android.synthetic.main.navigation_header.view.rv_business

class DashboardActivity : AppCompatActivity() {

    private val viewmodel by viewModels<DashboardViewModel> {
        Injector.providerDashboardViewModelFactory(this)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard_act)

        initToolbar()
        initViewModel()
        initDrawerNavigation()
    }

    override fun onResume() {
        super.onResume()
        viewmodel.getAccount()
        viewmodel.getAllBusiness()
    }

    private fun initToolbar(){
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return true
    }

    private fun initViewModel(){
        viewmodel.account.observe(this, Observer {
            initNavigationHeader(it)
        })
    }

    @SuppressLint("ResourceAsColor")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun initDrawerNavigation(){
        val toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.label_open_drawer, R.string.label_close_drawer)
        drawer.addDrawerListener(toggle)
        toggle.drawerArrowDrawable.color = ContextCompat.getColor(this, R.color.global_white)
        toggle.syncState()
    }

    private fun initNavigationHeader(account: Account){
        val view = navigation.getHeaderView(0)
        val tvAppVersion = view.tv_app_version
        val rvBusiness = view.rv_business
        val btnAddBusiness = view.btn_add_new_business

        viewmodel.business.observe(this, Observer {
            rvBusiness.adapter?.notifyDataSetChanged()
        })

        val buildVersion = InfoUtils.getAppVersionName(this)
        tvAppVersion.text = "App Version $buildVersion"
        rvBusiness.apply {
            adapter = BusinessRVAdapter(viewmodel.business, viewmodel){businessId, businessName ->
                supportActionBar?.title = businessName
                initBottomNavigation(businessId)
            }
            itemAnimator = DefaultItemAnimator()
            layoutManager = LinearLayoutManager(this@DashboardActivity)
            setHasFixedSize(true)
        }
        btnAddBusiness.setOnClickListener {
            AddBusinessActivity.startActivity(this)
        }
    }

    private fun initBottomNavigation(businessId: Long){
        val adapter = FragmentAdapter(supportFragmentManager)
        adapter.addFragment(HutangFragment(businessId), "Hutang")
        adapter.addFragment(TransactionFragment(), "Transaction")
        adapter.addFragment(ProfileFragment(), "Profile")

        viewpager.adapter = adapter

        viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                when(position){
                    0 -> {
                        bottom_navigation.menu.findItem(R.id.item_hutang).isChecked = true
                    }

                    1 -> {
                        bottom_navigation.menu.findItem(R.id.item_transaksi).isChecked = true
                    }

                    2 -> {
                        bottom_navigation.menu.findItem(R.id.item_profile).isChecked = true
                    }
                }
            }

        })

        bottom_navigation.setOnNavigationItemSelectedListener {

            when(it.itemId){
                R.id.item_hutang -> {
                    viewpager.currentItem = 0
                }

                R.id.item_transaksi -> {
                    viewpager.currentItem = 1
                }

                R.id.item_profile -> {
                    viewpager.currentItem = 2
                }
            }

            return@setOnNavigationItemSelectedListener true
        }

    }

    companion object{
        fun startActivity(context: Context){
            context.startActivity(Intent(context, DashboardActivity::class.java))
        }

    }
}
