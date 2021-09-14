package com.gmail.gk_dev_software.pokezukan.Utils;

import android.content.Context;
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
import com.gmail.gk_dev_software.pokezukan.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONObject;

public class MoveBottomSheet {

    private Context context;
    private String move;
    private BottomSheetDialog dialog;
    private ImageView typeImg, catImg;
    private TextView name, type, cat, power, acc, pp, eff, des, con;
    private ProgressBarHelper pbh;
    private TypeHelper typeHelper;

    public MoveBottomSheet(Context context, String move) {
        this.context = context;
        this.move = move;
        dialog = new BottomSheetDialog(context, R.style.SheetDialog);
        dialog.setContentView(R.layout.move_bottom_sheet);
        name = dialog.findViewById(R.id.move_name_textView);
        type = dialog.findViewById(R.id.move_type_textView);
        cat = dialog.findViewById(R.id.move_cat_textView);
        power = dialog.findViewById(R.id.move_power_textView);
        acc = dialog.findViewById(R.id.move_acc_textView);
        pp = dialog.findViewById(R.id.move_pp_textView);
        eff = dialog.findViewById(R.id.move_effect_textView);
        des = dialog.findViewById(R.id.move_des_textView);
        con = dialog.findViewById(R.id.move_contest_textView);
        typeImg = dialog.findViewById(R.id.move_type_imageView);
        catImg = dialog.findViewById(R.id.move_cat_imageView);

        ProgressBar p1 = dialog.findViewById(R.id.move_name_progressBar);
        ProgressBar p2 = dialog.findViewById(R.id.move_type_progressBar);
        ProgressBar p3 = dialog.findViewById(R.id.move_cat_progressBar);
        ProgressBar p4 = dialog.findViewById(R.id.move_power_progressBar);
        ProgressBar p5 = dialog.findViewById(R.id.move_pp_progressBar);
        ProgressBar p6 = dialog.findViewById(R.id.move_acc_progressBar);
        ProgressBar p7 = dialog.findViewById(R.id.move_effect_progressBar);
        ProgressBar p8 = dialog.findViewById(R.id.move_des_progressBar);
        ProgressBar p9 = dialog.findViewById(R.id.move_contest_progressBar);

        pbh = new ProgressBarHelper(new ProgressBar[]{p1, p2, p3, p4, p5, p6, p7, p8, p9});

        typeHelper = new TypeHelper(context);
    }

    public void show(){
        String url = String.format("%s%s", context.getString(R.string.pokezukan_api), move);
        RequestQueue mQueue = Volley.newRequestQueue(context);
        pbh.multiShow();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String nameText = response.getString("name");
                            String typeText = response.getString("type");
                            String catText = response.getString("category");
                            String powerText = response.getString("power");
                            String ppText = response.getString("pp");
                            String conText = response.getString("contest");
                            String accText = response.getString("accuracy");
                            String effText = response.getString("effect");
                            String desText = response.getString("description");

                            typeHelper.setImage(typeText, typeImg);
                            setCatImg(context, catText, catImg);

                            name.setText(AutoCap.capStart(nameText));
                            type.setText(AutoCap.capStart(typeText));
                            cat.setText(AutoCap.capStart(catText));
                            setText(power, powerText);
                            setText(pp, ppText);
                            setText(acc, accText);
                            setText(eff, effText);
                            setText(des, desText);
                            setText(con, conText);
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

    private void setCatImg(Context context, String cat, ImageView imageView){
        String url = String.format("%simages/moves/%s.png", context.getString(R.string.git_repo), cat);
        Glide.with(context)
                .load(url)
                .into(imageView);
    }

    private void setText(TextView textView, String s){
        if (s.equals("null") || s.equals("dummy data")){
            textView.setText("\u23af");
        }
        else{
            textView.setText(AutoCap.capStart(s));
        }
    }
}
