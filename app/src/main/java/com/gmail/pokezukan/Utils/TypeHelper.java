package com.gmail.pokezukan.Utils;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.gmail.pokezukan.R;

import java.util.List;

public class TypeHelper {

    public TypeHelper() {
    }

    public void setImages(Context context, List<String> types, ImageView image1, ImageView image2){
        image2.setVisibility(View.INVISIBLE);
        Glide.with(context)
                .load(getImage(types.get(0)))
                .placeholder(R.drawable.blank_type)
                .error(R.drawable.blank_type)
                .into(image1);
        if (types.size() > 1){
            image2.setVisibility(View.VISIBLE);
            Glide.with(context)
                .load(getImage(types.get(1)))
                .placeholder(R.drawable.blank_type)
                .error(R.drawable.blank_type)
                .into(image2);
        }
    }

    public void setImage(Context context, String type, ImageView imageView){
        Glide.with(context)
                .load(getImage(type))
                .placeholder(R.drawable.blank_type)
                .error(R.drawable.blank_type)
                .into(imageView);
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

    public String getImage(String type){
        String image;
        String url = "https://raw.githubusercontent.com/glennkok1501/SimpleDexAPI/main/images/types/";
        switch (type){
            case "bug":
                image = "ic_bug";
                break;
            case "dark":
                image = "ic_dark";
                break;
            case "dragon":
                image = "ic_dragon";
                break;
            case "electric":
                image = "ic_electric";
                break;
            case "fairy":
                image = "ic_fairy";
                break;
            case "fighting":
                image = "ic_fighting";
                break;
            case "fire":
                image = "ic_fire";
                break;
            case "flying":
                image = "ic_flying";
                break;
            case "ghost":
                image = "ic_ghost";
                break;
            case "grass":
                image = "ic_grass";
                break;
            case "ground":
                image = "ic_ground";
                break;
            case "ice":
                image = "ic_ice";
                break;
            case "normal":
                image = "ic_normal";
                break;
            case "poison":
                image = "ic_poison";
                break;
            case "psychic":
                image = "ic_psychic";
                break;
            case "rock":
                image = "ic_rock";
                break;
            case "steel":
                image = "ic_steel";
                break;
            case "water":
                image = "ic_water";
                break;
            default:
                image = "";
        }
        return String.format("%s%s.png",url, image);
    }
}
