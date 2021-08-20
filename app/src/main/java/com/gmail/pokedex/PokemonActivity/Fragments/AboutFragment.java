package com.gmail.pokedex.PokemonActivity.Fragments;

import static android.content.Context.WINDOW_SERVICE;

import android.content.Context;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.gmail.pokedex.Model.Pokemon;
import com.gmail.pokedex.R;
import com.gmail.pokedex.Utils.TypeImageHelper;
import com.google.android.material.bottomsheet.BottomSheetBehavior;


public class AboutFragment extends Fragment {

    private Pokemon pokemon;
    private LinearLayout mBottomSheetLayout;
    private BottomSheetBehavior sheetBehavior;
    private ImageView header_Arrow_Image;

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
        TextView dex_id = view.findViewById(R.id.aboutFragment_id_textView);
        TextView name = view.findViewById(R.id.aboutFragment_name_textView);

        setPokemonImage(view.getContext(), pokemon.getSprites().getOther().getOfficial_artwork().getFront_default(), pokemonImage);

        TypeImageHelper typeImageHelper = new TypeImageHelper();
        typeImageHelper.setType(pokemon.getTypes(), type1Image, type2Image);

        dex_id.setText(String.format("#%03d", pokemon.getId()));
        name.setText(pokemon.getName());

        //Bottom sheet
        mBottomSheetLayout = view.findViewById(R.id.bottom_sheet_layout);
        sheetBehavior = BottomSheetBehavior.from(mBottomSheetLayout);
        header_Arrow_Image = view.findViewById(R.id.bottom_sheet_arrow);

        header_Arrow_Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED){
                    sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

            }
        });

        sheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
            }
            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

                header_Arrow_Image.setRotation(slideOffset * 180);
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
                .placeholder(R.drawable.ic_pokeball_color)
                .override(px, px)
                .error(R.drawable.ic_pokeball)
                .into(imageView);
    }
}