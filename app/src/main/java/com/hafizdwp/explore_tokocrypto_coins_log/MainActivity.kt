package com.hafizdwp.explore_tokocrypto_coins_log

import android.os.Bundle
import com.hafizdwp.explore_tokocrypto_coins_log.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = obtainViewModel<MainViewModel>()

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

        btn_get.setOnClickListener {
            viewModel.getAllCoins()
        }
        observe(viewModel)
    }

    fun observe(viewModel: MainViewModel) {
        viewModel.apply {
            coins.observe {
                text.text = it.toString()
            }
        }
    }
}