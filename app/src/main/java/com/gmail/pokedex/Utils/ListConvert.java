package com.gmail.pokedex.Utils;

import java.util.List;

public class ListConvert {
    public static String ToString(List<String> ls){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < ls.size(); i++){
            if (i == ls.size() - 1){
                builder.append(AutoCap.set(ls.get(i)));
            }
            else{
                builder.append(String.format("%s, ", AutoCap.set(ls.get(i))));
            }
        }
        return builder.toString();
    }

}
