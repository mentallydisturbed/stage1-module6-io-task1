package com.epam.mjc.io;


import java.io.File;
import java.io.FileWriter;
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
                    if(tp.toString().contains("Name:")){
                        profile.setName(val.substring(0, val.length() - 1));
                    }else if(tp.toString().contains("Age:")) {
                        profile.setAge(Integer.parseInt(val.substring(0, val.length() - 1)));
                    }else if(tp.toString().contains("Email:")) {
                        profile.setEmail(val.substring(0, val.length() - 1));
                    }else if(tp.toString().contains("Phone:")) {
                        profile.setPhone(Long.parseLong(val.substring(0, val.length() - 1)));
                    }
                    tp = new StringBuilder("");
                    val = new StringBuilder("");
                    has = false;
                    continue;
                }else if((char)c == ' '){
                    has = true;
                }
                if(has && (char)c != ' '){
                    val.append((char)c);
                }else if(!has){
                    tp.append((char)c);
                }
            }
        } catch(IOException e) {
            System.out.println(e);
        }
        return profile;
    }
}
