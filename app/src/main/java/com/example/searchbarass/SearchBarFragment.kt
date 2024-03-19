package com.example.searchbarass

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.searchbarass.databinding.FragmentSearchBarBinding

class SearchBarFragment: Fragment() {
    private lateinit var binding : FragmentSearchBarBinding
    lateinit var listAdapter: ArrayAdapter<String>
    lateinit var programmingLanguagesList: ArrayList<String>;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_bar, container, false)
        activity?.let { hideKeyboard(it) }
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
        listAdapter = context?.let {
            ArrayAdapter<String>(
                it.applicationContext,
                android.R.layout.simple_list_item_1,
                programmingLanguagesList
            )
        }!!
        binding.idLVProgrammingLanguages.adapter = listAdapter

        binding.idSV.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (programmingLanguagesList.contains(query)) {
                    listAdapter.filter.filter(query)
                } else {
                    Toast.makeText(context, "No Language found..", Toast.LENGTH_LONG)
                        .show()
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                listAdapter.filter.filter(newText)
                return false
            }
        })

        return binding.root
    }
    fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}