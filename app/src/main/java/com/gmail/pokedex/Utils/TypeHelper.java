package com.gmail.pokedex.Utils;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gmail.pokedex.R;

import java.util.ArrayList;
import java.util.List;

public class TypeHelper {

    public TypeHelper() {
    }

    public void setImages(List<String> types, ImageView image1, ImageView image2){
        image2.setVisibility(View.INVISIBLE);
        int type1 = getImage(types.get(0));
        setImage(type1, image1);
        if (types.size() > 1){
            image2.setVisibility(View.VISIBLE);
            int type2 = getImage(types.get(1));
            setImage(type2, image2);
        }
    }

    private void setImage(int image, ImageView imageView){
        if (image > 0){
            imageView.setImageResource(image);
        }
    }

    public int getColor(String type){
        int color;
        switch (type){
            case "bug":
                color = R.color.bug;
                break;
            case "dark":
                color = R.color.dark;
                break;
            case "dragon":
                color = R.color.dragon;
                break;
            case "electric":
                color = R.color.electric;
                break;
            case "fairy":
                color = R.color.fairy;
                break;
            case "fighting":
                color = R.color.fighting;
                break;
            case "fire":
                color = R.color.fire;
                break;
            case "flying":
                color = R.color.flying;
                break;
            case "ghost":
                color = R.color.ghost;
                break;
            case "grass":
                color = R.color.grass;
                break;
            case "ground":
                color = R.color.ground;
                break;
            case "ice":
                color = R.color.ice;
                break;
            case "normal":
                color = R.color.normal;
                break;
            case "poison":
                color = R.color.poison;
                break;
            case "psychic":
                color = R.color.psychic;
                break;
            case "rock":
                color = R.color.rock;
                break;
            case "steel":
                color = R.color.steel;
                break;
            case "water":
                color =  R.color.water;
                break;
            default:
                color = R.color.white;
        }
        return color;
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
