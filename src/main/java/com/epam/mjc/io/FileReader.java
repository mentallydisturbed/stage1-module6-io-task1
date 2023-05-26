package com.epam.mjc.io;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class FileReader {

    public Profile getDataFromFile(File file) {
        FileWriter fileWriter = null;
        Profile profile = new Profile();
        try(java.io.FileReader in = new java.io.FileReader(file)) {
            int c;
            String tp = "";
            String val = "";
            boolean has = false;
            while((c = in.read()) != -1){
                if((char)(c) == '\n') {
                    if(tp.contains("Name:")){
                        profile.setName(val.substring(0, val.length() - 1));
                    }else if(tp.contains("Age:")) {
                        profile.setAge(Integer.parseInt(val.substring(0, val.length() - 1)));
                    }else if(tp.contains("Email:")) {
                        profile.setEmail(val.substring(0, val.length() - 1));
                    }else if(tp.contains("Phone:")) {
                        profile.setPhone(Long.parseLong(val.substring(0, val.length() - 1)));
                    }
                    tp = "";
                    val = "";
                    has = false;
                    continue;
                }else if((char)c == ' '){
                    has = true;
                }
                if(has && (char)c != ' '){
                    val += (char)c;
                }else if(!has){
                    tp += (char)c;
                }
            }
        } catch(IOException e) {
            System.out.println(e);
        }
        return profile;
    }
}
