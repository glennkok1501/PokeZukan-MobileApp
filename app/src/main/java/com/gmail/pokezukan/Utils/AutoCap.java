package com.gmail.pokezukan.Utils;

public class AutoCap {
    public static String set(String s){
        String words[]=s.split("\\s");
        StringBuilder capitalizeWord = new StringBuilder();
        for(String w:words){
            String first=w.substring(0,1);
            String afterfirst=w.substring(1);
            capitalizeWord.append(first.toUpperCase()+afterfirst+" ");
        }
        return capitalizeWord.toString().trim();
    }
}
