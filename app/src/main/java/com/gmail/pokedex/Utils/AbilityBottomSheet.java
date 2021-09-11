package com.gmail.pokedex.Utils;

import android.content.Context;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.gmail.pokedex.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AbilityBottomSheet {
    private Context context;
    private String ability;
    private BottomSheetDialog dialog;
    private TextView name, des;
    private ProgressBarHelper pbh;


    public AbilityBottomSheet(Context context, String ability) {
        this.context = context;
        this.ability = ability;
        dialog = new BottomSheetDialog(context, R.style.SheetDialog);
        dialog.setContentView(R.layout.ability_bottom_sheet);
        name = dialog.findViewById(R.id.ability_name_textView);
        des = dialog.findViewById(R.id.ability_des_textView);
        ProgressBar p1 = dialog.findViewById(R.id.ability_name_progressBar);
        ProgressBar p2 = dialog.findViewById(R.id.ability_des_progressBar);
        pbh = new ProgressBarHelper(new ProgressBar[]{p1, p2});
    }

    public void show(){
        String url = context.getString(R.string.cdn)+"/abilities/"+ability+".json";
        RequestQueue mQueue = Volley.newRequestQueue(context);
        pbh.multiShow();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String nameText = response.getString("name");
                            String desText = response.getString("description");
                            name.setText(AutoCap.set(nameText));
                            des.setText(AutoCap.set(desText));
                            pbh.multiHide();

                        }

                        catch (Exception e) {
                            Toast.makeText(context, "Data unavailable", Toast.LENGTH_SHORT).show();
                            pbh.multiHide();
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Data unavailable", Toast.LENGTH_LONG).show();
                pbh.multiHide();
                error.printStackTrace();
            }
        });
        mQueue.add(request);
        dialog.show();
    }

}
