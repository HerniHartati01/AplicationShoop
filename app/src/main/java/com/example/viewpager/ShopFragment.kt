package com.example.viewpager

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.viewpager.adapter.ApiAdapter
import com.example.viewpager.adapter.CategoryAdapter
import com.example.viewpager.adapter.ProductAdapter
import com.example.viewpager.model.Category
import com.example.viewpager.model.Product
import com.example.viewpager.model.ProductApiItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ShopFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    //CategoryList
    private lateinit var categoryRecyclarView : RecyclerView
    private lateinit var categoryList : ArrayList<Category>
    private lateinit var categoryAdapter : CategoryAdapter

    //ProductList
    private lateinit var productRecyclarView : RecyclerView
    private lateinit var productList: ArrayList<Product>
    private lateinit var productAdapter: ProductAdapter

    //Search
    private lateinit var searchView: SearchView
    private lateinit var searchList: ArrayList<Product>

    //API
    var BASE_URL ="https://fakestoreapi.com"
    lateinit var apiAdapter : ApiAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shop, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ShopFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ShopFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //CategoryList
        val layoutManagerCategoryList = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
        categoryRecyclarView = view.findViewById(R.id.categoryList)
        categoryRecyclarView.setHasFixedSize(true)
        categoryRecyclarView.layoutManager = layoutManagerCategoryList

        categoryList = ArrayList()
        categoryList.add(Category(R.drawable.coverelectronic, "Electronics", "18 Brands"))
        categoryList.add(Category(R.drawable.makeupcover, "MakeUp", "22 Brands" ))
        categoryList.add(Category(R.drawable.housecover,"House Stuff", "15 Brands"))

        categoryAdapter = CategoryAdapter(categoryList)
        categoryRecyclarView.adapter = categoryAdapter

        //ProductList
        /*val layoutManagerProductList = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
        productRecyclarView = view.findViewById(R.id.productList)
        productRecyclarView.setHasFixedSize(true)
        productRecyclarView.layoutManager = layoutManagerProductList*/

        /*//Search Product
        searchView = view.findViewById(R.id.search)
        searchList = arrayListOf<Product>()

        searchView.clearFocus()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query : String?): Boolean {
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange( newText : String?): Boolean {
                searchList.clear()
                val searchText = newText!!.toLowerCase(Locale.getDefault())
                if (searchText.isNotEmpty()){
                    productList.forEach {
                        if (it.nameProduct.toLowerCase(Locale.getDefault()).contains(searchText)){
                            productList.add(it)
                        }
                    }
                    productRecyclarView.adapter!!.notifyDataSetChanged()
                }else{
                    searchList.clear()
                    searchList.addAll(productList)
                    productRecyclarView.adapter!!.notifyDataSetChanged()
                }
                return false
            }
        })*/

        /*productList = ArrayList()
        productList.add(Product(R.drawable.product6, "Cozy Cusion Up To 30% Disc", "Rp 1.200.000", R.drawable.love_active))
        productList.add(Product(R.drawable.product1, "IPhone 14xx Up To 30% Disc", "Rp 12.000.000", R.drawable.love_nonactive))
        productList.add(Product(R.drawable.product5, "Tab Apple23 Up To 30% Disc", "Rp 5.000.000", R.drawable.love_nonactive))
        productList.add(Product(R.drawable.product7, "Cute Baby Doll4@ Up To 30% Disc", "Rp 700.000", R.drawable.love_nonactive))
        productList.add(Product(R.drawable.product3, "IPhone 13@ECyyy To 30% Disc", "Rp 11.200.000", R.drawable.love_nonactive))
        productList.add(Product(R.drawable.product2, "Earphone 13@ECyyy To 30% Disc", "Rp 2.000.000", R.drawable.love_nonactive))
        productList.add(Product(R.drawable.product4, "Keyboard Gamer Nice To 30% Disc", "Rp 3.200.000", R.drawable.love_nonactive))

        productAdapter = ProductAdapter(productList)
        productRecyclarView.adapter = productAdapter

        productAdapter.onItemClick ={
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("nameProduct", it)
            startActivity(intent)
        }
        */

        //Get Data API
        val layoutManagerProductList = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
        productRecyclarView = view.findViewById(R.id.productList)
        productRecyclarView.setHasFixedSize(true)
        productRecyclarView.layoutManager = layoutManagerProductList
        getAllData()
    }

    private fun getAllData() {
        var retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        var retroData = retrofit.getData()
        retroData.enqueue(object : Callback<List<ProductApiItem>>{
            override fun onResponse(
                call: Call<List<ProductApiItem>>,
                response: Response<List<ProductApiItem>>
            ) {
                var data = response.body()!!
                apiAdapter = ApiAdapter(context!!, data)
                productRecyclarView.adapter = apiAdapter
                apiAdapter.onItemClick ={
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra("intent_title", it)
                    startActivity(intent)
                }
                Log.d("data", data.toString())
            }

            override fun onFailure(call: Call<List<ProductApiItem>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }
}


