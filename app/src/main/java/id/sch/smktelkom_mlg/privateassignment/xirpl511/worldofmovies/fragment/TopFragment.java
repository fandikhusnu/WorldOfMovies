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
import id.sch.smktelkom_mlg.privateassignment.xirpl511.worldofmovies.adapter.TopAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl511.worldofmovies.model.Top;
import id.sch.smktelkom_mlg.privateassignment.xirpl511.worldofmovies.model.TopResponse;
import id.sch.smktelkom_mlg.privateassignment.xirpl511.worldofmovies.service.GsonGetRequest;
import id.sch.smktelkom_mlg.privateassignment.xirpl511.worldofmovies.service.VolleySingleton;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopFragment extends Fragment {
    ArrayList<Top> top_mList = new ArrayList<>();
    TopAdapter top_mAdapter;

    public TopFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_rated, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.topRated_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        top_mAdapter = new TopAdapter(this.getActivity(), top_mList);
        recyclerView.setAdapter(top_mAdapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        TopDataSources();
    }

    private void TopDataSources() {
        String url = "https://api.themoviedb.org/3/movie/top_rated?api_key=42b94af512727f00271d9c67c7b2120a";

        GsonGetRequest<TopResponse> myRequest = new GsonGetRequest<TopResponse>
                (url, TopResponse.class, null, new Response.Listener<TopResponse>() {

                    @Override
                    public void onResponse(TopResponse response) {
                        Log.d("FLOW", "onResponse: " + (new Gson().toJson(response)));


                        top_mList.addAll(response.results);
                        top_mAdapter.notifyDataSetChanged();

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