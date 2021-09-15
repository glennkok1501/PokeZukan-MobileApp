package com.gmail.gk_dev_software.pokezukan.PokemonActivity.Fragments;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.gmail.gk_dev_software.pokezukan.Model.Pokemon;
import com.gmail.gk_dev_software.pokezukan.Model.PokemonMove;
import com.gmail.gk_dev_software.pokezukan.PokemonActivity.Adapters.Moves.PokemonMovesAdapter;
import com.gmail.gk_dev_software.pokezukan.R;
import com.gmail.gk_dev_software.pokezukan.Utils.Comparators.PokemonMoveComparator;
import com.gmail.gk_dev_software.pokezukan.Utils.EmptyDataHelper;
import com.gmail.gk_dev_software.pokezukan.Utils.ProgressBarHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MovesFragment extends Fragment {
    private Context context;
    private Pokemon pokemon;
    private ArrayList<PokemonMove> moves;
    private TextView levelUp, tutor, machine, eggMove;
    private TextView[] filters;
    private PokemonMovesAdapter adapter;

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

        levelUp = view.findViewById(R.id.move_levelUp_textView);
        tutor = view.findViewById(R.id.move_tutor_textView);
        machine = view.findViewById(R.id.move_machine_textView);
        eggMove = view.findViewById(R.id.move_eggMove_textView);
        filters = new TextView[] {levelUp, tutor, machine, eggMove};

        moves = new ArrayList<>();
        pokemon = (Pokemon) getArguments().getSerializable("pokemon");

        RequestQueue mQueue = Volley.newRequestQueue(context);
        RecyclerView movesRV = view.findViewById(R.id.moves_RV);
        ProgressBar progressBar = view.findViewById(R.id.moves_progressbar);
        ProgressBarHelper pbh = new ProgressBarHelper(progressBar);

        EmptyDataHelper emptyDataHelper = new EmptyDataHelper(view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        movesRV.setLayoutManager(layoutManager);
        movesRV.setItemAnimator(new DefaultItemAnimator());
        adapter = new PokemonMovesAdapter(moves, emptyDataHelper);
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
                            selectFilter(filters, 0);
//                            adapter.updateData();
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
        initFilter();
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

    private void selectFilter(TextView[] ls, int sel){
        for (TextView t : ls) {
            t.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.white), PorterDuff.Mode.SRC_IN);
            t.setTextColor(ContextCompat.getColor(context, R.color.black));
        }
        ls[sel].getBackground().setColorFilter(ContextCompat.getColor(context, R.color.gray), PorterDuff.Mode.SRC_IN);
        ls[sel].setTextColor(ContextCompat.getColor(context, R.color.white));
    }

    private void initFilter(){
        levelUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectFilter(filters, 0);
                adapter.filter(filterMoves(PokemonMove.LEVEL_UP, moves));
            }
        });
        tutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectFilter(filters, 1);
                adapter.filter(filterMoves(PokemonMove.TUTOR, moves));
            }
        });
        machine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectFilter(filters, 2);
                adapter.filter(filterMoves(PokemonMove.MACHINE, moves));
            }
        });
        eggMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectFilter(filters, 3);
                adapter.filter(filterMoves(PokemonMove.EGG, moves));
            }
        });
    }
}