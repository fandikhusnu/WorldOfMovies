package id.sch.smktelkom_mlg.privateassignment.xirpl511.worldofmovies.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl511.worldofmovies.R;
import id.sch.smktelkom_mlg.privateassignment.xirpl511.worldofmovies.adapter.PopularAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl511.worldofmovies.model.Popular;
import id.sch.smktelkom_mlg.privateassignment.xirpl511.worldofmovies.model.PopularResponse;
import id.sch.smktelkom_mlg.privateassignment.xirpl511.worldofmovies.service.GsonGetRequest;
import id.sch.smktelkom_mlg.privateassignment.xirpl511.worldofmovies.service.VolleySingleton;

/**
 * A simple {@link Fragment} subclass.
 */
public class PopularFragment extends Fragment {
    ArrayList<Popular> popular_mList = new ArrayList<>();
    PopularAdapter popular_mAdapter;

    public PopularFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.popular_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        popular_mAdapter = new PopularAdapter(this.getActivity(), popular_mList);
        recyclerView.setAdapter(popular_mAdapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        PopularDataSources();
    }

    private void PopularDataSources() {
        String url =
                "https://api.themoviedb.org/3/movie/popular?api_key=42b94af512727f00271d9c67c7b2120a";

        GsonGetRequest<PopularResponse> myRequest = new GsonGetRequest<PopularResponse>
                (url, PopularResponse.class, null, new Response.Listener<PopularResponse>() {

                    @Override
                    public void onResponse(PopularResponse response) {
                        Log.d("FLOW", "onResponse: " + (new Gson().toJson(response)));


                        popular_mList.addAll(response.results);
                        popular_mAdapter.notifyDataSetChanged();

                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("FLOW", "onErrorResponse: ", error);
                    }
                });
        VolleySingleton.getInstance(this.getActivity()).addToRequestQueue(myRequest);
    }
}