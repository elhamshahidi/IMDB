package com.example.elham.myimdb.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.elham.myimdb.LatestFrag;
import com.example.elham.myimdb.NowPlaying;
import com.example.elham.myimdb.Popular;
import com.example.elham.myimdb.R;
import com.example.elham.myimdb.TopRated;
import com.example.elham.myimdb.Upcoming;

import java.util.ArrayList;

/**
 * Created by Elham on 9/22/2017.
 */

public class ToolbarAdapter extends RecyclerView.Adapter<ToolbarAdapter.MyViewHolder> {
    Context context ;
    FragmentManager fragmentManager;

    //    private List<SafarNameS> safarNameSList;
    private ArrayList<String> safarNameSList = new ArrayList<>();


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtitem;


        public MyViewHolder(View view) {
            super(view);
            txtitem = (TextView) view.findViewById(R.id.txtitem);

        }
    }

    public ToolbarAdapter(ArrayList<String> safarNameSList , Context context) {
        this.safarNameSList= safarNameSList;
        this.context = context;
    }

    @Override
    public ToolbarAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.toolbar_item, parent, false);
        context = parent.getContext();
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ToolbarAdapter.MyViewHolder holder, final int position) {




        String s = safarNameSList.get(position);
        holder.txtitem.setText(s);

        holder.txtitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                switch(position) {


                    case 0 :

                        fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                        fragmentManager
                                .beginTransaction()
                                .replace(R.id.frame_container, new LatestFrag()).commit();

                        break;
                    case 1 :

                        fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                        fragmentManager
                                .beginTransaction()
                                .replace(R.id.frame_container, new NowPlaying()).commit();//                        holder.txtitem.setTextColor(Color.WHITE);

                        break;
                    case 2 :

                        fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                        fragmentManager
                                .beginTransaction()
                                .replace(R.id.frame_container, new Popular()).commit();

                        break;
                    case 3 :

                        fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                        fragmentManager
                                .beginTransaction()
                                .replace(R.id.frame_container, new TopRated()).commit();

                        break;
                    case 4 :
                        fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                        fragmentManager
                                .beginTransaction()
                                .replace(R.id.frame_container, new Upcoming()).commit();
                        break;


                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return safarNameSList.size();
    }




}
