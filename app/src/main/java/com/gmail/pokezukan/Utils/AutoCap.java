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

    public static String capStart(String s){
        int pos = 0;
        boolean capitalize = true;
        StringBuilder sb = new StringBuilder(s);
        while (pos < sb.length()) {
            if (sb.charAt(pos) == '.') {
                capitalize = true;
            } else if (capitalize && !Character.isWhitespace(sb.charAt(pos))) {
                sb.setCharAt(pos, Character.toUpperCase(sb.charAt(pos)));
                capitalize = false;
            }
            pos++;
        }
        return sb.toString();
    }
}
