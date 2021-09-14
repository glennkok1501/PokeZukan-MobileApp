package com.gmail.gk_dev_software.pokezukan.Main.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
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
import com.gmail.gk_dev_software.pokezukan.Main.Adapters.ability.AbilityAdapter;
import com.gmail.gk_dev_software.pokezukan.Main.Utils.FabHelper;
import com.gmail.gk_dev_software.pokezukan.Model.AbilityBrief;
import com.gmail.gk_dev_software.pokezukan.Utils.ProgressBarHelper;
import com.gmail.gk_dev_software.pokezukan.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class AbilityFragment extends Fragment {

    private Context context;
    private RecyclerView abilityRV;
    private AbilityAdapter adapter;
    private FabHelper fabHelper;
    private FloatingActionButton fab;
    private ArrayList<AbilityBrief> abilities;
    private View view;

    public AbilityFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_abilitiy, container, false);
        context = view.getContext();
        RequestQueue mQueue = Volley.newRequestQueue(context);
        abilityRV = view.findViewById(R.id.ability_RV);
        ProgressBar progressBar = view.findViewById(R.id.main_pokemon_progressbar);
        ProgressBarHelper pbh = new ProgressBarHelper(progressBar);

        abilities = new ArrayList<>();
        GridLayoutManager layoutManager = new GridLayoutManager(context, 2);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        abilityRV.setLayoutManager(layoutManager);
        abilityRV.setItemAnimator(new DefaultItemAnimator());
        adapter = new AbilityAdapter(abilities);
        abilityRV.setAdapter(adapter);

        pbh.show();
        String url = context.getString(R.string.git_repo)+"abilities/all.json";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray results = response.getJSONArray("abilities");
                            for (int i = 0; i < results.length(); i++){
                                AbilityBrief a = parseAbilityBrief(results.getJSONObject(i));
                                if (a != null){
                                    abilities.add(a);
                                }
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

    private ArrayList<AbilityBrief> filter(String search_string){
        ArrayList<AbilityBrief> filtered = new ArrayList<>();
        for (AbilityBrief a : abilities){
            String string = a.getName().toLowerCase();
            if (string.contains(search_string)){
                filtered.add(a);
            }
        }
        return filtered;
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

    @Override
    public void onResume() {
        super.onResume();
        initFab();
        fabHelper.checkFabImage();

        fabHelper.getSearchEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.filter(filter(charSequence.toString().toLowerCase()));
                if (charSequence.length()>0){
                    fabHelper.getClear().setVisibility(View.VISIBLE);
                }
                else{
                    fabHelper.getClear().setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    @Override
    public void onPause() {
        super.onPause();
        fabHelper.checkPause();
    }

    private void initFab(){
        fab =  getActivity().findViewById(R.id.main_fab);
        fabHelper = new FabHelper(context, abilityRV, fab, view, 55);
        fabHelper.setHint("Search ability name");

    }
}