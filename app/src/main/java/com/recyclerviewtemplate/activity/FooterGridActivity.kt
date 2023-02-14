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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.recyclerviewtemplate.AbstractModel
import com.recyclerviewtemplate.GridMarginDecoration
import com.recyclerviewtemplate.R
import com.recyclerviewtemplate.adapter.FooterGridAdapter
import java.util.*

class FooterGridActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var toolbar: Toolbar? = null
    private var swipeRefreshRecyclerList: SwipeRefreshLayout? = null
    private var fab: FloatingActionButton? = null
    private var mAdapter: FooterGridAdapter? = null
    private val modelList = ArrayList<AbstractModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_footer_grid)
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
                val filterList = ArrayList<AbstractModel>()
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
        modelList.add(AbstractModel("Android", "Hello " + " Android"))
        modelList.add(AbstractModel("Beta", "Hello " + " Beta"))
        modelList.add(AbstractModel("Cupcake", "Hello " + " Cupcake"))
        modelList.add(AbstractModel("Donut", "Hello " + " Donut"))
        modelList.add(AbstractModel("Eclair", "Hello " + " Eclair"))
        modelList.add(AbstractModel("Froyo", "Hello " + " Froyo"))
        modelList.add(AbstractModel("Gingerbread", "Hello " + " Gingerbread"))
        modelList.add(AbstractModel("Honeycomb", "Hello " + " Honeycomb"))
        modelList.add(AbstractModel("Ice Cream Sandwich", "Hello " + " Ice Cream Sandwich"))
        modelList.add(AbstractModel("Jelly Bean", "Hello " + " Jelly Bean"))
        modelList.add(AbstractModel("KitKat", "Hello " + " KitKat"))
        modelList.add(AbstractModel("Lollipop", "Hello " + " Lollipop"))
        modelList.add(AbstractModel("Marshmallow", "Hello " + " Marshmallow"))
        modelList.add(AbstractModel("Nougat", "Hello " + " Nougat"))
        modelList.add(AbstractModel("Android O", "Hello " + " Android O"))
        mAdapter = FooterGridAdapter(this@FooterGridActivity, modelList, "Footer")
        recyclerView!!.setHasFixedSize(true)
        val layoutManager = GridLayoutManager(this@FooterGridActivity, 2)
        recyclerView!!.addItemDecoration(GridMarginDecoration(this@FooterGridActivity, 2, 2, 2, 2))
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.adapter = mAdapter
        mAdapter!!.SetOnItemClickListener(object : FooterGridAdapter.OnItemClickListener {
            override fun onItemClick(view: View?, position: Int, model: AbstractModel?) {

                //handle item click events here
                Toast.makeText(this@FooterGridActivity, "Hey " + model!!.title, Toast.LENGTH_SHORT).show()
            }
        })
        mAdapter!!.SetOnFooterClickListener(object : FooterGridAdapter.OnFooterClickListener {
            override fun onFooterClick(view: View?, footerTitle: String?) {

                //handle item click events here
                Toast.makeText(this@FooterGridActivity, "Hey I am a footer", Toast.LENGTH_SHORT).show()
            }
        })
    }
}