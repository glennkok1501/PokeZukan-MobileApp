package com.gmail.gk_dev_software.pokezukan.Main;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.gmail.gk_dev_software.pokezukan.Model.Pokemon;
import com.gmail.gk_dev_software.pokezukan.Model.PokemonBrief;
import com.gmail.gk_dev_software.pokezukan.PokemonActivity.PokemonActivity;
import com.gmail.gk_dev_software.pokezukan.R;
import com.gmail.gk_dev_software.pokezukan.Utils.AutoCap;
import com.gmail.gk_dev_software.pokezukan.Utils.PokemonSerializer;
import com.gmail.gk_dev_software.pokezukan.Utils.ProgressBarHelper;
import com.gmail.gk_dev_software.pokezukan.Utils.TypeHelper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Queue;

public class PokemonDialog {

    private Context context;
    private Dialog dialog;
    private TextView name, view;
    private ImageView sprite, type1, type2;
    private ProgressBar pb;
    private View layout;

    public PokemonDialog(Context context) {
        this.context = context;
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.pokemon_dialog);
        Window window = dialog.getWindow();
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);

        layout = dialog.findViewById(R.id.dialog_details_layout);
        name = dialog.findViewById(R.id.dialog_name_textView);
        sprite = dialog.findViewById(R.id.dialog_pokemon_imageView);
        type1 = dialog.findViewById(R.id.dialog_type1_imageView);
        type2 = dialog.findViewById(R.id.dialog_type2_imageView);
        pb = dialog.findViewById(R.id.pokemon_progressbar);
        view = dialog.findViewById(R.id.dialog_view);

        layout.setVisibility(View.INVISIBLE);
    }

    public void show(PokemonBrief pokemon){
        dialog.show();
        RequestQueue mQueue = Volley.newRequestQueue(context);
        ProgressBarHelper pbh = new ProgressBarHelper(pb);
        pbh.show();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, pokemon.getLink(), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            PokemonSerializer pokemonSerializer = new PokemonSerializer(context);
                            TypeHelper typeHelper = new TypeHelper(context);
                            Pokemon p = pokemonSerializer.serialize(response);

                            name.setText(AutoCap.set(p.getName()));
                            Glide.with(context)
                                    .load(p.getSprites().getArtwork())
                                    .error(R.drawable.ic_pokeball)
                                    .into(sprite);

                            typeHelper.setImages(p.getInfo().getType(), type1, type2);

                            pbh.hide();
                            layout.setVisibility(View.VISIBLE);

                            view.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dialog.cancel();
                                    Intent intent = new Intent(context, PokemonActivity.class);
                                    intent.putExtra("link", pokemon.getLink());
                                    context.startActivity(intent);
                                }
                            });
                        }

                        catch (Exception e) {
                            Toast.makeText(context, "Data unavailable", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                            pbh.hide();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Data unavailable", Toast.LENGTH_LONG).show();
                error.printStackTrace();
                pbh.hide();
            }
        });
        mQueue.add(request);
    }

    public void cancel(){
        dialog.cancel();
    }
}
