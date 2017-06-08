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
import id.sch.smktelkom_mlg.privateassignment.xirpl511.worldofmovies.adapter.NowAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl511.worldofmovies.model.Now;
import id.sch.smktelkom_mlg.privateassignment.xirpl511.worldofmovies.model.NowResponse;
import id.sch.smktelkom_mlg.privateassignment.xirpl511.worldofmovies.service.GsonGetRequest;
import id.sch.smktelkom_mlg.privateassignment.xirpl511.worldofmovies.service.VolleySingleton;

/**
 * A simple {@link Fragment} subclass.
 */
public class NowFragment extends Fragment {
    ArrayList<Now> now_mList = new ArrayList<>();
    NowAdapter now_mAdapter;

    public NowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_now, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.now_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        now_mAdapter = new NowAdapter(this.getActivity(), now_mList);
        recyclerView.setAdapter(now_mAdapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        NowDataSources();
    }

    private void NowDataSources() {
        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=42b94af512727f00271d9c67c7b2120a";

        GsonGetRequest<NowResponse> myRequest = new GsonGetRequest<NowResponse>
                (url, NowResponse.class, null, new Response.Listener<NowResponse>() {

                    @Override
                    public void onResponse(NowResponse response) {
                        Log.d("FLOW", "onResponse: " + (new Gson().toJson(response)));


                        now_mList.addAll(response.results);
                        now_mAdapter.notifyDataSetChanged();

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
