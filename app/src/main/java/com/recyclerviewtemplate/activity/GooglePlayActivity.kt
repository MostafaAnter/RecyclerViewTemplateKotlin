package com.recyclerviewtemplate.activity

import android.app.SearchManager
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.view.Menu
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuItemCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.recyclerviewtemplate.AbstractGooglePlay
import com.recyclerviewtemplate.R
import com.recyclerviewtemplate.adapter.GooglePlayAdapter
import com.recyclerviewtemplate.adapter.GooglePlayAdapter.OnMoreClickListener
import java.util.*

class GooglePlayActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var toolbar: Toolbar? = null
    private var swipeRefreshRecyclerList: SwipeRefreshLayout? = null
    private var fab: FloatingActionButton? = null
    private var mAdapter: GooglePlayAdapter? = null
    private val modelList = ArrayList<AbstractGooglePlay>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_play)

        // ButterKnife.bind(this);
        findViews()
        initToolbar("Takeoff Android")
        setAdapter()
        swipeRefreshRecyclerList!!.setOnRefreshListener { // Do your stuff on refresh
            Handler().postDelayed({ if (swipeRefreshRecyclerList!!.isRefreshing) swipeRefreshRecyclerList!!.isRefreshing = false }, 5000)
        }
    }

    private fun findViews() {
        toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        recyclerView = findViewById<View>(R.id.recycler_view) as RecyclerView
        swipeRefreshRecyclerList = findViewById<View>(R.id.swipe_refresh_recycler_list) as SwipeRefreshLayout
        fab = findViewById<View>(R.id.fab) as FloatingActionButton
    }

    fun initToolbar(title: String?) {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.title = title
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_search, menu)


        // Retrieve the SearchView and plug it into SearchManager
        val searchView = MenuItemCompat
                .getActionView(menu.findItem(R.id.action_search)) as SearchView
        val searchManager = this.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(this.componentName))

        //changing edittext color
        val searchEdit = searchView.findViewById<View>(R.id.search_src_text) as EditText
        searchEdit.setTextColor(Color.WHITE)
        searchEdit.setHintTextColor(Color.WHITE)
        searchEdit.setBackgroundColor(Color.TRANSPARENT)
        searchEdit.hint = "Search"
        val fArray = arrayOfNulls<InputFilter>(2)
        fArray[0] = LengthFilter(40)
        fArray[1] = InputFilter { source, start, end, dest, dstart, dend ->
            for (i in start until end) {
                if (!Character.isLetterOrDigit(source[i])) return@InputFilter ""
            }
            null
        }
        searchEdit.filters = fArray
        val v = searchView.findViewById<View>(R.id.search_plate)
        v.setBackgroundColor(Color.TRANSPARENT)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String): Boolean {
                return false
            }

            override fun onQueryTextChange(s: String): Boolean {
                val filterList = ArrayList<AbstractGooglePlay>()
                if (s.length > 0) {
                    for (i in modelList.indices) {
                        if (modelList[i].title!!.toLowerCase().contains(s.toLowerCase())) {
                            filterList.add(modelList[i])
                            mAdapter!!.updateList(filterList)
                        }
                    }
                } else {
                    mAdapter!!.updateList(modelList)
                }
                return false
            }
        })
        return true
    }

    private fun setAdapter() {
        val singleItemList = ArrayList<AbstractGooglePlay>()
        singleItemList.add(AbstractGooglePlay("Android", "Hello " + " Android"))
        singleItemList.add(AbstractGooglePlay("Beta", "Hello " + " Beta"))
        singleItemList.add(AbstractGooglePlay("Cupcake", "Hello " + " Cupcake"))
        singleItemList.add(AbstractGooglePlay("Donut", "Hello " + " Donut"))
        singleItemList.add(AbstractGooglePlay("Eclair", "Hello " + " Eclair"))
        singleItemList.add(AbstractGooglePlay("Froyo", "Hello " + " Froyo"))
        singleItemList.add(AbstractGooglePlay("Gingerbread", "Hello " + " Gingerbread"))
        singleItemList.add(AbstractGooglePlay("Honeycomb", "Hello " + " Honeycomb"))
        singleItemList.add(AbstractGooglePlay("Ice Cream Sandwich", "Hello " + " Ice Cream Sandwich"))
        singleItemList.add(AbstractGooglePlay("Jelly Bean", "Hello " + " Jelly Bean"))
        singleItemList.add(AbstractGooglePlay("KitKat", "Hello " + " KitKat"))
        singleItemList.add(AbstractGooglePlay("Lollipop", "Hello " + " Lollipop"))
        singleItemList.add(AbstractGooglePlay("Marshmallow", "Hello " + " Marshmallow"))
        singleItemList.add(AbstractGooglePlay("Nougat", "Hello " + " Nougat"))
        singleItemList.add(AbstractGooglePlay("Android O", "Hello " + " Android O"))
        modelList.add(AbstractGooglePlay("Android", "Hello " + " Android", singleItemList))
        modelList.add(AbstractGooglePlay("Beta", "Hello " + " Beta", singleItemList))
        modelList.add(AbstractGooglePlay("Cupcake", "Hello " + " Cupcake", singleItemList))
        modelList.add(AbstractGooglePlay("Donut", "Hello " + " Donut", singleItemList))
        modelList.add(AbstractGooglePlay("Eclair", "Hello " + " Eclair", singleItemList))
        modelList.add(AbstractGooglePlay("Froyo", "Hello " + " Froyo", singleItemList))
        modelList.add(AbstractGooglePlay("Gingerbread", "Hello " + " Gingerbread", singleItemList))
        modelList.add(AbstractGooglePlay("Honeycomb", "Hello " + " Honeycomb", singleItemList))
        modelList.add(AbstractGooglePlay("Ice Cream Sandwich", "Hello " + " Ice Cream Sandwich", singleItemList))
        modelList.add(AbstractGooglePlay("Jelly Bean", "Hello " + " Jelly Bean", singleItemList))
        modelList.add(AbstractGooglePlay("KitKat", "Hello " + " KitKat", singleItemList))
        modelList.add(AbstractGooglePlay("Lollipop", "Hello " + " Lollipop", singleItemList))
        modelList.add(AbstractGooglePlay("Marshmallow", "Hello " + " Marshmallow", singleItemList))
        modelList.add(AbstractGooglePlay("Nougat", "Hello " + " Nougat", singleItemList))
        modelList.add(AbstractGooglePlay("Android O", "Hello " + " Android O", singleItemList))
        mAdapter = GooglePlayAdapter(this@GooglePlayActivity, modelList)
        recyclerView!!.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.adapter = mAdapter
        mAdapter!!.SetOnMoreClickListener(object : OnMoreClickListener {
            override fun onMoreClick(view: View?, position: Int, model: AbstractGooglePlay?) {
                Toast.makeText(this@GooglePlayActivity, "See more $position", Toast.LENGTH_SHORT).show()
            }
        })
        mAdapter!!.SetOnItemClickListener(object : GooglePlayAdapter.OnItemClickListener {
            override fun onItemClick(view: View?, absolutePosition: Int, relativePosition: Int, model: AbstractGooglePlay?) {

                //handle item click events here
                Toast.makeText(this@GooglePlayActivity, "Absolute Pos: " + absolutePosition
                        + " Relative Pos: " + relativePosition, Toast.LENGTH_SHORT).show()
            }
        })
    }
}