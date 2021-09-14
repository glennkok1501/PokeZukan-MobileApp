package com.gmail.pokezukan.PokemonActivity.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.gmail.pokezukan.Model.Location;
import com.gmail.pokezukan.Model.Pokemon;
import com.gmail.pokezukan.PokemonActivity.Adapters.Location.LocationAdapter;
import com.gmail.pokezukan.R;
import com.gmail.pokezukan.Utils.EmptyDataHelper;
import com.gmail.pokezukan.Utils.PokemonSerializer;
import com.gmail.pokezukan.Utils.ProgressBarHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class LocationFragment extends Fragment {

    private Pokemon pokemon;
    private ArrayList<Location> locations;
    private PokemonSerializer ps;

    public LocationFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_location, container, false);
        Context context = view.getContext();
        locations = new ArrayList<>();
        pokemon = (Pokemon) getArguments().getSerializable("pokemon");
        ps = new PokemonSerializer(context);

        RequestQueue mQueue = Volley.newRequestQueue(context);
        RecyclerView locationRV = view.findViewById(R.id.location_RV);
        ProgressBar progressBar = view.findViewById(R.id.location_progressbar);
        ProgressBarHelper pbh = new ProgressBarHelper(progressBar);

        EmptyDataHelper emptyDataHelper = new EmptyDataHelper(view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        locationRV.setLayoutManager(layoutManager);
        locationRV.setItemAnimator(new DefaultItemAnimator());
        LocationAdapter adapter = new LocationAdapter(locations, emptyDataHelper);
        locationRV.setAdapter(adapter);

        pbh.show();
        String url = pokemon.getLocation();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray results = response.getJSONArray("location");
                            for (int i = 0; i < results.length(); i++){
                                Location l = parseLocation(results.getJSONObject(i));
                                locations.add(l);
                            }
                            adapter.updateData();
                            pbh.hide();
                        }

                        catch (Exception e) {
                            Toast.makeText(context, "Data unavailable", Toast.LENGTH_SHORT).show();
                            pbh.hide();
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Data unavailable", Toast.LENGTH_LONG).show();
                pbh.hide();
                error.printStackTrace();
            }
        });
        mQueue.add(request);

        return view;
    }

    private Location parseLocation(JSONObject obj) throws JSONException {
        Location l = new Location();
        l.setGame(ps.JsonToStringList(obj.getJSONArray("game")));
        l.setArea(ps.JsonToStringList(obj.getJSONArray("area")));
        return l;
    }

}