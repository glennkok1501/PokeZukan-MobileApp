package com.gmail.pokedex.PokemonActivity.Fragments;

import static android.content.Context.WINDOW_SERVICE;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.gmail.pokedex.Model.Pokemon;
import com.gmail.pokedex.R;
import com.gmail.pokedex.Utils.TypeImageHelper;


public class AboutFragment extends Fragment {

    private Pokemon pokemon;

    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        pokemon = (Pokemon) getArguments().getSerializable("pokemon");
        ImageView pokemonImage = view.findViewById(R.id.aboutFragment_pokemon_imageView);
        ImageView type1Image = view.findViewById(R.id.aboutFragment_type1_imageView);
        ImageView type2Image = view.findViewById(R.id.aboutFragment_type2_imageView);

        int px = getSize(container.getContext());
        Glide.with(container.getContext())
                .load(pokemon.getSprites().getOther().getOfficial_artwork().getFront_default())
                .placeholder(R.drawable.pokeball)
                .override(px, px)
                .error(R.drawable.pokeball)
                .into(pokemonImage);

        TypeImageHelper typeImageHelper = new TypeImageHelper();
        typeImageHelper.setType(pokemon.getTypes(), type1Image, type2Image);


        return view;
    }

    private int getSize(Context context){
        //get window size
        WindowManager manager = (WindowManager) context.getSystemService(WINDOW_SERVICE);

        //initializing a variable for default display
        Display display = manager.getDefaultDisplay();

        Point point = new Point();
        display.getSize(point);

        int size = Math.min(point.x, point.y);

        return ((size)/5)*4;
    }
}