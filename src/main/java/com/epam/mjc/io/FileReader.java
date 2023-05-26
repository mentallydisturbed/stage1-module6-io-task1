package com.epam.mjc.io;


import java.io.File;
import java.io.IOException;


public class FileReader {

    public Profile getDataFromFile(File file) {
        Profile profile = new Profile();
        try(java.io.FileReader in = new java.io.FileReader(file)) {
            int c;
            StringBuilder tp = new StringBuilder("");
            StringBuilder val = new StringBuilder("");
            boolean has = false;
            while((c = in.read()) != -1){
                if((char)(c) == '\n') {
                    int len = val.length();
                    if(tp.toString().contains("Name:")){
                        if(!Character.isDigit(val.charAt(len - 1)) && !Character.isAlphabetic(val.charAt(len - 1)))
                            len--;
                        profile.setName(val.substring(0, len));
                    }else if(tp.toString().contains("Age:")) {
                        if(!Character.isDigit(val.charAt(val.length() - 1)))
                            len--;
                        profile.setAge(Integer.parseInt(val.substring(0, len)));
                    }else if(tp.toString().contains("Email:")) {
                        if(!Character.isDigit(val.charAt(len - 1)) && !Character.isAlphabetic(val.charAt(len - 1)))
                            len--;
                        profile.setEmail(val.substring(0, len));
                    }else if(tp.toString().contains("Phone:")) {
                        if(!Character.isDigit(val.charAt(val.length() - 1)))
                            len--;
                        profile.setPhone(Long.parseLong(val.substring(0, len)));
                    }
                    tp = new StringBuilder("");
                    val = new StringBuilder("");
                    has = false;
                    continue;
                }else if((char)c == ' '){
                    has = true;
                }
                if(has && (char)c != ' ' && c != 10){
                    val.append((char)c);
                }else if(!has){
                    tp.append((char)c);
                }
            }
        } catch(IOException e) {
            // add logger
            // remove serr
        }

        return profile;
    }
}
