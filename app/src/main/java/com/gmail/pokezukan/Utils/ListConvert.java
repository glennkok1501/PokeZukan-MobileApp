package com.gmail.pokezukan.Utils;

import java.util.List;

public class ListConvert {
    public static String ToString(List<String> ls){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < ls.size(); i++){
            String text = AutoCap.capStart(ls.get(i).replace("-", " "));
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
