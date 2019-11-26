package com.example.cocktailapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    final static String drinkUri = "https://www.thecocktaildb.com/api/json/v1/1/filter.php?c=Cocktail";

    private RecyclerView recyclerView;
    private HomeListAdapter homeListAdapter;
    private List<CockTailModal> cockTailModalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        toolbar.setTitle("CockTail App");

        recyclerView =  findViewById(R.id.home_listView);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);


        cockTailModalList = new ArrayList<>();

        getCockTail();


    }

    private void getCockTail() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        final StringRequest stringRequest =new StringRequest(Request.Method.GET, drinkUri, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray jsonArray = jsonObject.getJSONArray("drinks");

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject cocktailObject= jsonArray.getJSONObject(i);

                       String drinkName= cocktailObject.getString("strDrink");
                       String drinkImage =cocktailObject.getString("strDrinkThumb");

                         CockTailModal cockTailModal = new CockTailModal(drinkName,drinkImage);
                        cockTailModalList.add(cockTailModal);
                    }
                     homeListAdapter = new HomeListAdapter(MainActivity.this,cockTailModalList);
                    recyclerView.setAdapter(homeListAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    }

