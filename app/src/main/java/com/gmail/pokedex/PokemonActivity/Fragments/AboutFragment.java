package com.gmail.pokedex.PokemonActivity.Fragments;

import static android.content.Context.WINDOW_SERVICE;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gmail.pokedex.Model.Pokemon;
import com.gmail.pokedex.PokemonActivity.InfoBottomSheetDialog;
import com.gmail.pokedex.R;
import com.gmail.pokedex.Utils.AutoCap;
import com.gmail.pokedex.Utils.PokemonSerializer;
import com.gmail.pokedex.Utils.TypeHelper;

import java.util.Locale;


public class AboutFragment extends Fragment {

    private Pokemon pokemon;
    private Context context;

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
        context = view.getContext();
        pokemon = (Pokemon) getArguments().getSerializable("pokemon");
        ImageView pokemonImage = view.findViewById(R.id.aboutFragment_pokemon_imageView);
        ImageView type1Image = view.findViewById(R.id.aboutFragment_type1_imageView);
        ImageView type2Image = view.findViewById(R.id.aboutFragment_type2_imageView);
        TextView dex_id = view.findViewById(R.id.aboutFragment_id_textView);
        TextView name = view.findViewById(R.id.aboutFragment_name_textView);
        ConstraintLayout layout = view.findViewById(R.id.aboutFragment_layout);

        setPokemonImage(context, pokemon.getSprites().getLarge(), pokemonImage);
        TypeHelper typeHelper = new TypeHelper();
        typeHelper.setImages(context, pokemon.getInfo().getType(), type1Image, type2Image);
        dex_id.setText(String.format("%03d", pokemon.getId()));
        name.setText(AutoCap.set(pokemon.getName()));

        Animation pulse = AnimationUtils.loadAnimation(context, R.anim.pulse);
        pokemonImage.startAnimation(pulse);

        view.post(new Runnable() {
            @Override
            public void run() {
                InfoBottomSheetDialog dialog = new InfoBottomSheetDialog(context, pokemon);
                layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.getDialog().show();
                    }
                });
            }
        });

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

    private void setPokemonImage(Context context, String url, ImageView imageView){
        int px = getSize(context);
        Glide.with(context)
                .load(url)
                .override(px, px)
                .error(R.drawable.ic_pokeball)
                .into(imageView);
    }
}