package com.hafizdwp.explore_tokocrypto_coins_log

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.hafizdwp.explore_tokocrypto_coins_log.base.BaseActivity
import com.hafizdwp.explore_tokocrypto_coins_log.util.defaultDivider
import com.hafizdwp.explore_tokocrypto_coins_log.util.obtainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override val layoutRes: Int
        get() = R.layout.activity_main

    lateinit var mainAdapter: MainAdapter
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = obtainViewModel()
        observe(viewModel)

        mainAdapter = MainAdapter()
        recycler_view.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(defaultDivider())
        }

        swipe_refresh.setOnRefreshListener {
            viewModel.getAllCoins(true)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllCoins()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.night_mode -> {
                viewModel.switchNightMode()
                recreate()
            }
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        val nightModeMenu = menu?.findItem(R.id.night_mode)
        val isNightMode = viewModel.getNightMode()
        nightModeMenu?.setIcon(if (isNightMode) R.drawable.ic_day_mode else R.drawable.ic_night_mode)

        return true
    }

    fun observe(viewModel: MainViewModel) {
        viewModel.apply {
            coins.observe {
                mainAdapter.updateCoins(it ?: arrayListOf())
            }

            swipe.observe {
                when (it) {
                    true -> swipe_refresh.isRefreshing = true
                    false -> swipe_refresh.isRefreshing = false
                }
            }
        }
    }

    fun export2excel() {
        //        viewModel.start()
//        viewModel.print()

//        val export = SQLiteToExcel(this, "database")
//        export.exportAllTables("tesexport.xls", object : SQLiteToExcel.ExportListener {
//            override fun onStart() {
//                log("exporting...")
//            }
//
//            override fun onCompleted(filePath: String?) {
//                log("export complete!")
//            }
//
//            override fun onError(e: Exception?) {
//                logError("export error: $e")
//            }
//        })

        //        btn_get.setOnClickListener {
//            viewModel.getAllCoins()
//        }
    }
}