package id.sch.smktelkom_mlg.privateassignment.xirpl317.mokletcinemax;


import android.os.Bundle;
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

import id.sch.smktelkom_mlg.privateassignment.xirpl317.mokletcinemax.adapter.TopAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl317.mokletcinemax.model.Results;
import id.sch.smktelkom_mlg.privateassignment.xirpl317.mokletcinemax.model.ResultsRespone;
import id.sch.smktelkom_mlg.privateassignment.xirpl317.mokletcinemax.service.GsonGetRequest;
import id.sch.smktelkom_mlg.privateassignment.xirpl317.mokletcinemax.service.VolleySingleton;


/**
 * A simple {@link Fragment} subclass.
 */
public class TopFragment extends Fragment {
    ArrayList<Results> mlist = new ArrayList<>();
    TopAdapter topAdapter;


    public TopFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_top, container, false);
        RecyclerView rv = (RecyclerView) rootview.findViewById(R.id.recyclerTop);
        rv.setHasFixedSize(true);
        topAdapter = new TopAdapter(this, mlist, getContext());
        rv.setAdapter(topAdapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        downloadDataResource();
        return rootview;

    }

    private void downloadDataResource() {
        String url = "https://api.themoviedb.org/3/movie/top_rated?api_key=fb9cf49e2df500beca3a138fa9697205&language=en-US&page=1";

        GsonGetRequest<ResultsRespone> myRequest = new GsonGetRequest<ResultsRespone>
                (url, ResultsRespone.class, null, new Response.Listener<ResultsRespone>() {

                    @Override
                    public void onResponse(ResultsRespone response) {
                        Log.d("FLOW", "onResponse: " + (new Gson().toJson(response)));
                        mlist.addAll(response.results);
                        topAdapter.notifyDataSetChanged();
                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("FLOW", "onErrorResponse: ", error);
                    }
                });
        VolleySingleton.getInstance(getActivity()).addToRequestQueue(myRequest);
    }
}
