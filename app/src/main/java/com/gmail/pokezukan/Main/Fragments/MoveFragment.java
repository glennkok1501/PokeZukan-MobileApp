package com.gmail.pokezukan.Main.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import com.gmail.pokezukan.Main.Adapters.move.MoveAdapter;
import com.gmail.pokezukan.Main.Utils.FabHelper;
import com.gmail.pokezukan.Model.MoveBrief;
import com.gmail.pokezukan.R;
import com.gmail.pokezukan.Utils.ProgressBarHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MoveFragment extends Fragment {
    private Context context;
    private RecyclerView moveRV;
    private MoveAdapter adapter;
    private FloatingActionButton fab;
    private ArrayList<MoveBrief> moves;
    private FabHelper fabHelper;
    private View view;

    public MoveFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_move, container, false);
        context = view.getContext();
        RequestQueue mQueue = Volley.newRequestQueue(context);
        moveRV = view.findViewById(R.id.move_RV);
        ProgressBar progressBar = view.findViewById(R.id.main_pokemon_progressbar);
        ProgressBarHelper pbh = new ProgressBarHelper(progressBar);

        moves = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        moveRV.setLayoutManager(layoutManager);
        moveRV.setItemAnimator(new DefaultItemAnimator());
        adapter = new MoveAdapter(context, moves);
        moveRV.setAdapter(adapter);

        pbh.show();
        String url = String.format("%smoves/all.json", context.getString(R.string.pokezukan_api));
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray results = response.getJSONArray("moves");
                            for (int i = 0; i < results.length(); i++){
                                MoveBrief m = parseMoveBrief(results.getJSONObject(i));
                                if (m != null){
                                    moves.add(m);
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

    private ArrayList<MoveBrief> filter(String search_string){
        ArrayList<MoveBrief> filtered = new ArrayList<>();
        for (MoveBrief m : moves){
            String string = String.format("%s %s", m.getName().toLowerCase(), m.getType().toLowerCase());
            if (string.contains(search_string)){
                filtered.add(m);
            }
        }
        return filtered;
    }

    private MoveBrief parseMoveBrief(JSONObject obj){
        MoveBrief m = new MoveBrief();
        try{
            m.setName(obj.getString("name"));
            m.setType(obj.getString("type"));
            m.setLink(obj.getString("link"));
        }
        catch (Exception e){
            return null;
        }
        return m;
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
        fabHelper = new FabHelper(context, moveRV, fab, view, 52);
        fabHelper.setHint("Search by name or type");
    }
}