package com.example.searchbarass

import android.app.Activity
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.searchbarass.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var listAdapter: ArrayAdapter<String>
    lateinit var programmingLanguagesList: ArrayList<String>;
     private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
            hideKeyboard(this)
            programmingLanguagesList = ArrayList()
            programmingLanguagesList.add("C")
            programmingLanguagesList.add("C#")
            programmingLanguagesList.add("Java")
            programmingLanguagesList.add("Javascript")
            programmingLanguagesList.add("Python")
            programmingLanguagesList.add("Dart")
            programmingLanguagesList.add("Kotlin")
            programmingLanguagesList.add("Typescript")

            // initializing list adapter and setting layout
            // for each list view item and adding array list to it.
            listAdapter = ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                programmingLanguagesList
            )
            binding.idLVProgrammingLanguages.adapter = listAdapter

            binding.idSV.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (programmingLanguagesList.contains(query)) {
                        listAdapter.filter.filter(query)
                    } else {
                        Toast.makeText(this@MainActivity, "No Language found..", Toast.LENGTH_LONG)
                            .show()
                    }
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    listAdapter.filter.filter(newText)
                    return false
                }
            })


    }

    fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}