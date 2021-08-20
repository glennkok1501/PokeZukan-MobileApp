package com.gmail.pokedex.Utils;

import android.view.View;
import android.widget.ImageView;

import com.gmail.pokedex.Model.PokemonType;
import com.gmail.pokedex.R;

import java.util.ArrayList;

public class TypeImageHelper {

    public TypeImageHelper() {
    }

    public void setType(ArrayList<PokemonType> types, ImageView image1, ImageView image2){
        image2.setVisibility(View.INVISIBLE);
        int type1 = getImage(types.get(0).getType().getName());
        setImage(type1, image1);
        if (types.size() > 1){
            image2.setVisibility(View.VISIBLE);
            int type2 = getImage(types.get(1).getType().getName());
            setImage(type2, image2);
        }
    }

    private void setImage(int image, ImageView imageView){
        if (image > 0){
            imageView.setImageResource(image);
        }
    }

    public int getImage(String type){
        int image;
        switch (type){
            case "bug":
                image = R.drawable.ic_bug;
                break;
            case "dark":
                image = R.drawable.ic_dark;
                break;
            case "dragon":
                image = R.drawable.ic_dragon;
                break;
            case "electric":
                image = R.drawable.ic_electric;
                break;
            case "fairy":
                image = R.drawable.ic_fairy;
                break;
            case "fighting":
                image = R.drawable.ic_fighting;
                break;
            case "fire":
                image = R.drawable.ic_fire;
                break;
            case "flying":
                image = R.drawable.ic_flying;
                break;
            case "ghost":
                image = R.drawable.ic_ghost;
                break;
            case "grass":
                image = R.drawable.ic_grass;
                break;
            case "ground":
                image = R.drawable.ic_ground;
                break;
            case "ice":
                image = R.drawable.ic_ice;
                break;
            case "normal":
                image = R.drawable.ic_normal;
                break;
            case "poison":
                image = R.drawable.ic_poison;
                break;
            case "psychic":
                image = R.drawable.ic_psychic;
                break;
            case "rock":
                image = R.drawable.ic_rock;
                break;
            case "steel":
                image = R.drawable.ic_steel;
                break;
            case "water":
                image = R.drawable.ic_water;
                break;
            default:
                image = R.drawable.ic_launcher_foreground;
        }
        return image;
    }
}
