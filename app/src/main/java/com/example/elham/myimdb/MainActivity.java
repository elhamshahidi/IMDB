package com.example.elham.myimdb;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.elham.myimdb.adapter.ToolbarAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ToolbarAdapter adapter;
    FragmentManager fragmentManager;
    private static final String TAG = MainActivity.class.getSimpleName();

    // TODO - insert your themoviedb.org API KEY here
    private final static String API_KEY = "e6bd59464d3fe8e4d302408cbb5c5204";

    private ArrayList<String> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayList.add("Latest Movies");
        arrayList.add("Now Playing");
        arrayList.add("Popular Movies");
        arrayList.add("Top Rated");
        arrayList.add("Upcoming Movies");

        fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.frame_container, new LatestFrag()).commit();


        LinearLayoutManager layoutManager= new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        adapter = new ToolbarAdapter(arrayList ,getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);



    }


}
