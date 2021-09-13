package com.gmail.pokezukan.PokemonActivity.Fragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.gmail.pokezukan.Model.Location;
import com.gmail.pokezukan.Model.Pokemon;
import com.gmail.pokezukan.Model.PokemonMove;
import com.gmail.pokezukan.PokemonActivity.Adapters.Moves.PokemonMovesAdapter;
import com.gmail.pokezukan.R;
import com.gmail.pokezukan.Utils.Comparators.PokemonMoveComparator;
import com.gmail.pokezukan.Utils.EmptyDataHelper;
import com.gmail.pokezukan.Utils.ProgressBarHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MovesFragment extends Fragment {
    private Context context;
    private Pokemon pokemon;
    private ArrayList<PokemonMove> moves;

    public MovesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_moves, container, false);
        context = view.getContext();

        moves = new ArrayList<>();
        pokemon = (Pokemon) getArguments().getSerializable("pokemon");
        ImageView filterImage = view.findViewById(R.id.moves_filter_imageView);

        RequestQueue mQueue = Volley.newRequestQueue(context);
        RecyclerView movesRV = view.findViewById(R.id.moves_RV);
        ProgressBar progressBar = view.findViewById(R.id.moves_progressbar);
        ProgressBarHelper pbh = new ProgressBarHelper(progressBar);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        movesRV.setLayoutManager(layoutManager);
        movesRV.setItemAnimator(new DefaultItemAnimator());
        PokemonMovesAdapter adapter = new PokemonMovesAdapter(moves);
        movesRV.setAdapter(adapter);

        pbh.show();
        String url = pokemon.getMoves();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray results = response.getJSONArray("moves");
                            for (int i = 0; i < results.length(); i++){
                                PokemonMove m = parseMove(results.getJSONObject(i));
                                moves.add(m);
                            }
                            adapter.filter(filterMoves(PokemonMove.LEVEL_UP, moves));
//                            adapter.updateData();
                           new EmptyDataHelper(view, moves.size());
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

        filterImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("TAG", "Filter");
            }
        });

        return view;
    }

    private ArrayList<PokemonMove> filterMoves(int filter, ArrayList<PokemonMove> moves){
        ArrayList<PokemonMove> filtered = new ArrayList<>();
        for (PokemonMove m : moves){
            if (m.getMethod() == filter){
                filtered.add(m);
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            filtered.sort(new PokemonMoveComparator());
        }
        return filtered;
    }

    private PokemonMove parseMove(JSONObject obj) throws JSONException {
        PokemonMove m = new PokemonMove();
        m.setName(obj.getString("name"));
        m.setLevel(obj.getInt("level"));
        m.setMethod(obj.getString("method"));
        return m;
    }
}