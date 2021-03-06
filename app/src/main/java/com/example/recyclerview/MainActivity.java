package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private  static  final String URL_DATA="https://simplifiedcoding.net/demos/marvel/";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ModelClass>  listItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //recyclerView = findViewById(R.id.recycler_view);
        //LinearLayoutManager manager = new LinearLayoutManager(this);
        //recyclerView.setLayoutManager(manager);
        //recyclerView.setHasFixedSize(true);
        //adapter = new MyAdapter();
       // recyclerView.setAdapter(adapter);

        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
       recyclerView.setLayoutManager(new LinearLayoutManager(this));
       listItems= new ArrayList<>();
       loadRecyclerViewData();




    }
    private  void loadRecyclerViewData(){
        ProgressDialog progressDialog= new ProgressDialog(this);
        progressDialog.setMessage("Loading Data.....");
        progressDialog.show();
        StringRequest stringRequest= new StringRequest(Request.Method.GET,
                URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                           // JSONObject jsonObject=new JSONObject(response);
                            JSONArray array= new JSONArray(response);
                            for(int i=0; i<array.length(); i++){
                                JSONObject o=array.getJSONObject(i);
                                ModelClass item=new ModelClass(
                                        o.getString("name"),
                                        o.getString("bio"),
                                        o.getString("imageurl")
                                );
                                listItems.add(item);
                            }
                            adapter= new MyAdapter(listItems,getApplicationContext());
                            recyclerView.setAdapter(adapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}