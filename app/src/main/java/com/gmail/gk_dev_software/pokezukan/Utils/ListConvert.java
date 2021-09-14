package com.gmail.gk_dev_software.pokezukan.Utils;

import android.os.Build;

import com.gmail.gk_dev_software.pokezukan.Utils.Comparators.StringComparator;

import java.util.List;
import java.util.stream.Stream;

public class ListConvert {
    public static String ToString(List<String> ls){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            ls.sort(new StringComparator());
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String[] strLs = new String[ls.size()];
            for (int i = 0; i < ls.size(); i++){
                String text = AutoCap.capStart(ls.get(i).replace("-", " "));
                strLs[i] = text;
            }
            return String.join(", ", strLs);
        }
        else{
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < ls.size(); i++){
                String text = String.format("\u2022 %s", AutoCap.capStart(ls.get(i).replace("-", " ")));
                if (i == ls.size() - 1){
                    builder.append(text);
                }
                else{
                    builder.append(String.format("%s, ", text));
                }
            }
            return builder.toString();
            }
    }

    public static String ToBulletList(List<String> ls){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            ls.sort(new StringComparator());
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String[] strLs = new String[ls.size()];
            for (int i = 0; i < ls.size(); i++){
                String text = String.format("\u2022 %s", AutoCap.capStart(ls.get(i).replace("-", " ")));
                strLs[i] = text;
            }
            return String.join("\n", strLs);
        }
        else{
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < ls.size(); i++){
                String text = String.format("\u2022 %s", AutoCap.capStart(ls.get(i).replace("-", " ")));
                if (i == ls.size() - 1){
                    builder.append(text);
                }
                else{
                    builder.append(String.format("%s\n", text));
                }
            }
            return builder.toString();
        }
    }

}
