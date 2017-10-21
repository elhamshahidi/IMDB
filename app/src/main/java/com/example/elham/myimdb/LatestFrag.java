package com.example.elham.myimdb;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.elham.myimdb.adapter.MoviesAdapter;
import com.example.elham.myimdb.model.Movie;
import com.example.elham.myimdb.model.MoviesResponse;
import com.example.elham.myimdb.rest.ApiClient;
import com.example.elham.myimdb.rest.ApiInterface;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class LatestFrag extends Fragment {

      View view;
    RecyclerView recyclerView;
    MoviesAdapter moviesAdapter;

    private static final String TAG = MainActivity.class.getSimpleName();

    // TODO - insert your themoviedb.org API KEY here
    private final static String API_KEY = "e6bd59464d3fe8e4d302408cbb5c5204";


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_latest_frag, container, false);

        if (API_KEY.isEmpty()) {
            Toast.makeText(getActivity(), "Please obtain your API KEY first from themoviedb.org", Toast.LENGTH_LONG).show();

        }
          recyclerView = (RecyclerView) view.findViewById(R.id.movies_recycler_view);
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Observable<MoviesResponse> call = apiService.getTopRatedMovies(API_KEY);

        try{

            call.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<MoviesResponse>() {

                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                        }

                        @Override
                        public void onNext(MoviesResponse moviesResponse) {
                            List<Movie> movies = moviesResponse.getResults();

                            moviesAdapter = new MoviesAdapter(movies, R.layout.list_item_view, getContext());

                            moviesAdapter.updateAnswers(moviesResponse.getResults());



                            RecyclerView.LayoutManager mLayoutManager =(new GridLayoutManager(getContext(), 2));

                            recyclerView.setLayoutManager(mLayoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            recyclerView.setAdapter(moviesAdapter);
                        }
                    });

        }catch (Exception e){
            Toast.makeText(getContext() , "can not connect" ,Toast.LENGTH_SHORT).show();
        }


//        call.enqueue(new Callback<MoviesResponse>() {
//            @Override
//            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
//                List<Movie> movies = response.body().getResults();
//                RecyclerView.LayoutManager mLayoutManager =(new GridLayoutManager(getContext(), 2));
//
//                recyclerView.setLayoutManager(mLayoutManager);
//                recyclerView.setItemAnimator(new DefaultItemAnimator());
//                recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_view, getContext()));
//                Log.d(TAG, "Number of movies received: " + movies.size());
//            }
//
//            @Override
//            public void onFailure(Call<MoviesResponse>call, Throwable t) {
//                // Log error here since request failed
//                Log.e(TAG, t.toString());
//            }
//        });



        return view;
    }


}

