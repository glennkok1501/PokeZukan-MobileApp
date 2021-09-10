package com.gmail.pokedex.Main.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.gmail.pokedex.Main.Adapters.AbilityAdapter;
import com.gmail.pokedex.Model.AbilityBrief;
import com.gmail.pokedex.Utils.ProgressBarHelper;
import com.gmail.pokedex.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class AbilitiyFragment extends Fragment {

    private Context context;
    private RecyclerView abilityRV;
    private AbilityAdapter adapter;

    public AbilitiyFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_abilitiy, container, false);
        context = view.getContext();
        RequestQueue mQueue = Volley.newRequestQueue(context);
        abilityRV = view.findViewById(R.id.ability_RV);
        ProgressBar progressBar = view.findViewById(R.id.main_pokemon_progressbar);
        ProgressBarHelper pbh = new ProgressBarHelper(progressBar);

        ArrayList<AbilityBrief> abilities = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        abilityRV.setLayoutManager(layoutManager);
        abilityRV.setItemAnimator(new DefaultItemAnimator());
        adapter = new AbilityAdapter(abilities);
        abilityRV.setAdapter(adapter);

        String url = context.getString(R.string.cdn)+"/abilities/all.json";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            pbh.show();
                            JSONArray results = response.getJSONArray("abilities");
                            for (int i = 0; i < results.length(); i++){
                                AbilityBrief a = parseAbilityBrief(results.getJSONObject(i));
                                if (a != null){
                                    abilities.add(a);
                                }
                            }
                            adapter.notifyItemRangeChanged(0, abilities.size());
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

    private AbilityBrief parseAbilityBrief(JSONObject obj){
        AbilityBrief a = new AbilityBrief();
        try{
            a.setName(obj.getString("name"));
            a.setLink(obj.getString("link"));
        }
        catch (Exception e){
            return null;
        }
        return a;
    }
}