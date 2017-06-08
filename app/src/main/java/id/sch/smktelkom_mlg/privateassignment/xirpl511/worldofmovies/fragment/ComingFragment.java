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
import id.sch.smktelkom_mlg.privateassignment.xirpl511.worldofmovies.adapter.ComingAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl511.worldofmovies.model.Coming;
import id.sch.smktelkom_mlg.privateassignment.xirpl511.worldofmovies.model.ComingResponse;
import id.sch.smktelkom_mlg.privateassignment.xirpl511.worldofmovies.service.GsonGetRequest;
import id.sch.smktelkom_mlg.privateassignment.xirpl511.worldofmovies.service.VolleySingleton;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComingFragment extends Fragment {
    ArrayList<Coming> coming_mList = new ArrayList<>();
    ComingAdapter coming_mAdapter;

    public ComingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coming, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.coming_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        coming_mAdapter = new ComingAdapter(this.getActivity(), coming_mList);
        recyclerView.setAdapter(coming_mAdapter);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        ComingDataSources();
    }

    private void ComingDataSources() {
        String url = "https://api.themoviedb.org/3/movie/upcoming?api_key=42b94af512727f00271d9c67c7b2120a";

        GsonGetRequest<ComingResponse> myRequest = new GsonGetRequest<ComingResponse>
                (url, ComingResponse.class, null, new Response.Listener<ComingResponse>() {

                    @Override
                    public void onResponse(ComingResponse response) {
                        Log.d("FLOW", "onResponse: " + (new Gson().toJson(response)));


                        coming_mList.addAll(response.results);
                        coming_mAdapter.notifyDataSetChanged();

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
