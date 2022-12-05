package com.yikuni.mc.rumiyalib.utils;

import org.bukkit.ChatColor;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class StringUtil {
    public static String colorString(String s){
        char[] chars = s.toCharArray();
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < s.length(); i++){
            if (chars[i] == '&'){
                builder.append(ChatColor.getByChar(chars[++i]));
            }else if (chars[i] == '\\'){
                if (chars[++i] == 'n'){
                    builder.append('\n');
                }else if (chars[i] == 't'){
                    builder.append('\t');
                }else {
                    builder.append('\\');
                    i--;
                }
            }else {
                builder.append(chars[i]);
            }
        }
        return builder.toString();
    }

    public static String colorString(String... args){
        final StringBuilder builder = new StringBuilder();
        for (String arg: args){
            builder.append(arg);
            if (arg != args[args.length - 1]){
                builder.append(' ');
            }
        }
        return colorString(builder.toString());
    }

    public static ArrayList<String> splitToArray(String s, int limit){
        ArrayList<String> result = new ArrayList<>();
        int i = 0;
        while (i + limit < s.length()){
            result.add(s.substring(i, i + limit));
            i += limit;
        }
        if (i != s.length()){
            result.add(s.substring(i));
        }
        return result;
    }

    public static String[] splitToArray2(String s, int limit){
        String[] result = new String[s.length() / limit + 1];
        return splitToArray(s, limit).toArray(result);
    }

}
